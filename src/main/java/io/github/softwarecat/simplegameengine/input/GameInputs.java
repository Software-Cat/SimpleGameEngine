package io.github.softwarecat.simplegameengine.input;

import javafx.scene.input.KeyCode;

public class GameInputs {

    private static final InputAction UP = new InputAction(KeyCode.W);
    private static final InputAction DOWN = new InputAction(KeyCode.S);
    public static final InputAxis UP_DOWN = new InputAxis(UP, DOWN);

    public static final InputAction LEFT = new InputAction(KeyCode.A);
    public static final InputAction RIGHT = new InputAction(KeyCode.D);

    static {
        InputPolling.getInstance().addHandler(UP_DOWN);
    }
}
