package io.github.softwarecat.simplegameengine.entity;

import io.github.softwarecat.simplegameengine.input.GameInputs;
import io.github.softwarecat.simplegameengine.input.InputEventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player extends Entity {

    @Override
    public void start() {
        GameInputs.UP_DOWN.onPerform().add((InputEventHandler.CallbackContext<Float> cxt) -> System.out.println(cxt.readAsFloat()));
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.fillOval(0, 0, 100, 100);
    }
}
