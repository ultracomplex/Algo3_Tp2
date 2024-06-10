package edu.fiuba.algo3.modelo.premio.equipamiento;

public class Nada extends Equipamiento {

    public Nada(){
        super(0);
    }

    public Equipamiento mejorar() {
        return new Casco();
    }

    public int proteger() {
        return this.defensa; // Protege 0 daño.
    }

    public boolean esLlave(){
        return false;
    }

    @Override
    public String obtenerNombre() {
        return "Nada";
    }
}
