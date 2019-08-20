package com.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class test1 extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Button b1 = new Button("Close");
        b1.setEffect(new DropShadow());

        VBox root = new VBox();
        root.getChildren().addAll(b1);

        Scene scene = new Scene(root,100,100);
        stage.setScene(scene);
        stage.setTitle("Testing LayoutBounds");
        stage.show();

    }
}