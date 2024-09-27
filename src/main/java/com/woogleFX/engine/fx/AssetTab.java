package com.woogleFX.engine.fx;

import com.woogleFX.editorObjects.Asset;
import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.fx.hierarchy.FXHierarchy;
import com.woogleFX.file.FileManager;
import com.woogleFX.gameData.level._Level;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class AssetTab extends Tab {

    private final Asset asset;
    public Asset getAsset() {
        return asset;
    }


    public AssetTab(String text, Asset asset) {
        super(text);
        this.asset = asset;
    }


    public static final int NO_UNSAVED_CHANGES = 0;
    public static final int UNSAVED_CHANGES = 1;
    public static final int UNSAVED_CHANGES_UNMODIFIABLE = 2;


    public void update(int editingStatus, boolean shouldSelect) {

        AnchorPane pane = new AnchorPane();

        pane.getChildren().add(new ImageView(buildGraphics(asset)));

        TreeItem<EditorObject> root = FXHierarchy.getHierarchy().getRoot();

        StackPane graphicContainer = new StackPane();
        graphicContainer.prefWidthProperty().bind(getTabPane().tabMaxWidthProperty());
        StackPane.setAlignment(pane, Pos.CENTER_LEFT);
        Label label = new Label(asset.getLevelName());
        graphicContainer.getChildren().addAll(pane, label);
        setGraphic(graphicContainer);
        if (shouldSelect) {
            getTabPane().getSelectionModel().select(this);
            FXHierarchy.getHierarchy().setRoot(root);
        }

        switch (editingStatus) {
            case NO_UNSAVED_CHANGES -> {
                label.setText(asset.getLevelName());
                label.setStyle("-fx-text-fill: #000000;");
            }
            case UNSAVED_CHANGES -> {
                label.setText(asset.getLevelName() + "*");
                label.setStyle("-fx-text-fill: #808080;");
            }
            case UNSAVED_CHANGES_UNMODIFIABLE -> {
                label.setText(asset.getLevelName() + "*");
                label.setStyle("-fx-text-fill: #ff4040;");
            }
        }
    }


    public static Image buildGraphics(Asset asset) {
        String assetFileName;
        if (asset instanceof _Level) assetFileName = "ObjectIcons/assets/Level.png";
        else assetFileName = "ObjectIcons/assets/Ball.png";

        ImageView imageView = new ImageView(FileManager.getIcon(assetFileName));
        imageView.setFitWidth(24);
        imageView.setFitHeight(24);
        StackPane stackPane = new StackPane(imageView, new ImageView(FileManager.getIcon("ObjectIcons/assets/versionNumbers/2.png")));
        WritableImage writableImage = new WritableImage(24, 24);
        stackPane.snapshot(null, writableImage);
        return writableImage;
    }

}
