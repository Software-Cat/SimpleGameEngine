package io.github.softwarecat.simplegameengine.input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class InputEventHandler<T> implements EventHandler<KeyEvent> {

    protected final List<Consumer<CallbackContext<T>>> onStart = new ArrayList<>();
    protected final List<Consumer<CallbackContext<T>>> onPerform = new ArrayList<>();
    protected final List<Consumer<CallbackContext<T>>> onCancel = new ArrayList<>();

    public InputEventHandler() {
    }

    public List<Consumer<CallbackContext<T>>> onStart() {
        return onStart;
    }

    public List<Consumer<CallbackContext<T>>> onPerform() {
        return onPerform;
    }

    public List<Consumer<CallbackContext<T>>> onCancel() {
        return onCancel;
    }

    public abstract void tick();

    public record CallbackContext<T>(T inputValue) {

        public static <T> CallbackContext<T> of(T inputValue) {
            return new CallbackContext<>(inputValue);
        }

        public <U> U readAs() {
            return (U) inputValue;
        }

        public float readAsFloat() {
            return readAs();
        }

        public float readAsVector2() {
            return readAs();
        }
    }
}
