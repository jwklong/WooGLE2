package com.woogleFX.engine.gui.alarms;

import com.woogleFX.editorObjects.Asset;
import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.fx.assetSelectPane.FXAssetSelectPane;
import com.woogleFX.engine.fx.FXPropertiesView;
import com.woogleFX.engine.fx.hierarchy.FXHierarchy;
import com.woogleFX.gameData.level.LevelCloser;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;

public class CloseTabAlarm {

    public static void show(Tab tab, Asset level) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Unsaved Changes");
        alert.setContentText("Ignore unsaved changes?");

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType.equals(ButtonType.OK)) {
                if (tab.getTabPane().getTabs().size() == 1) {
                    FXAssetSelectPane.getAssetSelectPane().setMinHeight(0);
                    FXAssetSelectPane.getAssetSelectPane().setMaxHeight(0);
                    FXHierarchy.getHierarchy().setRoot(null);
                    FXPropertiesView.changeTableView(new EditorObject[]{});
                }
                Platform.runLater(() -> tab.getTabPane().getTabs().remove(tab));
            } else if (buttonType.equals(ButtonType.CANCEL)) {
                level.setEditingStatus(level.getEditingStatus(), level == AssetManager.getAsset());
            }
        });
    }

    public static void showClosingEditor(Tab tab, Asset asset) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Unsaved Changes");
        alert.setContentText("Ignore unsaved changes?");

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType.equals(ButtonType.OK)) {
                if (tab.getTabPane().getTabs().size() == 1) {
                    FXAssetSelectPane.getAssetSelectPane().setMinHeight(0);
                    FXAssetSelectPane.getAssetSelectPane().setMaxHeight(0);
                    FXHierarchy.getHierarchy().setRoot(null);
                    FXPropertiesView.changeTableView(new EditorObject[]{});
                }
                Platform.runLater(() -> {
                    tab.getTabPane().getTabs().remove(tab);
                    LevelCloser.resumeLevelClosing();
                });
            } else if (buttonType.equals(ButtonType.CANCEL)) {
                asset.setEditingStatus(asset.getEditingStatus(), asset == AssetManager.getAsset());
            }
        });
    }

}
