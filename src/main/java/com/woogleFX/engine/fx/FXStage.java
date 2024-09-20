package com.woogleFX.engine.fx;

import com.woogleFX.file.FileManager;
import com.woogleFX.gameData.level.LevelCloser;
import javafx.stage.Stage;

public class FXStage {

    private static Stage stage;
    public static Stage getStage() {
        return stage;
    }


    public static void init(Stage stage) {

        FXStage.stage = stage;
        stage.setTitle("World of Goo Anniversary Editor");
        stage.getIcons().add(FileManager.getIcon("ButtonIcons/icon.png"));
        stage.setScene(FXScene.getScene());
        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            LevelCloser.resumeLevelClosing();
        });
        stage.show();

    }

}
