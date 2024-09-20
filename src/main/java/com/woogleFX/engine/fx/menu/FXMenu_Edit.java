package com.woogleFX.engine.fx.menu;

import com.woogleFX.editorObjects.ObjectManager;
import com.woogleFX.editorObjects.clipboardHandling.ClipboardManager;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.undoHandling.UndoManager;
import javafx.scene.control.Menu;

public class FXMenu_Edit {

    private static final Menu editMenu = new Menu();
    public static Menu getEditMenu() {
        return editMenu;
    }


    public static final FXMenu.EditorMenuItem undoItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    public static final FXMenu.EditorMenuItem redoItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem cutItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem copyItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem pasteItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem deleteItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };


    public static void init() {

        editMenu.setText("Edit");

        String prefix = "ButtonIcons/Edit/";

        undoItem.setText("Undo");
        undoItem.setIcon(prefix + "undo.png");
        undoItem.setOnAction(e -> UndoManager.undo());
        editMenu.getItems().add(undoItem);

        redoItem.setText("Redo");
        redoItem.setIcon(prefix + "redo.png");
        redoItem.setOnAction(e -> UndoManager.redo());
        editMenu.getItems().add(redoItem);

        cutItem.setText("Cut");
        cutItem.setIcon(prefix + "cut.png");
        cutItem.setOnAction(e -> ClipboardManager.cut());
        editMenu.getItems().add(cutItem);

        copyItem.setText("Copy");
        copyItem.setIcon(prefix + "copy.png");
        copyItem.setOnAction(e -> ClipboardManager.copy());
        editMenu.getItems().add(copyItem);

        pasteItem.setText("Paste");
        pasteItem.setIcon(prefix + "paste.png");
        pasteItem.setOnAction(e -> ClipboardManager.paste());
        editMenu.getItems().add(pasteItem);

        deleteItem.setText("Delete");
        deleteItem.setIcon(prefix + "delete.png");
        deleteItem.setOnAction(e -> ObjectManager.delete(AssetManager.getAsset()));
        editMenu.getItems().add(deleteItem);

    }

}
