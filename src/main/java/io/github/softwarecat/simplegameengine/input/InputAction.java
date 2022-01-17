package io.github.softwarecat.simplegameengine.input;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.function.Consumer;

public class InputAction extends InputEventHandler<Void> {

    private KeyCode keyBinding;

    private boolean keyDown = false;

    public InputAction(KeyCode keyBinding) {
        this.keyBinding = keyBinding;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().equals(keyBinding)) {
            if (!keyDown && KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
                keyDown = true;

                for (Consumer<CallbackContext<Void>> action : onStart) {
                    action.accept(CallbackContext.of(null));
                }
            } else if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
                keyDown = false;

                for (Consumer<CallbackContext<Void>> action : onCancel) {
                    action.accept(CallbackContext.of(null));
                }
            }
        }
    }

    public void tick() {
        if (keyDown) {
            for (Consumer<CallbackContext<Void>> action : onPerform) {
                action.accept(null);
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
