package edu.fiuba.algo3.modelo.AlgoRoma.jugador;

public class Ganador implements EstadoJugador {
    public boolean esGanador(){ return true; }
    public boolean esPerdedor(){ return false; }
    public boolean enPartida(){ return false; }
}
