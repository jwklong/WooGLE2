package com.woogleFX.engine.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.woogleFX.file.FileManager;
import com.woogleFX.file.resourceManagers.BaseGameResources;
import com.woogleFX.gameData.level.levelOpening.LevelLoader;
import com.woogleFX.gameData.level.GameVersion;

public class LevelSelector extends AssetSelector {

    public LevelSelector(GameVersion version) {
        super(version);
    }

    @Override
    public List<String> getItems() {
        List<String> levels = new ArrayList<>();
        for (File child : Objects.requireNonNull(new File(FileManager.getGameDir(getVersion()) + "/res/levels").listFiles())) {
            if (getVersion() == GameVersion.VERSION_WOG1_OLD || getVersion() == GameVersion.VERSION_WOG1_NEW) {
                levels.add(child.getName());
            } else if (child.getName().endsWith(".wog2")) {
                levels.add(child.getName().substring(0, child.getName().length() - 5));
            }
        }
        return levels;
    }

    @Override
    public void onItemSelected(String item) {
        LevelLoader.openLevel(item, getVersion());
    }

    @Override
    public boolean isOriginal(String item) {
        return BaseGameResources.LEVELS.get(getVersion()).contains(item);
    }

}
