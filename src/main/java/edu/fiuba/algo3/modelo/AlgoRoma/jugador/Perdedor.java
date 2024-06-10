package edu.fiuba.algo3.modelo.AlgoRoma.jugador;

public class Perdedor implements EstadoJugador {
    public boolean esGanador(){ return false; }
    public boolean esPerdedor(){ return true; }
    public boolean enPartida(){ return false; }
}
