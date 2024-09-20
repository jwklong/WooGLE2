package com.woogleFX.engine.gui;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoadingScreen extends Application {

    private Task<Void> task;
    public void setTask(Task<Void> task) {
        this.task = task;
    }


    @Override
    public void start(Stage primaryStage) {

        Label label = new Label("Give it a second...");
        label.setPrefWidth(460);
        label.setPrefHeight(20);
        label.setTextAlignment(TextAlignment.CENTER);

        ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefWidth(460);
        progressBar.setPrefHeight(20);
        progressBar.progressProperty().bind(task.progressProperty());

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, progressBar);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setSpacing(10);
        vBox.setPrefSize(300, 200);

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Loading Level");
        primaryStage.setWidth(480);
        primaryStage.setHeight(270);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

}
