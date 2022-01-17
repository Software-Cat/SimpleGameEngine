package io.github.softwarecat.simplegameengine.input;

import javafx.scene.input.KeyEvent;

import java.util.function.Consumer;

/**
 * A 1D input.
 * <p>
 * Note: only register the topmost level of input to InputPolling. E.g., if you have two actions that link to an axis,
 * only register the axis, do not register or assign any callbacks to the actions.
 */
public class InputAxis extends InputEventHandler<Float> {

    private InputAction positiveAction;
    private InputAction negativeAction;

    private float axisValue = 0f;

    public InputAxis(InputAction positiveAction, InputAction negativeAction) {
        this.positiveAction = positiveAction;
        this.negativeAction = negativeAction;

        positiveAction.onStart.add((CallbackContext<Void> cxt) -> axisValue += 1);
        positiveAction.onCancel.add((CallbackContext<Void> cxt) -> axisValue -= 1);

        negativeAction.onStart.add((CallbackContext<Void> cxt) -> axisValue -= 1);
        negativeAction.onCancel.add((CallbackContext<Void> cxt) -> axisValue += 1);
    }

    // TODO: Add support for onStart and onCancel here. On start is called when either one of the actions are activated.
    // TODO: It is not called again until after both actions deactivate. On cancel is called when both actions become
    // TODO: deactivated.
    @Override
    public void handle(KeyEvent event) {
        positiveAction.handle(event);
        negativeAction.handle(event);
    }

    // TODO: Fix on perform so it updates every tick.
    @Override
    public void tick() {
        if (axisValue != 0) {
            for (Consumer<CallbackContext<Float>> action : onPerform) {
                action.accept(CallbackContext.of(axisValue));
            }
        }
    }

    public InputAction getPositiveAction() {
        return positiveAction;
    }

    public void setPositiveAction(InputAction positiveAction) {
        this.positiveAction = positiveAction;
    }

    public InputAction getNegativeAction() {
        return negativeAction;
    }

    public void setNegativeAction(InputAction negativeAction) {
        this.negativeAction = negativeAction;
    }
}
