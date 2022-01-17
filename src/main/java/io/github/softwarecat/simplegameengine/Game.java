package io.github.softwarecat.simplegameengine;

import io.github.softwarecat.simplegameengine.input.InputPolling;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main_view.fxml"));
        Scene scene = new Scene(root);

        InputPolling.getInstance().pollScene(scene);

        stage.setTitle("Game");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
