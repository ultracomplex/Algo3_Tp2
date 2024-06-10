package edu.fiuba.algo3.modelo.premio.equipamiento;

public class Casco extends Equipamiento {

    public Casco(){
        super(5);
    }

    public Equipamiento mejorar() {
        return new Armadura();
    }

    public int proteger() {
        return this.defensa; // Protege 5 de daño.
    }

    public boolean esLlave(){
        return false;
    }

    @Override
    public String obtenerNombre() {
        return "Casco";
    }
}
