package edu.fiuba.algo3.controladores;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorVistaFinal {
    @FXML
    private Button botonCerrar;

    @FXML
    private Button botonInicio;

    @FXML
    void irAInicio(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistaInicio.fxml"));
        Stage stage = (Stage) botonInicio.getScene().getWindow();

        Parent root = loader.load();
        stage.setTitle("Inicio");
        stage.setScene(new Scene(root));
    }

    @FXML
    void cerrarJuego(ActionEvent event) {
        Stage stage = (Stage) botonInicio.getScene().getWindow();
        stage.close();
    }
}
