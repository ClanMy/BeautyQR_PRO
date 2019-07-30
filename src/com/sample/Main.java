package com.sample;

import com.sample.contro.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new Controller().mainStage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
