package io.github.softwarecat.simplegameengine.entity;

import io.github.softwarecat.simplegameengine.input.GameInputs;
import io.github.softwarecat.simplegameengine.math.Vector2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player extends Entity {

    public float moveSpeed = 100f;
    public float rotSpeed = 90f;

    private Vector2 moveInput = Vector2.ZERO;
    private float rotInput = 0f;

    @Override
    public void start() {
        transform.scale = new Vector2(1, 2);

        GameInputs.LEFT.onStart().add(() -> moveInput = moveInput.add(-1, 0));
        GameInputs.LEFT.onCancel().add(() -> moveInput = moveInput.add(1, 0));

        GameInputs.RIGHT.onStart().add(() -> moveInput = moveInput.add(1, 0));
        GameInputs.RIGHT.onCancel().add(() -> moveInput = moveInput.add(-1, 0));

        GameInputs.UP.onStart().add(() -> moveInput = moveInput.add(0, -1));
        GameInputs.UP.onCancel().add(() -> moveInput = moveInput.add(0, 1));

        GameInputs.DOWN.onStart().add(() -> moveInput = moveInput.add(0, 1));
        GameInputs.DOWN.onCancel().add(() -> moveInput = moveInput.add(0, -1));

        GameInputs.ROT_LEFT.onStart().add(() -> rotInput -= 1);
        GameInputs.ROT_LEFT.onCancel().add(() -> rotInput += 1);

        GameInputs.ROT_RIGHT.onStart().add(() -> rotInput += 1);
        GameInputs.ROT_RIGHT.onCancel().add(() -> rotInput -= 1);
    }

    @Override
    public void update(float deltaTime) {
        Vector2 movement = moveInput.normalize().multiply(moveSpeed).multiply(deltaTime);
        transform.position = transform.position.add(movement);

        float rotation = rotInput * rotSpeed * deltaTime;
        transform.rotation += rotation;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.fillOval(0, 0, 100, 100);
    }
}
