package io.github.softwarecat.simplegameengine.controller;

import io.github.softwarecat.simplegameengine.animation.GameLoopTimer;
import io.github.softwarecat.simplegameengine.entity.Entity;
import io.github.softwarecat.simplegameengine.entity.Player;
import io.github.softwarecat.simplegameengine.input.InputPolling;
import io.github.softwarecat.simplegameengine.render.Renderer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    public Canvas gameCanvas;
    public AnchorPane gameAnchor;

    private final Entity player = new Player();

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        initialiseCanvas();

        Renderer renderer = new Renderer(this.gameCanvas);
        renderer.addEntity(player);

        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float deltaTime) {
                renderer.prepare();
                InputPolling.getInstance().tick();
                renderer.render(deltaTime);
            }
        };
        timer.start();
    }

    private void initialiseCanvas() {
        gameCanvas.widthProperty().bind(gameAnchor.widthProperty());
        gameCanvas.heightProperty().bind(gameAnchor.heightProperty());
    }
}
