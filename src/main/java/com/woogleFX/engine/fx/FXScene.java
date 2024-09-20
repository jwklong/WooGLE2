package com.woogleFX.engine.fx;

import javafx.scene.Scene;

import java.awt.*;

public class FXScene {

    private static Scene scene;
    public static Scene getScene() {
        return scene;
    }


    public static void init() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double sceneWidth = screenSize.width * 0.75;
        double sceneHeight = screenSize.height * 0.75 - 30;
        scene = new Scene(FXContainers.getvBox(), sceneWidth, sceneHeight);
        scene.getStylesheets().add("style.css");
    }


}
