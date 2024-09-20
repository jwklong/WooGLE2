package com.woogleFX.engine.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.woogleFX.file.FileManager;
import com.woogleFX.file.resourceManagers.BaseGameResources;
import com.woogleFX.gameData.ball.ballOpening.BallLoader;
import com.woogleFX.gameData.level.GameVersion;

public class BallAssetSelector extends AssetSelector {

    public BallAssetSelector(GameVersion version) {
        super(version);
    }

    @Override
    public List<String> getItems() {
        List<String> levels = new ArrayList<>();
        for (File child : Objects.requireNonNull(new File(FileManager.getGameDir(getVersion()) + "/res/balls").listFiles())) {
            levels.add(child.getName());
        }
        return levels;
    }

    @Override
    public void onItemSelected(String item) {
        BallLoader.openBall(item, getVersion());
    }

    @Override
    public boolean isOriginal(String item) {
        return BaseGameResources.GOO_BALL_TYPES.get(getVersion()).contains(item);
    }

}
