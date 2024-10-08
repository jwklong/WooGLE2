package com.woogleFX.engine.fx;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.EditorAttribute;
import com.woogleFX.engine.fx.hierarchy.FXHierarchy;
import com.woogleFX.file.FileManager;
import com.woogleFX.file.resourceManagers.ResourceManager;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.gameData.level.WOG1Level;
import com.woogleFX.gameData.particle.ParticleManager;
import com.woogleFX.engine.undoHandling.UndoManager;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import com.woogleFX.engine.undoHandling.userActions.AttributeChangeAction;
import com.worldOfGoo.resrc.ResrcImage;
import com.worldOfGoo2.util.ItemHelper;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.File;
import java.util.Arrays;

public class FXPropertiesView {

    private static final TreeTableView<EditorAttribute> propertiesView = new TreeTableView<>();
    public static TreeTableView<EditorAttribute> getPropertiesView() {
        return propertiesView;
    }


    public static void init() {

        // This seems to break when the user double-clicks on a hierarchy item with children (like those in "Addin").
        // TODO: figure out how to fix this or make some equivalent placeholder display
        //propertiesView.setPlaceholder(new Label());

        // Create the columns the properties view uses (Attribute name and attribute value).
        TreeTableColumn<EditorAttribute, String> name = new TreeTableColumn<>();
        name.setGraphic(new Label("Name"));
        name.setCellValueFactory(param -> param.getValue().getValue().getNameProperty());
        name.setSortable(false);
        propertiesView.getColumns().add(name);

        TreeTableColumn<EditorAttribute, String> value = new TreeTableColumn<>();
        value.setGraphic(new Label("Value"));
        value.setCellValueFactory(param -> param.getValue().getValue().getValueProperty());
        value.setSortable(false);
        propertiesView.getColumns().add(value);

        // Limit the width of the "names" column.
        name.setPrefWidth(200);

        // Hide the empty "root" attribute. This allows all of its children to be
        // displayed at once at the top of the TreeTableView.
        propertiesView.setShowRoot(false);

        // For each row of the properties view:
        propertiesView.setRowFactory(tableView -> {
            final TreeTableRow<EditorAttribute> row = new TreeTableRow<>();

            // Manually change the row's font size to prevent clipping.
            row.setStyle("-fx-font-size: 11");

            row.hoverProperty().addListener((observable) -> {
                if (row.isHover() && row.getItem() != null) {
                    // If the user is hovering over this row, select it.
                    propertiesView.getSelectionModel().select(row.getIndex());
                } else {
                    // If the user is not hovering over this row, deselect all rows.
                    // This works because hovering off a row is processed before hovering onto
                    // another row.
                    propertiesView.getSelectionModel().clearSelection();
                }
            });

            row.pressedProperty().addListener((observable, oldValue, newValue) -> {
                // If we are editing a cell with null content or an uneditable cell, cancel the
                // edit.
                if (row.getTreeItem() == null || row.getTreeItem().getValue().getType() == null) {
                    Platform.runLater(() -> propertiesView.edit(-1, null));
                }
            });

            row.setId("notDragTarget");

            return row;
        });

        name.setCellFactory(column -> new TreeTableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    // If this is an empty cell, set its text and graphic to empty.
                    // This prevents the cell from retaining other cells' information.
                    setText(null);
                } else {

                    if (getTableRow().getTreeItem() != null) {
                        EditorAttribute EditorAttribute = getTableRow().getTreeItem().getValue();
                        if (InputField.verify(EditorAttribute.getObject(), EditorAttribute.getType(), EditorAttribute.stringValue())) {
                            setStyle("-fx-text-fill: #000000ff");
                        } else {
                            setStyle("-fx-text-fill: #ff0000ff");
                        }
                    }

                    // Update this cell's text.
                    setText(item);
                    // Override the default padding that ruins the text.
                    setPadding(new Insets(0, 0, 0, 0));
                }
            }
        });

        value.setCellFactory(new Callback<>() {
            @Override
            public TreeTableCell<EditorAttribute, String> call(
                    TreeTableColumn<EditorAttribute, String> editorAttributeStringTreeTableColumn) {
                StringConverter<String> stringConverter = new StringConverter<>() {
                    @Override
                    public String toString(String s) {
                        return s;
                    }

                    @Override
                    public String fromString(String s) {
                        return s;
                    }
                };
                TextFieldTreeTableCell<EditorAttribute, String> cell = new TextFieldTreeTableCell<>(stringConverter) {
                    private TextField textField;

                    private ContextMenu contextMenu;

                    @Override
                    public void startEdit() {
                        super.startEdit();
                        if (textField == null) {
                            createTextField();
                        }
                        setText(null);
                        setGraphic(textField);
                        textField.setText(getItem());
                        textField.selectAll();
                        textField.requestFocus();
                        before = getItem();

                        Bounds bounds = localToScreen(getBoundsInLocal());

                        if (bounds == null) return;

                        double x = bounds.getMinX();
                        double y = bounds.getMinY() + 18;

                        if (contextMenu != null) contextMenu.hide();
                        contextMenu = possibleAttributeValues(this, getItem(), LevelManager.getLevel().getVersion());

                        if (((VBox)((ScrollPane)contextMenu.getItems().get(0).getGraphic()).getContent()).getChildren().isEmpty()) return;

                        contextMenu.show(FXStage.getStage(), x, y);

                        contextMenu.requestFocus();

                        TextField textField = new TextField();
                        textField.setText(getItem());
                        textField.selectAll();
                    }

                    @Override
                    public void cancelEdit() {
                        super.cancelEdit();
                        setText(getItem());
                        setGraphic(null);
                        if (getContextMenu() != null) {
                            getContextMenu().hide();
                        }
                    }

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            if (isEditing()) {
                                if (textField != null) {
                                    textField.setText(getItem());
                                }
                                setText(null);
                                setGraphic(textField);
                            } else {
                                setText(getItem());
                                setGraphic(null);
                            }
                        }

                        // Override the default padding that ruins the text.
                        setPadding(new Insets(0, 0, 0, 2));
                    }

                    private void createTextField() {
                        textField = new TextField(getItem());
                        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
                        TextFieldTreeTableCell<EditorAttribute, String> cell = this;
                        textField.textProperty().addListener((observable, oldValue, newValue) -> {
                            if (contextMenu == null) return;
                            contextMenu.hide();
                            contextMenu = possibleAttributeValues(cell, newValue, LevelManager.getLevel().getVersion());

                            Bounds bounds = localToScreen(getBoundsInLocal());

                            if (bounds == null) return;

                            double x = bounds.getMinX();
                            double y = bounds.getMinY() + 18;

                            contextMenu.show(FXStage.getStage(), x, y);

                            contextMenu.requestFocus();
                        });
                        textField.setOnAction(event -> commitEdit(textField.getText()));
                    }

                    private String before;

                    @Override
                    public void commitEdit(String s) {
                        InputField type = getTableRow().getItem().getType();
                        EditorObject object = getTableRow().getItem().getObject();
                        super.commitEdit(InputField.verify(object, type, s) ? s : before);
                    }



                };

                cell.setOnMousePressed(e -> {
                    if (!cell.isEmpty()) cell.startEdit();
                    e.consume();
                });

                return cell;
            }
        });

        // value.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());

        value.setOnEditCommit(e -> {
            // When editing a cell:

            // Change the actual attribute to reflect the edit.
            EditorAttribute attribute = propertiesView.getTreeItem(e.getTreeTablePosition().getRow()).getValue();
            String oldValue = attribute.stringValue();
            if (InputField.verify(attribute.getObject(), attribute.getType(), e.getNewValue())) {
                attribute.getObject().setAttribute(attribute.getName(), e.getNewValue());
            }

            // If the edit was actually valid:
            if (e.getNewValue().isEmpty() || InputField.verify(attribute.getObject(), attribute.getType(), e.getNewValue())) {

                // Push an attribute change to the undo buffer.
                UndoManager.registerChange(new AttributeChangeAction(attribute, oldValue,
                        attribute.stringValue()));

                // If we have edited the name or ID of the object, change the object's "Name or
                // ID" value.

            } else {
                // Reset the attribute.
                attribute.setValue(oldValue);
                // If the user entered an invalid value, refresh to clear the edit.
                propertiesView.refresh();
            }

            FXHierarchy.getHierarchy().refresh();

        });

        // Adjust the row height to fit more attributes on screen at once.
        // This currently breaks the text.
        propertiesView.setFixedCellSize(18);

        // Set the "value" column and the entire TreeTableView to be editable.
        value.setEditable(true);
        propertiesView.setEditable(true);

        propertiesView.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);

        propertiesView.setPrefWidth(FXHierarchy.getHierarchy().getPrefWidth());

        propertiesView.prefWidthProperty().bind(FXHierarchy.getHierarchy().widthProperty());
        propertiesView.setRoot(new TreeItem<>(EditorAttribute.NULL));

    }


    public static TreeItem<EditorAttribute> makePropertiesViewTreeItem(EditorObject[] selectionList) {

        if (selectionList.length == 0) return null;

        EditorObject object = selectionList[0];

        // If some of the objects are of different types, don't fill out the properties view. That would just be weird.
        if (!Arrays.stream(selectionList).allMatch(e -> e.getType().equals(object.getType()))) return null;

        // Create the root tree item.
        TreeItem<EditorAttribute> treeItem = new TreeItem<>(EditorAttribute.NULL);

        // Loop over the object's meta attributes.
        for (MetaEditorAttribute metaEditorAttribute : object.getMetaAttributes()) {

            // Find the object's EditorAttribute associated with this meta attribute
            // (sharing the same name).
            EditorAttribute attribute;
            if (object.attributeExists(metaEditorAttribute.getName())) {
                attribute = object.getAttribute(metaEditorAttribute.getName());
            } else {
                // If no such attribute exists, this attribute is instead the name of a category
                // of attributes.
                // In this case, create a dummy attribute with no value.
                attribute = new EditorAttribute(metaEditorAttribute.getName(), null, object);
            }
            if (attribute.getType() == InputField._2_LIST_CHILD || attribute.getType() == InputField._2_LIST_CHILD_HIDDEN) continue;
            TreeItem<EditorAttribute> thisAttribute = new TreeItem<>(attribute);

            // If this attribute is set to be open by default, set its tree item to open.
            if (metaEditorAttribute.getOpenByDefault()) {
                thisAttribute.setExpanded(true);
            }

            // If this attribute represents a category of attributes, it will have children.
            // Add the children's TreeItems as children of the category's TreeItem.
            for (MetaEditorAttribute childAttribute : metaEditorAttribute.getChildren()) {
                thisAttribute.getChildren().add(new TreeItem<>(object.getAttribute(childAttribute.getName())));
            }

            // Add the attribute's TreeItem as a child of the root's TreeItem.
            treeItem.getChildren().add(thisAttribute);

        }

        return treeItem;

    }


    private static ContextMenu possibleAttributeValues(TextFieldTreeTableCell<EditorAttribute, String> cell, String currentText, GameVersion version) {
        ContextMenu contextMenu = new ContextMenu();
        EditorAttribute attribute = cell.getTableRow().getItem();
        if (attribute == null) {
            return contextMenu;
        }

        VBox vBox = new VBox();

        switch (attribute.getType()) {
            case _1_IMAGE, _1_IMAGE_REQUIRED -> {
                for (EditorObject resource : ((WOG1Level)LevelManager.getLevel()).getResrc()) {
                    if (resource instanceof ResrcImage) {
                        Button setImageItem = new Button(resource.getAttribute("id").stringValue());

                        configureButton(setImageItem);

                        // Add thumbnail of the image to the menu item
                        try {
                            ImageView graphic = new ImageView(ResourceManager.getImage(((WOG1Level)LevelManager.getLevel()).getResrc(), resource.getAttribute("id").stringValue(), version));
                            graphic.setFitHeight(30);
                            // Set width depending on height
                            graphic.setFitWidth(graphic.getImage().getWidth() * 30 / graphic.getImage().getHeight());
                            setImageItem.setGraphic(graphic);
                        } catch (Exception ignored) {

                        }


                        setImageItem.setOnAction(event -> {
                            UndoManager.registerChange(new AttributeChangeAction(attribute,
                                    attribute.stringValue(), resource.getAttribute("id").stringValue()));
                            attribute.setValue(resource.getAttribute("id").stringValue());
                            if (contextMenu.isFocused()) {
                                cell.commitEdit(attribute.stringValue());
                            }
                        });

                        vBox.getChildren().add(setImageItem);
                    }
                }
            }
            case _1_BALL -> {
                String path = FileManager.getGameDir(version);
                File[] ballFiles = new File(path + "\\res\\balls").listFiles();
                if (ballFiles != null) {
                    for (File ballFile : ballFiles) {
                        Button setImageItem = new Button(ballFile.getName());

                        configureButton(setImageItem);

                        setImageItem.setOnAction(event -> {
                            UndoManager.registerChange(new AttributeChangeAction(attribute,
                                    attribute.stringValue(), ballFile.getName()));
                            attribute.setValue(ballFile.getName());
                            if (contextMenu.isFocused()) {
                                cell.commitEdit(attribute.stringValue());
                            }
                        });

                        vBox.getChildren().add(setImageItem);
                    }
                }
            }
            case _2_BALL_TYPE -> {
                String path = FileManager.getGameDir(version);
                File[] ballFiles = new File(path + "\\res\\balls").listFiles();
                if (ballFiles != null) {
                    for (File ballFile : ballFiles) {
                        if (ballFile.getName().contains(".")) continue;
                        if (!(ballFile.getName().toLowerCase().contains(currentText.toLowerCase()))) continue;
                        Button setImageItem = new Button(ballFile.getName());

                        configureButton(setImageItem);

                        setImageItem.setOnAction(event -> {
                            UndoManager.registerChange(new AttributeChangeAction(attribute,
                                    attribute.stringValue(), ballFile.getName()));
                            attribute.setValue(ballFile.getName());
                            if (contextMenu.isFocused()) {
                                cell.commitEdit(attribute.stringValue());
                            }
                        });

                        vBox.getChildren().add(setImageItem);
                    }
                }
            }
            case _1_PARTICLES -> {
                for (String particleType : ParticleManager.getSortedParticleNames()) {
                    Button setImageItem = new Button(particleType);

                    configureButton(setImageItem);

                    setImageItem.setOnAction(event -> {
                        UndoManager.registerChange(new AttributeChangeAction(attribute,
                                attribute.stringValue(), particleType));
                        attribute.setValue(particleType);
                        if (contextMenu.isFocused()) {
                            cell.commitEdit(attribute.stringValue());
                        }
                    });

                    vBox.getChildren().add(setImageItem);
                }
            }

            case _2_ITEM_TYPE -> {

                for (String itemType : ItemHelper.itemNameMap.values()) {
                    if (!itemType.toLowerCase().contains(currentText.toLowerCase())) continue;
                    Button setImageItem = new Button(itemType);

                    configureButton(setImageItem);

                    setImageItem.setOnAction(event -> {
                        UndoManager.registerChange(new AttributeChangeAction(attribute,
                                attribute.stringValue(), itemType));
                        attribute.setValue(itemType);
                        if (contextMenu.isFocused()) {
                            cell.commitEdit(attribute.stringValue());
                        }
                    });

                    vBox.getChildren().add(setImageItem);
                }

            }

        }

        vBox.setPrefWidth(300);
        vBox.setMaxHeight(100);

        ScrollPane pane = new ScrollPane(vBox);
        pane.setPadding(new Insets(0, -12, 0, 0));
        pane.setMaxWidth(300);
        pane.setPrefWidth(300);
        pane.setMaxHeight(300);

        MenuItem menuItem = new MenuItem();
        menuItem.setGraphic(pane);
        contextMenu.getItems().add(menuItem);

        menuItem.setId("contextMenu");

        pane.setId("contextMenu");

        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        contextMenu.setId("contextMenu");

        return contextMenu;

    }


    private static void configureButton(Button button) {

        button.setMinWidth(300);
        button.setMaxWidth(300);
        button.setPrefWidth(300);
        button.setMinHeight(36);
        button.setMaxHeight(36);
        button.setPrefHeight(36);
        button.setPadding(new Insets(2));
        button.setId("contextMenu");
        button.setAlignment(Pos.CENTER_LEFT);

    }


    public static void changeTableView(EditorObject[] selectionList) {
        propertiesView.setRoot(makePropertiesViewTreeItem(selectionList));
    }


}
