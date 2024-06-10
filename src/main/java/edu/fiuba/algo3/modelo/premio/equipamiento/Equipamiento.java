package edu.fiuba.algo3.modelo.premio.equipamiento;

import edu.fiuba.algo3.modelo.AlgoRoma.Gladiador;
import edu.fiuba.algo3.modelo.premio.Premio;

public abstract class Equipamiento implements Premio {
    protected int defensa;

    public Equipamiento(int defensa) {
        this.defensa = defensa;
    }

    public abstract Equipamiento mejorar();

    public abstract int proteger();

    public abstract boolean esLlave();

    public void premiar(Gladiador gladiador){
        gladiador.mejorarEquipamiento();
    }

    public String obtenerImagen() {
        return "file:src/main/resources/sprites/casilla_premio.png";
    }

    public abstract String obtenerNombre();
}
