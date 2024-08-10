package com.woogleFX.gameData.level;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.fx.hierarchy.FXHierarchy;
import com.woogleFX.file.FileManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class LevelTab extends Tab {

    private final _Level level;
    public _Level getLevel() {
        return level;
    }


    public LevelTab(String text, _Level level) {
        super(text);
        this.level = level;
    }

    public static final int NO_UNSAVED_CHANGES = 0;
    public static final int UNSAVED_CHANGES = 1;
    public static final int UNSAVED_CHANGES_UNMODIFIABLE = 2;

    private static final Image noChangesImageOld = FileManager.getIcon("ButtonIcons\\Level\\no_unsaved_changes_old.png");
    private static final Image changesImageOld = FileManager.getIcon("ButtonIcons\\Level\\unsaved_changes_old.png");
    private static final Image changesUnmodifiableImageOld = FileManager.getIcon("ButtonIcons\\Level\\unsaved_changes_unmodifiable_old.png");

    private static final Image noChangesImageNew = FileManager.getIcon("ButtonIcons\\Level\\no_unsaved_changes_new.png");
    private static final Image changesImageNew = FileManager.getIcon("ButtonIcons\\Level\\unsaved_changes_new.png");
    private static final Image changesUnmodifiableImageNew = FileManager.getIcon("ButtonIcons\\Level\\unsaved_changes_unmodifiable_new.png");

    private static final Image noChangesImage2 = FileManager.getIcon("ButtonIcons\\Level\\no_unsaved_changes_2.png");
    private static final Image changesImage2 = FileManager.getIcon("ButtonIcons\\Level\\unsaved_changes_2.png");
    private static final Image changesUnmodifiableImage2 = FileManager.getIcon("ButtonIcons\\Level\\unsaved_changes_unmodifiable_2.png");


    public void update(int editingStatus, boolean shouldSelect) {

        Image currentStatusImage = null;

        if (editingStatus == NO_UNSAVED_CHANGES) {
            currentStatusImage = switch (level.getVersion()) {
                case VERSION_WOG1_OLD -> noChangesImageOld;
                case VERSION_WOG1_NEW -> noChangesImageNew;
                default -> noChangesImage2;
            };
        } else if (editingStatus == UNSAVED_CHANGES) {
            currentStatusImage = switch (level.getVersion()) {
                case VERSION_WOG1_OLD -> changesImageOld;
                case VERSION_WOG1_NEW -> changesImageNew;
                default -> changesImage2;
            };
        } else if (editingStatus == UNSAVED_CHANGES_UNMODIFIABLE) {
            currentStatusImage = switch (level.getVersion()) {
                case VERSION_WOG1_OLD -> changesUnmodifiableImageOld;
                case VERSION_WOG1_NEW -> changesUnmodifiableImageNew;
                default -> changesUnmodifiableImage2;
            };
        }

        AnchorPane pane = new AnchorPane();

        pane.getChildren().add(new ImageView(currentStatusImage));

        TreeItem<EditorObject> root = FXHierarchy.getHierarchy().getRoot();

        StackPane graphicContainer = new StackPane();
        graphicContainer.prefWidthProperty().bind(getTabPane().tabMaxWidthProperty());
        StackPane.setAlignment(pane, Pos.CENTER_LEFT);
        graphicContainer.getChildren().addAll(pane, new Label(level.getLevelName()));
        setGraphic(graphicContainer);
        if (shouldSelect) {
            getTabPane().getSelectionModel().select(this);
            FXHierarchy.getHierarchy().setRoot(root);
        }
    }

}
