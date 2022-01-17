module io.github.softwarecat.snake {
    requires javafx.controls;
    requires javafx.fxml;

    opens io.github.softwarecat.simplegameengine.controller to javafx.fxml;
    exports io.github.softwarecat.simplegameengine;
}
