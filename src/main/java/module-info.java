module edu.fiuba.algo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    requires json.simple;
    exports edu.fiuba.algo3;
    exports edu.fiuba.algo3.controladores to javafx.fxml;
    opens edu.fiuba.algo3.controladores to javafx.fxml;
}