package com.example.recc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("entry.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 600);
        GameState.setHighest_score(FileWriteExample.readHighScoreFromFile());
        stage.setTitle("babblu2!");
        stage.setScene(scene);
        stage.show();
//        Random new2 = new Random();
//        int bab = new2.nextInt(2)+10;
//        System.out.println(bab);
    }

    public static void main(String[] args) {
        launch();
    }
}