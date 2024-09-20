package com.woogleFX.gameData.ball.ballOpening;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.gui.BallAssetSelector;
import com.woogleFX.engine.gui.alarms.ErrorAlarm;
import com.woogleFX.file.FileManager;
import com.woogleFX.gameData.ball._2Ball;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.levelOpening.LevelLoader;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class BallLoader {

    public static void openBall(GameVersion version) {
        new BallAssetSelector(version).start(new Stage());
    }


    public static void openBall(String ballName, GameVersion version) {

        _2Ball level;

        try {
            level = FileManager.open2Ball(ballName, version);
            if (level == null) return;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            ErrorAlarm.show(e);
            return;
        }

        level.setLevelName(ballName);

        for (EditorObject editorObject : level.getObjects()) {
            editorObject.update();
            editorObject.onLoaded();
        }

        AssetManager.setAsset(level);

        LevelLoader.finishOpeningLevel(level);

        level.resetCamera();

    }

}
