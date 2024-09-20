package com.woogleFX.editorObjects.clipboardHandling;

import com.woogleFX.editorObjects.Asset;
import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.fx.hierarchy.FXHierarchy;
import com.woogleFX.engine.fx.FXPropertiesView;
import com.woogleFX.editorObjects.objectCreators.ObjectAdder;
import com.woogleFX.editorObjects.ObjectManager;
import com.woogleFX.engine.undoHandling.UndoManager;
import com.woogleFX.engine.undoHandling.userActions.ObjectCreationAction;
import com.woogleFX.engine.undoHandling.userActions.UserAction;
import com.woogleFX.gameData.level.WOG1Level;
import com.woogleFX.gameData.level.WOG2Level;
import com.worldOfGoo.level.BallInstance;
import com.worldOfGoo.level.Strand;
import com.worldOfGoo2.level._2_Level_BallInstance;
import com.worldOfGoo2.level._2_Level_Strand;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.util.ArrayList;

public class ClipboardManager {

    public static void cut() {
        if (AssetManager.getAsset().getSelected().length != 0) {
            copy();
            ObjectManager.delete(AssetManager.getAsset());
        }
    }


    public static void copy() {
        if (AssetManager.getAsset().getSelected().length != 0 && FXPropertiesView.getPropertiesView().getEditingCell() == null) {
            String clipboard = ClipboardHandler.exportToClipBoardString(AssetManager.getAsset().getSelected());
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(clipboard);
            Clipboard.getSystemClipboard().setContent(clipboardContent);
        }
    }


    public static void paste() {

        Asset level = AssetManager.getAsset();
        if (level == null) return;

        if (FXPropertiesView.getPropertiesView().getEditingCell() != null) return;

        String clipboard = Clipboard.getSystemClipboard().getString();
        if (clipboard == null) return;


        EditorObject[] selectedList;
        try {
            selectedList = ClipboardHandler.importFromClipboardString(clipboard);
            if (selectedList == null) return;
        } catch (Exception e) {
            return;
        }

        ArrayList<UserAction> objectCreationActions = new ArrayList<>();

        for (EditorObject object : selectedList) {

            if (object instanceof BallInstance) {
                ObjectAdder.fixGooBall(object);
                for (EditorObject EditorObject : ((WOG1Level)level).getLevel()) {
                    if (EditorObject instanceof Strand strand) {
                        strand.update();
                    }
                }
            }

            if (object instanceof _2_Level_BallInstance) {
                ObjectAdder.fixGooBall(object);
                for (EditorObject EditorObject : ((WOG2Level)level).getObjects()) {
                    if (EditorObject instanceof _2_Level_Strand strand) {
                        if (strand.getAttribute("ball1UID").stringValue().equals(object.getAttribute("uid").stringValue())
                        || strand.getAttribute("ball2UID").stringValue().equals(object.getAttribute("uid").stringValue()))
                            strand.update();
                    }
                }
            }

            if (level instanceof WOG2Level wog2Level) {
                object.setParent(wog2Level.getLevel());
                wog2Level.getObjects().add(object);
                object.update();
                object.onLoaded();
                ObjectManager.create(level, object, wog2Level.getLevel().getChildren().size());
            } else {
                ObjectManager.create(level, object, 0);
            }
            objectCreationActions.add(new ObjectCreationAction(object, object.getParent().getChildren().indexOf(object)));

            ObjectAdder.adjustObjectLocation(object);

        }

        UndoManager.registerChange(objectCreationActions.toArray(new UserAction[0]));
        level.setSelected(selectedList);
        FXHierarchy.getHierarchy().refresh();

    }

}
