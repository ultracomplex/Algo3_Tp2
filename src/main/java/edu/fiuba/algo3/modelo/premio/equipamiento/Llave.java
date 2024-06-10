package edu.fiuba.algo3.modelo.premio.equipamiento;

public class Llave extends Equipamiento {
    
    public Llave(){
        super(20);
    }

    public Equipamiento mejorar() {
        return this;
    }

    public int proteger() {
        return this.defensa; // Protege 20 de daño.
    }

    public boolean esLlave(){
        return true;
    }

    @Override
    public String obtenerNombre() {
        return "Llave";
    }
}
