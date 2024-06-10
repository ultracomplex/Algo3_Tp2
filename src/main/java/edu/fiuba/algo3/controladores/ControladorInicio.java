package edu.fiuba.algo3.controladores;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorInicio {

    public ControladorJuego controladorJuego;

    @FXML protected AnchorPane anchorPane;

    @FXML public Button botonIniciar;
    private Stage stage;
    private Scene scene;

    @FXML private Event eventCambiarEscena;

    public ControladorInicio() throws Exception {
        this.controladorJuego = new ControladorJuego();
    }

    public void inicializar(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }

    @FXML
    public void creacionJugadores() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistaCreacionDeJugadores.fxml"));

        Stage stage = (Stage) botonIniciar.getScene().getWindow();

        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }
}
