package com.woogleFX.gameData.level.levelSaving;

import com.woogleFX.editorObjects.Asset;
import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.fx.AssetTab;
import com.woogleFX.engine.gui.alarms.AskForLevelNameAlarm;
import com.woogleFX.engine.gui.alarms.ErrorAlarm;
import com.woogleFX.engine.gui.alarms.LevelIssuesAlarm;
import com.woogleFX.engine.fx.assetSelectPane.FXAssetSelectPane;
import com.woogleFX.file.FileManager;
import com.woogleFX.gameData.level.*;
import com.woogleFX.gameData.level.levelOpening.LevelLoader;
import com.worldOfGoo.resrc.Resources;
import com.worldOfGoo.resrc.ResrcImage;
import com.worldOfGoo.resrc.Sound;
import javafx.scene.control.Tab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class LevelUpdater {

    private static final Logger logger = LoggerFactory.getLogger(LevelLoader.class);


    public static void saveLevel(Asset level) {
        if (!saveSpecificAsset(level)) return;
        level.setLastSavedUndoPosition(level.undoActions.size());
        if (level.getEditingStatus() != AssetTab.NO_UNSAVED_CHANGES)
            level.setEditingStatus(AssetTab.NO_UNSAVED_CHANGES, true);
    }


    public static boolean saveSpecificAsset(Asset asset) {

        boolean okayToSave = true;

        // Check for errors in level objects
        if (!asset.verifyAll()) {
            // Fail to save
            ErrorAlarm.show("Level could not be verified");
            okayToSave = false;
        }

        if (asset instanceof WOG2Level wog2Level) {

            ArrayList<String> levelErrors = AssetVerifier.checkForWoG2Errors(wog2Level);

            if (!levelErrors.isEmpty()) {
                if (LevelIssuesAlarm.show(levelErrors)) return false;
            }

        }
        // TODO: check for game errors (stuff like there being a levelexit but no pipe)

        if (!okayToSave) return false;

        return asset.save();

    }


    public static void saveAll() {
        int selectedIndex = FXAssetSelectPane.getAssetSelectPane().getSelectionModel().getSelectedIndex();
        for (Tab tab : FXAssetSelectPane.getAssetSelectPane().getTabs().toArray(new Tab[0])) {
            AssetTab assetTab = (AssetTab) tab;
            if (assetTab.getAsset().getEditingStatus() == AssetTab.UNSAVED_CHANGES) {
                if (saveSpecificAsset(assetTab.getAsset())) {
                    assetTab.getAsset().setEditingStatus(AssetTab.NO_UNSAVED_CHANGES, false);
                }
            }
        }
        FXAssetSelectPane.getAssetSelectPane().getSelectionModel().select(selectedIndex);
    }

    public static void renameLevel(_Level level) {
        if (level != null) {
            AskForLevelNameAlarm.show("changeName", level.getVersion());
        }
    }

    public static void renameLevel(_Level level, String text) {

        logger.info("Renaming " + level.getLevelName() + " to " + text);

        String start = FileManager.getGameDir(level.getVersion());

        /* Change level name in directory */
        File originalLevelDirectory = new File(start + "/res/levels/" + level.getLevelName());
        File levelDirectory = new File(start + "/res/levels/" + text);
        if (!originalLevelDirectory.renameTo(levelDirectory)) {
            ErrorAlarm.show("Could not rename level! (" + level.getLevelName() + " to " + text + ")");
            return;
        }

        /* Change the names of the scene, level, resrc, addin, text files */
        File[] levelParts = levelDirectory.listFiles();
        if (levelParts == null) return;

        for (File levelPart : levelParts) {
            if (levelPart.getName().length() >= level.getLevelName().length()
                    && levelPart.getName().startsWith(level.getLevelName())) {
                if (!levelPart.renameTo(new File(start + "/res/levels/" + text + "/" + text
                        + levelPart.getName().substring(level.getLevelName().length())))) {
                    ErrorAlarm.show("Could not rename level! (" + level.getLevelName() + " to " + text + ")");
                    return;
                }
            }
        }

        /* Edit every resource */
        for (EditorObject resource : ((WOG1Level)level).getResrc()) {

            if (resource instanceof Resources) {

                resource.setAttribute("id", "scene_" + text);

            } else if (resource instanceof ResrcImage || resource instanceof Sound) {

                String previousID = resource.getAttribute("id").stringValue();
                String newID = previousID.replaceAll(level.getLevelName().toUpperCase(), text.toUpperCase());
                resource.setAttribute("id", newID);

                String previousPath = resource.getAttribute("path").stringValue();
                String newPath = previousPath.replaceAll(level.getLevelName(), text);
                resource.setAttribute("path", newPath);

            }

        }

        level.setLevelName(text);
        level.setEditingStatus(level.getEditingStatus(), true);

        saveLevel(level);

    }

    public static void deleteLevel(_Level level) {
        if (level == null) return;
        AskForLevelNameAlarm.show("delete", level.getVersion());
    }

    public static void nuke(File file) throws IOException {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) for (File child : children) {
                nuke(child);
            }
        }
        Files.delete(file.toPath());
    }

}
