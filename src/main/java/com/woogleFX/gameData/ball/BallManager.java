package com.woogleFX.gameData.ball;

import com.woogleFX.engine.gui.alarms.ErrorAlarm;
import com.woogleFX.file.FileManager;
import com.woogleFX.engine.gui.BallSelector;
import com.woogleFX.gameData.level.GameVersion;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class BallManager {

    private static final ArrayList<_Ball> importedBalls = new ArrayList<>();
    public static ArrayList<_Ball> getImportedBalls() {
        return importedBalls;
    }

    private static final ArrayList<_2Ball> imported2Balls = new ArrayList<>();
    public static ArrayList<_2Ball> getImported2Balls() {
        return imported2Balls;
    }


    public static void saveBallInVersion(GameVersion oldVersion, GameVersion newVersion) {
        new BallSelector(oldVersion, newVersion).start(new Stage());
    }


    public static _Ball getBall(String ballName, GameVersion version) {
        for (_Ball ball : importedBalls) {
            if (ball.getVersion() == version && ball.getObjects().get(0).getAttribute("name").stringValue().equals(ballName)) {
                return ball;
            }
        }
        _Ball ball;
        try {
            ball = FileManager.openBall(ballName, version);
            if (ball == null) return null;
            ball.setVersion(version);
            importedBalls.add(ball);
            return ball;
        } catch (ParserConfigurationException | SAXException | IOException ignored) {
            return null;
        }
    }


    public static _2Ball get2Ball(String ballName, GameVersion version) {
        for (_2Ball ball : imported2Balls.toArray(new _2Ball[0])) {
            if (ball.getVersion() == version && ball.getObjects().get(0).getAttribute("name").stringValue().equals(ballName)) {
                return ball;
            }
        }
        _2Ball ball;
        try {
            ball = FileManager.open2Ball(ballName, version);
            if (ball == null) return null;
            ball.setVersion(version);
            imported2Balls.add(ball);
            return ball;
        } catch (ParserConfigurationException | SAXException | IOException ignored) {
            return null;
        }
    }


    public static void saveBallInVersion(String ball, GameVersion oldVersion, GameVersion newVersion) {

        try {

            _Ball _ball = getBall(ball, oldVersion);
            if (_ball == null) return;

            String dir = FileManager.getGameDir(newVersion);

            BallWriter.saveAsXML(_ball, dir + "\\res\\balls\\" + ball, newVersion, false);

        } catch (Exception e) {
            ErrorAlarm.show(e);
        }

    }

}
