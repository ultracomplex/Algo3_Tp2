package edu.fiuba.algo3.modelo.premio.equipamiento;

public class Armadura extends Equipamiento {

    public Armadura(){
        super(10);
    }

    public Equipamiento mejorar() {
        return new EscudoYEspada();
    }

    public int proteger() {
        return this.defensa; // Protege 10 de daño.
    }

    public boolean esLlave(){
        return false;
    }

    @Override
    public String obtenerNombre() {
        return "Armadura";
    }
}
