package io.github.softwarecat.simplegameengine.input;

import javafx.scene.input.KeyCode;

public class GameInputs {

    public static final InputAction UP = new InputAction(KeyCode.W);
    public static final InputAction DOWN = new InputAction(KeyCode.S);
    public static final InputAction LEFT = new InputAction(KeyCode.A);
    public static final InputAction RIGHT = new InputAction(KeyCode.D);
    public static final InputAction ROT_LEFT = new InputAction(KeyCode.Q);
    public static final InputAction ROT_RIGHT = new InputAction(KeyCode.E);
}
