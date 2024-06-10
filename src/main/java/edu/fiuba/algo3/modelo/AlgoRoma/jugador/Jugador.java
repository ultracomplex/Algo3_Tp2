package edu.fiuba.algo3.modelo.AlgoRoma.jugador;

import edu.fiuba.algo3.modelo.AlgoRoma.Casilla;
import edu.fiuba.algo3.modelo.AlgoRoma.Gladiador;
import edu.fiuba.algo3.modelo.AlgoRoma.Logger;
import edu.fiuba.algo3.modelo.AlgoRoma.Tablero;
import edu.fiuba.algo3.modelo.dados.Dado;
import edu.fiuba.algo3.modelo.errores.NombreInvalidoException;
import edu.fiuba.algo3.modelo.observadores.ObservadorGladiador;
import edu.fiuba.algo3.modelo.observadores.ObservadorJugador;
import edu.fiuba.algo3.modelo.premio.equipamiento.Nada;
import edu.fiuba.algo3.modelo.seniority.Novato;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private final String nombre;

    private final Gladiador gladiador;

    private Casilla posicion;

    private List<ObservadorJugador> observadores = new ArrayList<>();

    private int turno;
    
    private EstadoJugador estado;

    public Jugador(String nombre, Casilla inicio) {
        verificarNombre(nombre);
        this.nombre = nombre;
        this.gladiador = new Gladiador(20, new Nada(), new Novato());
        this.posicion = inicio;
        this.turno = 0;
        this.estado = new Vivo();
    }

    private void verificarNombre (String nombre) {
        if (nombre.length() < 4) {
            throw new NombreInvalidoException();
        }
    }

    public void jugarTurno(Dado dado, Tablero tablero) {
        if (this.gladiador.puedeMoverse() && this.estado.enPartida()) {
            this.turno++;
            this.gladiador.pasarTurno(turno);
            int posicionesAvanzadas = dado.tirarDado();
            Logger.info("El jugador " + this.nombre + " tiro el dado, y avanzo "+ String.valueOf(posicionesAvanzadas)+" posiciones ");
            Logger.info("Turno: " + String.valueOf(this.turno));
            this.posicion = tablero.moverJugador(this, posicionesAvanzadas);

            if (this.posicion.esIgualA(tablero.llegada())) {
                this.estado = new Ganador();
                Logger.info("El jugador " + this.nombre + " ha ganado la partida.");
            }
            else if (this.turno > 30) {
                this.estado = new Perdedor();
                Logger.info("El jugador " + this.nombre + " ha perdido la partida.");
            }
        }
        notificarObservadores();
    }
    public EstadoJugador obtenerEstado(){
        return this.estado;
    }

    public boolean tieneLlave(){
        return this.gladiador.tieneLlave();
    }


    public Gladiador obtenerGladiador() {
        return this.gladiador;
    }

    public Casilla obtenerPosicion() {
        return this.posicion;
    }

    public int obtenerTurno() {
        return this.turno;
    }

    public String obtenerNombre(){
        return this.nombre;
    }

    public void agregarObservador(ObservadorJugador observador){
        this.observadores.add(observador);
        this.gladiador.agregarObservador((ObservadorGladiador) observador);
    }

    public void notificarObservadores(){
        for (ObservadorJugador observador : this.observadores){
            observador.actualizarDatosJugador(Integer.toString(turno));
        }
    }

}
