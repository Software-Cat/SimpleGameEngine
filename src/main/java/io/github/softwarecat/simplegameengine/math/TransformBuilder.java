package io.github.softwarecat.simplegameengine.math;

public class TransformBuilder {

    private Vector2 position = new Vector2(0, 0);
    private float rotation = 0f;
    private Vector2 scale = new Vector2(1, 1);

    public TransformBuilder setPosition(Vector2 position) {
        this.position = position;
        return this;
    }

    public TransformBuilder setRotation(float rotation) {
        this.rotation = rotation;
        return this;
    }

    public TransformBuilder setScale(Vector2 scale) {
        this.scale = scale;
        return this;
    }

    public Transform createTransform() {
        return new Transform(position, rotation, scale);
    }
}
