package com.woogleFX.engine.fx.hierarchy;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.EditorAttribute;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.objectCreators.ObjectAdder;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.file.FileManager;
import com.woogleFX.engine.undoHandling.UndoManager;
import com.woogleFX.engine.undoHandling.userActions.HierarchyDragAction;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.WOG1Level;
import com.woogleFX.gameData.level.WOG2Level;
import com.woogleFX.gameData.level._Level;
import com.worldOfGoo.addin.*;
import com.worldOfGoo.level.*;
import com.worldOfGoo.resrc.*;
import com.worldOfGoo.scene.*;
import com.worldOfGoo.text.TextStrings;
import com.worldOfGoo2.level._2_Level_BallInstance;
import com.worldOfGoo2.level._2_Level_TerrainGroup;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class HierarchyManager {

    public static Image getObjectIcon(Class<? extends EditorObject> type, boolean terrain) {

        String iconName = switch (type.getSimpleName()) {
            case "addin", "Addin", "Addin_addin", "Addin_id", "Addin_name",
                    "Addin_type", "Addin_version", "Addin_description",
                    "Addin_author", "Addin_levels", "Addin_level",
                    "Addin_dir", "Addin_wtf_name",
                    "Addin_subtitle", "Addin_ocd" -> "addin/addin";
            case "BallInstance" -> "level/BallInstance";
            case "button" -> "scene/button";
            case "buttongroup" -> "scene/buttongroup";
            case "camera" -> "level/camera";
            case "circle" -> "scene/circle";
            case "compositegeom" -> "scene/compositegeom";
            case "endoncollision" -> "level/endoncollision";
            case "endonmessage" -> "level/endonmessage";
            case "endonnogeom" -> "level/endonnogeom";
            case "fire" -> "level/fire";
            case "font" -> "resrc/font";
            case "hinge" -> "scene/hinge";
            case "label" -> "scene/label";
            case "level", "_2_Level" -> "level/level";
            case "levelexit" -> "level/levelexit";
            case "line" -> "scene/line";
            case "linearforcefield" -> "scene/linearforcefield";
            case "loopsound" -> "level/loopsound";
            case "motor" -> "scene/motor";
            case "music" -> "level/music";
            case "particles" -> "scene/particles";
            case "pipe" -> "level/pipe";
            case "poi" -> "level/poi";
            case "rectangle" -> "scene/rectangle";
            case "radialforcefield" -> "scene/radialforcefield";
            case "ResourceManifest" -> "resrc/resourcemanifest";
            case "Resources" -> "resrc/resources";
            case "Image" -> "resrc/resrcimage";
            case "scene" -> "scene/scene";
            case "SceneLayer" -> "scene/SceneLayer";
            case "SetDefaults" -> "resrc/setdefaults";
            case "signpost" -> "level/signpost";
            case "slider" -> "scene/slider";
            case "Sound" -> "resrc/sound";
            case "Strand" -> "level/Strand";
            case "string" -> "text/textstring";
            case "strings" -> "text/textstrings";
            case "targetheight" -> "level/targetheight";
            case "Vertex" -> "level/Vertex";

            // WOG2

            case "CameraKeyFrame", "_2_Level_CameraKeyFrame" -> "level/camera";
            case "Pin", "_2_Level_Pin" -> "scene/hinge";
            case "Item" -> "scene/SceneLayer";
            case "UserVariable" -> "addin/addin";
            case "_2_Level_BallInstance" -> "level/BallInstance";
            case "_2_Level_Strand" -> "level/Strand";
            //case "_2_Level_TerrainGroup" -> "level/Strand";

            default -> null;
        };
        if (iconName == null) return null;

        if (terrain)
            iconName = "WoG2/TerrainBallInstance";

        return FileManager.getIcon("ObjectIcons/" + iconName + ".png");

    }

    public static Image getItemIcon(String name) {

        String iconName = switch (name) {

            case "LevelExit" -> "level/levelexit";
            case "CameraEOL", "CameraControl" -> "level/camera";
            case "Pool" -> "level/pipe";
            case "TerrainClear" -> "WoG2/terrainClear";
            case "TerrainDeadly" -> "WoG2/terrainDeadly";
            case "TerrainFrictionless" -> "WoG2/terrainFrictionless";
            case "TerrainNonSticky" -> "WoG2/terrainNonSticky";
            case "TerrainSticky" -> "WoG2/terrainSticky";
            case "TerrainUnwalkable" -> "WoG2/terrainUnwalkable";
            case "PipeInLiquid" -> "WoG2/pipeInLiquid";
            case "PipeInBalls" -> "WoG2/pipeInBalls";
            case "LinearForceField" -> "scene/linearforcefield";
            case "RadialForceField" -> "scene/radialforcefield";
            case "AmbientSoundArea" -> "level/loopsound";
            case "AutoBoundsArea" -> "WoG2/autoBounds";
            case "Water" -> "WoG2/water";
            case "Connection" -> "WoG2/connection";

            default -> "scene/SceneLayer";
        };
        if (iconName == null) return null;

        return FileManager.getIcon("ObjectIcons/" + iconName + ".png");

    }


    private static int oldDropIndex;
    public static void setOldDropIndex(int oldDropIndex) {
        HierarchyManager.oldDropIndex = oldDropIndex;
    }


    private static TreeTableRow<EditorObject> dragSourceRow;
    public static TreeTableRow<EditorObject> getDragSourceRow() {
        return dragSourceRow;
    }


    public static void updateNameCell(TreeTableCell<EditorObject, String> cell, String item, boolean empty) {

        if (empty) {
            // If this is an empty cell, set its text and graphic to empty.
            // This prevents the cell from retaining other cells' information.
            cell.setText(null);
            cell.setGraphic(null);
        } else {
            // setTextFill(Paint.valueOf("FFFFFFFF"));
            // Update this cell's text.
            cell.setText(item);
            // Override the default padding that ruins the text.
            cell.setPadding(new Insets(-2, 0, 0, 3));
        }

    }


    public static void updateElementCell(TreeTableCell<EditorObject, String> cell, String item, boolean empty) {

        if (empty) {
            // If this is an empty cell, set its text and graphic to empty.
            // This prevents the cell from retaining other cells' information.
            cell.setText(null);
            cell.setGraphic(null);
        } else {
            // Update this cell's text.
            cell.setText(item);
            // Override the default padding that ruins the text.
            cell.setPadding(new Insets(-2, 0, 0, 3));

            if (cell.getTableRow().getItem() != null) {
                ImageView imageView;

                boolean terrain = false;
                if (cell.getTableRow().getItem() instanceof _2_Level_BallInstance && cell.getTableRow().getItem().getAttribute("type").stringValue().equals("Terrain")) {
                    terrain = true;
                }
                imageView = new ImageView(getObjectIcon(cell.getTableRow().getItem().getClass(), terrain));
                if (cell.getTableRow().getItem().getType().equals("Item")) {
                    cell.setText(cell.getTableRow().getItem().getAttribute("type").stringValue());
                    imageView.setImage(getItemIcon(cell.getTableRow().getItem().getAttribute("type").stringValue()));
                }
                imageView.setFitWidth(16);
                imageView.setFitHeight(16);

                // If the cell's EditorObject is invalid, display its graphic with a warning symbol.
                // Otherwise, just display its graphic.

                boolean valid = true;

                EditorObject editorObject = cell.getTableRow().getItem();

                for (EditorAttribute attribute : editorObject.getAttributes()) {
                    if (attribute.stringValue().isEmpty()) {
                        if (!InputField.verify(editorObject, attribute.getType(), attribute.getDefaultValue(), attribute.getRequired())) {
                            valid = false;
                        }
                    } else if (!InputField.verify(editorObject, attribute.getType(), attribute.actualValue(), attribute.getRequired())) {
                        valid = false;
                    }
                }

                if (!valid) {
                    ImageView failedImg = new ImageView(FileManager.getFailedImage());
                    cell.setGraphic(new StackPane(imageView, failedImg));
                    cell.setStyle("-fx-text-fill: red");
                } else {
                    cell.setGraphic(imageView);
                    cell.setStyle("-fx-text-fill: black");
                }
            }
        }

    }


    public static boolean handleDragDrop(TreeTableView<EditorObject> hierarchy, int toIndex) {

        // TODO: give the user a way to choose if they want an object to be a child of the object above it

        if (toIndex == oldDropIndex) return false;

        EditorObject toItem = hierarchy.getTreeItem(toIndex).getValue();

        EditorObject fromItem = hierarchy.getTreeItem(oldDropIndex).getValue();

        EditorObject absoluteParent = fromItem;
        ArrayList<EditorObject> list;

        if (AssetManager.getAsset() instanceof WOG1Level level) {

            while (absoluteParent.getParent() != null) absoluteParent = absoluteParent.getParent();

            if (absoluteParent instanceof Scene) list = level.getScene();
            else if (absoluteParent instanceof Level) list = level.getLevel();
            else if (absoluteParent instanceof ResourceManifest) list = level.getResrc();
            else if (absoluteParent instanceof Addin) list = level.getAddin();
            else if (absoluteParent instanceof TextStrings) list = level.getText();
            else return false;

        } else {
            list = ((WOG2Level)AssetManager.getAsset()).getLevel().getChildren(fromItem.getTypeID());
        }

        int indexOfToItemInList = list.indexOf(toItem);

        // YOU CAN'T PUT AN OBJECT INSIDE ITSELF
        if (toItem.getChildren().contains(fromItem)) return false;

        // Or inside an object that doesn't have it as a possible child
        if (Stream.of(toItem.getParent().getPossibleChildren()).noneMatch(e -> e.equals(fromItem.getClass())))
            return false;

        // Or above every SetDefaults (meaning at position 2) if it's a resource
        if ((fromItem instanceof ResrcImage || fromItem instanceof Sound || fromItem instanceof Font) && toIndex == 2)
            return false;

        // Or anywhere that would put a resource at position 2 if it's a SetDefaults
        if (fromItem instanceof SetDefaults && (oldDropIndex == 2 && !(hierarchy.getTreeItem(3).getValue() instanceof SetDefaults)))
            return false;

        // Add the dragged item just above the item that it gets dragged to
        int indexOfToItem = toItem.getParent().getChildren().indexOf(toItem);

        fromItem.getParent().getChildren().remove(fromItem);
        fromItem.getParent().getTreeItem().getChildren().remove(fromItem.getTreeItem());

        fromItem.setParent(toItem.getParent(), indexOfToItem);

        list.remove(fromItem);
        list.add(indexOfToItemInList, fromItem);

        if (fromItem.getParent() instanceof Resources) ((_Level) AssetManager.getAsset()).reAssignSetDefaultsToAllResources();
        else if (fromItem instanceof Vertex) fromItem.getParent().update();

        hierarchy.getSelectionModel().select(toIndex);
        hierarchy.refresh();

        int i = FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().getSelectedIndex();
        int total = FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getTabs().size();
        FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select((i + 1) % total);
        FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(i);

        if (fromItem instanceof _2_Level_TerrainGroup terrainGroup1 && toItem instanceof _2_Level_TerrainGroup terrainGroup2) {
            for (EditorObject ball : ((WOG2Level)AssetManager.getAsset()).getLevel().getChildren("balls"))
                ball.setAttribute("terrainGroup", ball.getAttribute("terrainGroup").stringValue());
        }

        return true;

    }


    public static TreeTableRow<EditorObject> createRow(TreeTableView<EditorObject> hierarchy) {

        TreeTableRow<EditorObject> row = new TreeTableRow<>();

        row.setOnMousePressed(event -> {
            if (row.getTreeItem() == null) return;
            if (event.getButton().equals(MouseButton.SECONDARY)) row.setContextMenu(contextMenuForEditorObject(row.getTreeItem().getValue()));
        });

        row.setOnDragDetected(event -> {
            TreeItem<EditorObject> selected2 = hierarchy.getSelectionModel().getSelectedItem();
            if (selected2 == null) return;

            if (row.getTreeItem() == null) return;

            Dragboard db = hierarchy.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(selected2.getValue().getClass().getName());
            db.setContent(content);
            oldDropIndex = row.getIndex();
            event.consume();

            dragSourceRow = row;

            row.setId("dragTarget");

        });

        row.setOnDragExited(event -> {
            // row.setStyle("");
            row.setStyle("-fx-border-width: 0 0 0 0;");
            row.setTranslateY(0);
            row.setPadding(new Insets(1, 0, 0, 0));
        });

        row.setOnDragOver(event -> {
            if (event.getDragboard().hasString() && row.getTreeItem() != null) {

                if (event.getY() + row.getTranslateY() >= 9) {
                    row.setStyle("-fx-border-color: #a0a0ff; -fx-border-width: 0 0 2 0;");
                    row.setTranslateY(1);
                } else {
                    row.setStyle("-fx-border-color: #a0a0ff; -fx-border-width: 2 0 0 0;");
                    row.setTranslateY(-1);
                }
                row.setPadding(new Insets(0.5, 0, -0.5, 0));

                row.toFront();

                event.acceptTransferModes(TransferMode.MOVE);

            }
            event.consume();
        });

        row.setOnDragDropped(event -> {
            dragSourceRow.setId("notDragTarget");
            if (!row.isEmpty()) {
                int toIndex = row.getIndex();
                if (event.getY() + row.getTranslateY() >= 9) toIndex += 1;
                if (toIndex > oldDropIndex) toIndex -= 1;
                boolean completed = handleDragDrop(hierarchy, toIndex);
                event.setDropCompleted(event.getDragboard().hasString() && completed);

                if (completed) {
                    hierarchy.getSelectionModel().clearSelection();
                    hierarchy.getSelectionModel().select(FXHierarchy.getHierarchy().getTreeItem(toIndex));
                    UndoManager.registerChange(new HierarchyDragAction(FXHierarchy.getHierarchy().getTreeItem(oldDropIndex).getValue(), oldDropIndex, toIndex));
                }

            }
            event.consume();
        });

        row.setPadding(new Insets(1, 0, 0, 0));

        return row;

    }


    public static ContextMenu contextMenuForEditorObject(EditorObject object) {

        // Create the content menu.
        ContextMenu menu = new ContextMenu();

        // For every object that can be created as a child of this object:
        int i = 0;
        for (Class<? extends EditorObject> childToAdd : object.getPossibleChildren()) {

            // Create a menu item representing creating this child.
            MenuItem addItemItem = null;
            try {
                EditorObject newInstance = ((EditorObject)childToAdd.getConstructors()[0].newInstance((Object) null));
                addItemItem = new MenuItem(" Add " + newInstance.getType());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

            // Attempt to set graphics for this menu item.
            addItemItem.setGraphic(new ImageView(getObjectIcon(childToAdd, false)));

            // Set the item's action to creating the child, with the object as its parent.
            int finalI = i;
            addItemItem.setOnAction(event -> {
                if (object.getVersion() == GameVersion.VERSION_WOG2) ObjectAdder.addObject2(childToAdd, object.getPossibleChildrenTypeIDs()[finalI], object);
                else ObjectAdder.addObject(childToAdd, object);
            });
            i++;

            menu.getItems().add(addItemItem);
        }

        return menu;

    }

}
