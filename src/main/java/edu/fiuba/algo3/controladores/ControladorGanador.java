package edu.fiuba.algo3.controladores;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControladorGanador {

    @FXML
    private Button inicio;

    @FXML
    private Label nombreGanador;

    private String nombre;

    public ControladorGanador(String nombreGanador){
        this.nombre = nombreGanador;
    }

    public void initialize() {
        nombreGanador.setText(nombre);
    }


    @FXML
    void botonInicio(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistaInicio.fxml"));
        Stage stage = (Stage) inicio.getScene().getWindow();

        Parent root = loader.load();
        stage.setTitle("Inicio");
        stage.setScene(new Scene(root));
    }
}
