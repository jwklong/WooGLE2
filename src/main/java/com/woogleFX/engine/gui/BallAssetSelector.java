package com.woogleFX.engine.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.woogleFX.editorObjects.Asset;
import com.woogleFX.file.FileManager;
import com.woogleFX.file.resourceManagers.BaseGameResources;
import com.woogleFX.gameData.ball._2Ball;
import com.woogleFX.gameData.level.GameVersion;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class BallAssetSelector extends AssetSelector {

    public BallAssetSelector(GameVersion version) {
        super(version);
    }

    @Override
    public String getTitle() {
        return "ball";
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
    public boolean isOriginal(String item) {
        return BaseGameResources.GOO_BALL_TYPES.get(getVersion()).contains(item);
    }

    @Override
    public Asset newInstance(String name) {
        return new _2Ball(null, null);
    }

    @Override
    public Asset openInstance(String name) {
        try {
            return FileManager.open2Ball(name, getVersion());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            return null;
        }
    }

}
