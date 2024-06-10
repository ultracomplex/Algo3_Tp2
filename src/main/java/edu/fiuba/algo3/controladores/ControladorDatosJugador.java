package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.AlgoRoma.jugador.Jugador;
import edu.fiuba.algo3.modelo.observadores.ObservadorGladiador;
import edu.fiuba.algo3.modelo.observadores.ObservadorJugador;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ControladorDatosJugador implements ObservadorJugador, ObservadorGladiador {

    @FXML
    private Label equipamientoJugador;

    @FXML
    private Label nombreJugador;

    @FXML
    private Label seniorityJugador;

    @FXML
    private Label turnoJugador;

    @FXML
    private Label energiaJugador;

    private Jugador jugador;

    private Color colorJugador;

    public ControladorDatosJugador(Jugador jugador, Color colorJugador) {
        this.jugador = jugador;
        this.colorJugador = colorJugador;
    }

    public void initialize() {
        nombreJugador.setText(jugador.obtenerNombre());
        nombreJugador.setTextFill(colorJugador);
        turnoJugador.setText("1");
        energiaJugador.setText("20");
        seniorityJugador.setText("Novato");
        equipamientoJugador.setText("Nada");
        jugador.agregarObservador(this);
    }

    @Override
    public void actualizarDatosJugador(String turno) {
        this.turnoJugador.setText(turno);
    }

    @Override
    public void actualizarDatosGladiador(String energia, String equipamiento, String seniority) {
        this.energiaJugador.setText(energia);
        this.equipamientoJugador.setText(equipamiento);
        this.seniorityJugador.setText(seniority);
    }
}
