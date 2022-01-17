package io.github.softwarecat.simplegameengine.math;

public class Transform {

    public Vector2 position;

    public float rotation;

    public Vector2 scale;

    protected Transform(Vector2 position, float rotation, Vector2 scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }
}
