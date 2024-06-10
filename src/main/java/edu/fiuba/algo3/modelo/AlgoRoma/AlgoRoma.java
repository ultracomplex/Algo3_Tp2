package edu.fiuba.algo3.modelo.AlgoRoma;

import edu.fiuba.algo3.modelo.AlgoRoma.jugador.Jugador;
import edu.fiuba.algo3.modelo.dados.Dado;

public class AlgoRoma {
    private Tablero tablero;
    private Dado dado;


    public AlgoRoma(Tablero tablero, Dado dado) {
        this.tablero = tablero;
        this.dado = dado;
    }

    public void jugarTurno(Jugador jugador){
        Logger.info("juega el jugador " + jugador.obtenerNombre());
        jugador.jugarTurno(this.dado, this.tablero);
    }
}
