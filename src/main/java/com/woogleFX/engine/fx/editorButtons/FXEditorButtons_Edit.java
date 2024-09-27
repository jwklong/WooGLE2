package com.woogleFX.engine.fx.editorButtons;

import com.woogleFX.editorObjects.ObjectManager;
import com.woogleFX.editorObjects.clipboardHandling.ClipboardManager;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.SelectionManager;
import com.woogleFX.engine.undoHandling.UndoManager;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;

public class FXEditorButtons_Edit {

    public static final FXEditorButtons.EditorButton buttonUndo = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    public static final FXEditorButtons.EditorButton buttonRedo = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonCut = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonCopy = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonPaste = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonDelete = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    public static final FXEditorButtons.EditorButton buttonSelectMoveAndResize = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    public static final FXEditorButtons.EditorButton buttonStrandMode = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    public static final FXEditorButtons.EditorButton buttonGeometryMode = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };


    public static void edit(ToolBar toolBar) {

        String prefix = "ButtonIcons/Edit/";

        buttonUndo.setIcon(prefix + "undo.png");
        buttonUndo.setOnAction(e -> UndoManager.undo());
        buttonUndo.setTooltip(new FXEditorButtons.DelayedTooltip("Undo"));
        toolBar.getItems().add(buttonUndo);

        buttonRedo.setIcon(prefix + "redo.png");
        buttonRedo.setOnAction(e -> UndoManager.redo());
        buttonRedo.setTooltip(new FXEditorButtons.DelayedTooltip("Redo"));
        toolBar.getItems().add(buttonRedo);

        toolBar.getItems().add(new Separator());

        buttonCut.setIcon(prefix + "cut.png");
        buttonCut.setOnAction(e -> ClipboardManager.cut());
        buttonCut.setTooltip(new FXEditorButtons.DelayedTooltip("Cut"));
        toolBar.getItems().add(buttonCut);

        buttonCopy.setIcon(prefix + "copy.png");
        buttonCopy.setOnAction(e -> ClipboardManager.copy());
        buttonCopy.setTooltip(new FXEditorButtons.DelayedTooltip("Copy"));
        toolBar.getItems().add(buttonCopy);

        buttonPaste.setIcon(prefix + "paste.png");
        buttonPaste.setOnAction(e -> ClipboardManager.paste());
        buttonPaste.setTooltip(new FXEditorButtons.DelayedTooltip("Paste"));
        toolBar.getItems().add(buttonPaste);

        toolBar.getItems().add(new Separator());

        buttonDelete.setIcon(prefix + "delete.png");
        buttonDelete.setOnAction(e -> ObjectManager.delete(AssetManager.getAsset()));
        buttonDelete.setTooltip(new FXEditorButtons.DelayedTooltip("Delete"));
        toolBar.getItems().add(buttonDelete);

        toolBar.getItems().add(new Separator());

        buttonSelectMoveAndResize.setIcon(prefix + "selection_mode.png");
        buttonSelectMoveAndResize.setOnAction(e -> SelectionManager.selectionMode());
        buttonSelectMoveAndResize.setStyle("-fx-background-color: #9999ff;"); // Highlighted by default
        buttonSelectMoveAndResize.setTooltip(new FXEditorButtons.DelayedTooltip("Select, Move and Resize"));
        toolBar.getItems().add(buttonSelectMoveAndResize);

        buttonStrandMode.setIcon(prefix + "strand_mode.png");
        buttonStrandMode.setOnAction(e -> SelectionManager.strandMode());
        buttonStrandMode.setTooltip(new FXEditorButtons.DelayedTooltip("Place Strands"));
        toolBar.getItems().add(buttonStrandMode);

        buttonGeometryMode.setIcon(prefix + "geometry_mode.png");
        buttonGeometryMode.setOnAction(e -> SelectionManager.geometryMode());
        buttonGeometryMode.setTooltip(new FXEditorButtons.DelayedTooltip("[Very Experimental] Build Geometry"));
        toolBar.getItems().add(buttonGeometryMode);

    }

}
