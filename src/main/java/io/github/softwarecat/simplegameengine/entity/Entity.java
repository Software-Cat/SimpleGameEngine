package io.github.softwarecat.simplegameengine.entity;

import io.github.softwarecat.simplegameengine.math.Transform;
import io.github.softwarecat.simplegameengine.math.TransformBuilder;
import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {

    public Transform transform = new TransformBuilder().createTransform();

    public void start() {
    }

    public void update(float deltaTime) {
    }

    public abstract void draw(GraphicsContext context);
}
