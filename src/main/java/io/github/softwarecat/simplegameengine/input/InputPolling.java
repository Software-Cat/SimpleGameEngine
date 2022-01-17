package io.github.softwarecat.simplegameengine.input;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class InputPolling {

    private static final InputPolling INSTANCE = new InputPolling();
    private static Scene scene;

    private static final List<InputEventHandler<?>> inputHandlers = new ArrayList<>();

    public static InputPolling getInstance() {
        return INSTANCE;
    }

    private InputPolling() {
    }

    public void addHandler(InputEventHandler<?> handler) {
        inputHandlers.add(handler);
    }

    public void pollScene(Scene scene) {
        removeCurrentKeyHandlers();
        setScene(scene);
    }

    public void tick() {
        for (InputEventHandler<?> handler : inputHandlers) {
            handler.tick();
        }
    }

    private void removeCurrentKeyHandlers() {
        if (scene != null) {
            InputPolling.scene.setOnKeyPressed(null);
            InputPolling.scene.setOnKeyReleased(null);
        }
    }

    private void setScene(Scene scene) {
        InputPolling.scene = scene;
        InputPolling.scene.setOnKeyPressed(InputHandler.getInstance());
        InputPolling.scene.setOnKeyReleased(InputHandler.getInstance());
    }

    private static class InputHandler implements EventHandler<KeyEvent> {

        private static final InputHandler INSTANCE = new InputHandler();

        private InputHandler() {
        }

        public static InputHandler getInstance() {
            return INSTANCE;
        }

        @Override
        public void handle(KeyEvent keyEvent) {
            for (InputEventHandler<?> handler : inputHandlers) {
                handler.handle(keyEvent);
            }
        }
    }
}
