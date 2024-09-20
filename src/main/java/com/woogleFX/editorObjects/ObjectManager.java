package com.woogleFX.editorObjects;

import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.objectCreators.ObjectAdder;
import com.woogleFX.editorObjects.objectCreators.ObjectCreator;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.fx.hierarchy.FXHierarchy;
import com.woogleFX.engine.fx.FXPropertiesView;
import com.woogleFX.engine.fx.hierarchy.FXHierarchySwitcherButtons;
import com.woogleFX.engine.undoHandling.UndoManager;
import com.woogleFX.engine.undoHandling.userActions.ObjectDestructionAction;
import com.woogleFX.engine.undoHandling.userActions.UserAction;
import com.woogleFX.gameData.level.WOG1Level;
import com.woogleFX.gameData.level.WOG2Level;
import com.worldOfGoo.level.BallInstance;
import com.worldOfGoo.level.Level;
import com.worldOfGoo.level.Strand;
import com.worldOfGoo.level.Vertex;
import com.worldOfGoo.scene.Scene;
import com.worldOfGoo2.level._2_Level_BallInstance;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectManager {

    public static void create(Asset _level, EditorObject object, int row) {

        System.out.println("creating " + object);

        if (object instanceof _2_Level_BallInstance) {
            ObjectAdder.fixGooBall(object);
        }

        if (!object.getParent().getChildren().contains(object)) {
            object.getParent().getChildren().add(row, object);

            if (!object.getParent().attributeExists(object.getTypeID()) &&
                    object.getParent().getAttribute(object.getTypeID()).getType() != InputField._2_CHILD_HIDDEN
                            && object.getParent().getAttribute(object.getTypeID()).getType() != InputField._2_LIST_CHILD_HIDDEN)
                object.getParent().getTreeItem().getChildren().add(row, object.getTreeItem());
        }

        if (!object.getChildren().isEmpty()) {
            int i = 0;
            for (EditorObject child : object.getChildren().toArray(new EditorObject[0])) {
                create(_level, child, i);
                i++;
            }
        }

        _level.addItem(object, row);

        object.update();

    }


    public static List<ObjectDestructionAction> deleteItem(Asset _level, EditorObject _item, boolean parentDeleted) {

        System.out.println("deleting " + _item);
        List<ObjectDestructionAction> childActions = _item.onDelete();

        if (childActions != null) for (ObjectDestructionAction action : childActions) {
            deleteItem(_level, action.getObject(), false);
        }

        for (EditorObject child : _item.getChildren().toArray(new EditorObject[0])) {
            deleteItem(_level, child, true);
        }

        _level.removeItem(_item, parentDeleted);

        return childActions;

    }


    public static void delete(Asset level) {

        ArrayList<ObjectDestructionAction> objectDestructionActions = new ArrayList<>();

        ArrayList<EditorObject> newSelectionBuilder = new ArrayList<>();
        for (EditorObject selected : level.getSelected()) {

            EditorObject parent = selected.getParent();
            int row = parent.getTreeItem().getChildren().indexOf(selected.getTreeItem());

            objectDestructionActions.add(new ObjectDestructionAction(selected, Math.max(row, 0)));

            EditorObject parentObject = (row <= 0) ? parent : parent.getTreeItem().getChildren().get(row - 1).getValue();
            if (Arrays.stream(level.getSelected()).noneMatch(e -> e == parentObject)) newSelectionBuilder.add(parentObject);
        }

        List<ObjectDestructionAction> allActions = new ArrayList<>(objectDestructionActions);

        for (ObjectDestructionAction action : objectDestructionActions) {
            List<ObjectDestructionAction> actions = deleteItem(level, action.getObject(), false);
            if (actions != null) allActions.addAll(actions);
        }
        
        allActions.sort((a, b) -> b.compareTo(a));
        UndoManager.registerChange(allActions.toArray(new UserAction[0]));

        EditorObject[] newSelected = newSelectionBuilder.toArray(new EditorObject[0]);
        level.setSelected(newSelected);
        FXPropertiesView.changeTableView(newSelected);
        if (newSelected.length != 0) {
            int[] indices = new int[newSelected.length - 1];
            for (int i = 0; i < newSelected.length - 1; i++)
                indices[i] = FXHierarchy.getHierarchy().getRow(newSelected[i + 1].getTreeItem());
            FXHierarchy.getHierarchy().getSelectionModel().selectIndices(FXHierarchy.getHierarchy().getRow(newSelected[0].getTreeItem()), indices);
        } else FXHierarchy.getHierarchy().getSelectionModel().clearSelection();
        FXHierarchy.getHierarchy().refresh();

    }

}
