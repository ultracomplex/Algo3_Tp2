package edu.fiuba.algo3.modelo.AlgoRoma;

import edu.fiuba.algo3.modelo.observadores.ObservadorGladiador;
import edu.fiuba.algo3.modelo.premio.equipamiento.Equipamiento;
import edu.fiuba.algo3.modelo.seniority.Seniority;

import java.util.ArrayList;
import java.util.List;

public class Gladiador {
    private int energia;
    private boolean lesionado;

    private Equipamiento equipamiento;

    public Seniority seniority;

    private List<ObservadorGladiador> observadores = new ArrayList<>();

    public Gladiador(int energiaInicial, Equipamiento equipamiento, Seniority seniority) {
        this.equipamiento = equipamiento;
        this.energia = energiaInicial;
        this.seniority = seniority;
        this.lesionado = false;
    }

    public void recibirDanio(int danio) {
        this.energia -= danio;
        Logger.info("El gladiador tiene " + this.energia + " de energia");
        notificarObservadores();
    };

    public void recibirAtaque(int danio) {
        int danioARecibir = danio - this.equipamiento.proteger();
        Logger.info("El gladiador ha recibido " + danioARecibir + " de daño");
        recibirDanio(danioARecibir);
    }

    public void recibirEnergia(int energia) {
        this.energia += energia;
        notificarObservadores();
    }

    public void recibirComida() {
        Logger.info("El gladiador ha recibido comida");
        recibirEnergia(15);
    }

    public void mejorarEquipamiento() {
        this.equipamiento = this.equipamiento.mejorar();
        Logger.info("El gladiador ha mejorado su equipamiento");
        notificarObservadores();
    }

    public void lesionar(){
        Logger.info("El Gladiador fue lesionado, pierde un turno");
        this.lesionado = true;
    }

    public boolean puedeMoverse() {
        if (this.energia <= 0) {
            recibirEnergia(5);
            Logger.info("El gladiador no puede moverse, pierde el turno y recibe 5 de energia");
            return false;
        }
        if (this.lesionado) {
            Logger.info("El gladiador no puede moverse, esta lesionado");
            this.lesionado = false;
            return false;
        }
        return true;
    }

    public void pasarTurno(int turno) {
        this.seniority = this.seniority.sumarTurno(turno);
        recibirEnergia(this.seniority.bonificarEnergia());
        notificarObservadores();
    }

    public boolean tieneLlave(){
        return this.equipamiento.esLlave();
    }

    public void agregarObservador(ObservadorGladiador observador) {
        this.observadores.add(observador);
    }

    public void notificarObservadores() {
        for (ObservadorGladiador observador : this.observadores) {
            observador.actualizarDatosGladiador(String.valueOf(this.energia), this.equipamiento.obtenerNombre(), this.seniority.obtenerNombre());
        }
    }
}
