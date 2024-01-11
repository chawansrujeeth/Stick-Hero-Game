package com.example.recc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Text highScore;



    public void startGame(ActionEvent event) throws IOException {
        GameState.setPresent_score(0);
        GameState.setPresent_cherries(0);
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void setHighScoreText(int score) {
        highScore.setText("High Score: " + score);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int highestScore = FileWriteExample.readHighScoreFromFile();
        setHighScoreText(highestScore);
    }
}
