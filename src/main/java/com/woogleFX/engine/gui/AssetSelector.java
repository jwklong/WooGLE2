package com.woogleFX.engine.gui;

import com.woogleFX.editorObjects.Asset;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.levelOpening.AssetLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public abstract class AssetSelector extends Application {

    private final GameVersion version;
    public GameVersion getVersion() {
        return version;
    }
    public AssetSelector(GameVersion version) {
        this.version = version;
    }

    private Label selectedLabel;


    private void rebuildAssetSelectBox(VBox assetSelectBox, ArrayList<Label> labels, String searchField, int filterState) {

        assetSelectBox.getChildren().clear();

        int i = 0;
        for (Label label : labels) {
            if (label.getText().toLowerCase().contains(searchField.toLowerCase()) || searchField.isEmpty()) {
                if (filterState == 0 && !isOriginal(label.getText())) continue;
                if (filterState == 1 && isOriginal(label.getText())) continue;
                assetSelectBox.getChildren().add(label);
                if (i % 2 == 0) {
                    label.setStyle("-fx-background-color: #f0f0f0");
                    label.setId("-fx-background-color: #f0f0f0");
                }
                else {
                    label.setStyle("-fx-background-color: #e4e4e4");
                    label.setId("-fx-background-color: #e4e4e4");
                }
                label.setPrefWidth(400);
                i++;
            }
        }

    }


    private void buildStage(Stage stage) {

        VBox assetSelectBox = new VBox();

        ArrayList<Label> labels = new ArrayList<>();

        TextField searchField = new TextField();
        searchField.setPrefWidth(200);


        ComboBox<String> filter = new ComboBox<>();

        filter.getItems().addAll("Original Only", "Customizable Only", "All Assets");

        filter.getSelectionModel().selectedIndexProperty().addListener((observableValue, s, t1) ->
                rebuildAssetSelectBox(assetSelectBox, labels, searchField.getText(), t1.intValue()));
        filter.getSelectionModel().select(2);

        List<String> items = getItems();
        for (String item : items) {
            Label label = new Label(item);

            label.setOnMouseClicked(event -> {
                if (label == selectedLabel) {
                    AssetLoader.openAsset(this, label.getText());
                    stage.close();
                } else {
                    if (selectedLabel != null) selectedLabel.setStyle(selectedLabel.getId());
                    selectedLabel = label;
                    label.setStyle("-fx-background-color: #C0E0FFFF");
                }
            });

            labels.add(label);
        }

        searchField.textProperty().addListener((observableValue, string, t1) -> rebuildAssetSelectBox(assetSelectBox, labels, t1, filter.getSelectionModel().getSelectedIndex()));
        rebuildAssetSelectBox(assetSelectBox, labels, "", filter.getSelectionModel().getSelectedIndex());

        ScrollPane scrollPane = new ScrollPane(assetSelectBox);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        HBox hBox = new HBox(searchField, filter);

        VBox allEncompassingBox = new VBox(hBox, scrollPane);

        stage.setScene(new Scene(allEncompassingBox, 400, 375));

        stage.setTitle("Select " + getTitle() + "...");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        searchField.prefWidthProperty().bind(hBox.widthProperty().subtract(filter.widthProperty()));
        System.out.println(hBox.getWidth() - filter.getWidth());

    }

    @Override
    public void start(Stage stage) {
        buildStage(stage);
    }


    public abstract String getTitle();

    public abstract List<String> getItems();

    public abstract boolean isOriginal(String item);

    public abstract Asset newInstance(String name);

    public abstract Asset openInstance(String name);

}
