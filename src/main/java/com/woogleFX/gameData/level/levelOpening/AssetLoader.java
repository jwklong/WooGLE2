package com.woogleFX.gameData.level.levelOpening;

import com.woogleFX.editorObjects.Asset;
import com.woogleFX.engine.fx.*;
import com.woogleFX.engine.fx.assetSelectPane.FXAssetSelectPane;
import com.woogleFX.engine.fx.editorButtons.FXEditorButtons;
import com.woogleFX.engine.fx.menu.FXMenu;
import com.woogleFX.engine.gui.AssetSelector;
import com.woogleFX.engine.gui.LevelSelector;
import com.woogleFX.engine.gui.alarms.AskForLevelNameAlarm;
import com.woogleFX.engine.gui.alarms.LoadingResourcesAlarm;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.gameData.level.*;
import com.woogleFX.gameData.level.levelSaving.AssetUpdater;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class AssetLoader {

    private static final Logger logger = LoggerFactory.getLogger(AssetLoader.class);


    public static void newAsset(GameVersion version) {
        AskForLevelNameAlarm.show("new", version);
    }


    public static void openAsset(GameVersion version) {
        new LevelSelector(version).start(new Stage());
    }


    /** Creates a new, default level. */
    public static void newAsset(AssetSelector assetSelector, String name) {
        logger.debug("New level");

        FXAssetSelectPane.getAssetSelectPane().setMinHeight(30);
        FXAssetSelectPane.getAssetSelectPane().setMaxHeight(30);

        Asset asset = assetSelector.newInstance(name);
        AssetUpdater.saveAsset(asset);
        finishOpeningAsset(asset);

    }


    public static final ArrayList<String> failedResources = new ArrayList<>();


    public static void openAsset(AssetSelector assetSelector, String levelName) {

        System.out.println("I'm here!");

        // Don't open a level if none selected
        if (levelName == null || levelName.isEmpty()) return;

        // Don't open a level if it's already open
        for (Tab tab : FXAssetSelectPane.getAssetSelectPane().getTabs()) {
            if (tab.getText() != null && tab.getText().equals(levelName) && ((AssetTab)tab).getAsset().getVersion() == assetSelector.getVersion()) {
                FXAssetSelectPane.getAssetSelectPane().getSelectionModel().select(tab);
                return;
            }
        }

        failedResources.clear();

        System.out.println("Performance check 1.");

        Asset level = assetSelector.openInstance(levelName);
        if (level == null) return;

        System.out.println("Performance check 2.");

        level.setLevelName(levelName);
        FXEditorButtons.updateAllButtons();
        FXMenu.updateAllButtons();

        FXAssetSelectPane.getAssetSelectPane().setMinHeight(30);
        FXAssetSelectPane.getAssetSelectPane().setMaxHeight(30);

        level.load();

        if (!failedResources.isEmpty()) {
            StringBuilder fullError = new StringBuilder();
            for (String resource : failedResources) {
                fullError.append("\n").append(resource);
            }
            LoadingResourcesAlarm.show(fullError.substring(1));
        }

        finishOpeningAsset(level);

    }


    public static void cloneLevel(String name) {
        FXAssetSelectPane.getAssetSelectPane().setMinHeight(30);
        FXAssetSelectPane.getAssetSelectPane().setMaxHeight(30);

        Asset _level = AssetManager.getAsset().clone(name);

        AssetUpdater.saveAsset(_level);

        finishOpeningAsset(_level);

    }


    public static void cloneLevel() {
        GameVersion version = AssetManager.getAsset().getVersion();
        AskForLevelNameAlarm.show("clone", version);
    }


    public static void finishOpeningAsset(Asset asset) {

        AssetTab assetSelectButton = FXAssetSelectPane.createAssetTab(asset);
        FXAssetSelectPane.getAssetSelectPane().getTabs().add(assetSelectButton);

        int numTabs = FXAssetSelectPane.getAssetSelectPane().getTabs().size();
        double tabSize = 1 / (numTabs + 1.0);
        double tabWidth = tabSize * (FXAssetSelectPane.getAssetSelectPane().getWidth() - 15) - 15;
        FXAssetSelectPane.getAssetSelectPane().setTabMaxWidth(tabWidth);
        FXAssetSelectPane.getAssetSelectPane().setTabMinWidth(tabWidth);

        asset.setAssetTab(assetSelectButton);
        asset.setEditingStatus(AssetTab.NO_UNSAVED_CHANGES, true);
        FXAssetSelectPane.getAssetSelectPane().getSelectionModel().select(assetSelectButton);
        AssetManager.onSetAsset(asset);

    }


}
