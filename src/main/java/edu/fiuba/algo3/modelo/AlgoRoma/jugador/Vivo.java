package edu.fiuba.algo3.modelo.AlgoRoma.jugador;

public class Vivo implements EstadoJugador {
    public boolean esGanador(){ return false; }
    public boolean esPerdedor(){ return false; }
    public boolean enPartida(){ return true; }
}
