package com.woogleFX.engine;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.EditorAttribute;
import com.woogleFX.engine.fx.FXContainers;
import com.woogleFX.engine.fx.editorButtons.FXEditorButtons;
import com.woogleFX.engine.fx.editorButtons.FXEditorButtons_Edit;
import com.woogleFX.engine.fx.hierarchy.FXHierarchy;
import com.woogleFX.editorObjects.DragSettings;
import com.woogleFX.engine.fx.hierarchy.FXHierarchySwitcherButtons;
import com.woogleFX.gameData.level.GameVersion;
import javafx.scene.control.SplitPane;

public class SelectionManager {

    private static double mouseStartX;
    public static double getMouseStartX() {
        return mouseStartX;
    }
    public static void setMouseStartX(double mouseStartX) {
        SelectionManager.mouseStartX = mouseStartX;
    }


    private static double mouseStartY;
    public static double getMouseStartY() {
        return mouseStartY;
    }
    public static void setMouseStartY(double mouseStartY) {
        SelectionManager.mouseStartY = mouseStartY;
    }


    private static double mouseX = 0;
    public static double getMouseX() {
        return mouseX;
    }
    public static void setMouseX(double mouseX) {
        SelectionManager.mouseX = mouseX;
    }


    private static double mouseY = 0;
    public static double getMouseY() {
        return mouseY;
    }
    public static void setMouseY(double mouseY) {
        SelectionManager.mouseY = mouseY;
    }


    private static EditorAttribute[][] oldAttributes;
    public static EditorAttribute[][] getOldAttributes() {
        return oldAttributes;
    }
    public static void setOldAttributes(EditorAttribute[][] _oldAttributes) {
        oldAttributes = _oldAttributes;
    }


    private static EditorObject[] oldSelected;
    public static EditorObject[] getOldSelected() {
        return oldSelected;
    }
    public static void setOldSelected(EditorObject[] oldSelected) {
        SelectionManager.oldSelected = oldSelected;
    }


    private static DragSettings dragSettings = DragSettings.NULL;
    public static DragSettings getDragSettings() {
        return dragSettings;
    }
    public static void setDragSettings(DragSettings dragSettings) {
        SelectionManager.dragSettings = dragSettings;
    }


    public static final int SELECTION = 0;
    public static final int STRAND = 1;
    public static final int GEOMETRY = 2;

    private static int mode = SELECTION;
    public static int getMode() {
        return mode;
    }

    public static void selectionMode() {
        mode = SELECTION;
        // Highlight selection button blue
        FXEditorButtons_Edit.buttonSelectMoveAndResize.setStyle("-fx-background-color: #9999ff;");
        // Un-highlight strand button
        FXEditorButtons_Edit.buttonStrandMode.setStyle("");
        // Un-highlight geometry button
        FXEditorButtons_Edit.buttonGeometryMode.setStyle("");
    }

    public static void strandMode() {
        mode = STRAND;
        // Highlight strand button blue
        FXEditorButtons_Edit.buttonStrandMode.setStyle("-fx-background-color: #9999ff;");
        // Un-highlight selection button
        FXEditorButtons_Edit.buttonSelectMoveAndResize.setStyle("");
        // Un-highlight geometry button
        FXEditorButtons_Edit.buttonGeometryMode.setStyle("");
    }

    public static void geometryMode() {
        mode = GEOMETRY;
        // Highlight geometry button blue
        FXEditorButtons_Edit.buttonGeometryMode.setStyle("-fx-background-color: #9999ff;");
        // Un-highlight selection button
        FXEditorButtons_Edit.buttonSelectMoveAndResize.setStyle("");
        // Un-highlight strand button
        FXEditorButtons_Edit.buttonStrandMode.setStyle("");
    }


    private static EditorObject strand1Gooball;
    public static EditorObject getStrand1Gooball() {
        return strand1Gooball;
    }
    public static void setStrand1Gooball(EditorObject strand1Gooball) {
        SelectionManager.strand1Gooball = strand1Gooball;
    }

    public static void goToSelectedInHierarchy() {

        EditorObject[] selectedArray = AssetManager.getAsset().getSelected();
        if (selectedArray.length == 0 || selectedArray[0] == null) {

            SplitPane splitPane = FXContainers.getSplitPane();
            double editorViewWidth = splitPane.getDividerPositions()[0] * splitPane.getWidth() - 6;

            if (SelectionManager.getMouseX() < editorViewWidth)
                FXHierarchy.getHierarchy().getSelectionModel().clearSelection();

            return;
        }

        EditorObject selected = selectedArray[0];

        EditorObject absoluteParent = selected;
        if (selected.getVersion() != GameVersion.VERSION_WOG2)
            while (absoluteParent.getParent() != null) absoluteParent = absoluteParent.getParent();

        if (selected.getParent() != null) {

            if (selected.getVersion() != GameVersion.VERSION_WOG2)
                FXHierarchy.getHierarchy().setRoot(absoluteParent.getTreeItem());
            FXHierarchy.getHierarchy().setShowRoot(true);


            switch (absoluteParent.getType()) {
                case "scene" -> FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(0);
                case "level" -> FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(1);
                case "resourcemanifest" -> FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(2);
                case "strings" -> FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(3);
                case "addin" -> {
                    FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(4);
                    FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(5);
                }

                case "BallInstance" -> {
                    if (absoluteParent.getAttribute("type").stringValue().equals("Terrain"))
                        FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(0);
                    else FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(2);
                }
                case "TerrainGroup" -> FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(1);
                case "Item" -> FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(3);
                case "Pin" -> FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(4);
                case "CameraKeyFrame" -> FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(5);

            }
        }
    }

}
