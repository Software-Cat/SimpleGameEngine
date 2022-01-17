module io.github.softwarecat.snake {
    requires javafx.controls;
    requires javafx.fxml;


    opens io.github.softwarecat.snake to javafx.fxml;
    exports io.github.softwarecat.snake;
}
