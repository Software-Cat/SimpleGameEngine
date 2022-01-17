package io.github.softwarecat.simplegameengine.input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class InputAction implements EventHandler<KeyEvent> {

    private KeyCode keyBinding;

    private final List<Runnable> onStart = new ArrayList<>();

    private final List<Runnable> onPerform = new ArrayList<>();

    private final List<Runnable> onCancel = new ArrayList<>();

    private boolean keyDown = false;

    public InputAction(KeyCode keyBinding) {
        this.keyBinding = keyBinding;

        // Receive inputs from InputPolling
        InputPolling.getInstance().addAction(this);
    }

    public List<Runnable> onStart() {
        return onStart;
    }

    public List<Runnable> onPerform() {
        return onPerform;
    }

    public List<Runnable> onCancel() {
        return onCancel;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().equals(keyBinding)) {
            if (!keyDown && KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
                keyDown = true;

                for (Runnable action : onStart) {
                    action.run();
                }
            } else if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
                keyDown = false;

                for (Runnable action : onCancel) {
                    action.run();
                }
            }
        }
    }

    /**
     * If the {@link InputAction#keyBinding} is down in the current tick call the actions in {@link
     * InputAction#onPerform}.
     * <p>
     * This method is to be run every tick.
     */
    public void tick() {
        if (keyDown) {
            for (Runnable action : onPerform) {
                action.run();
            }
        }
    }

    public KeyCode getKeyBinding() {
        return keyBinding;
    }

    public void setKeyBinding(KeyCode keyBinding) {
        this.keyBinding = keyBinding;
    }
}
