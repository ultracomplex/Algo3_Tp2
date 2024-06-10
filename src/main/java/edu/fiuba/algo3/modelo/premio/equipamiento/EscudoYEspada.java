package edu.fiuba.algo3.modelo.premio.equipamiento;

public class EscudoYEspada extends Equipamiento {

    public EscudoYEspada(){
        super(18);
    }

    public Equipamiento mejorar() {
        return new Llave();
    }

    public int proteger() {
        return this.defensa; // Protege 18 de daño.
    }

    public boolean esLlave(){
        return false;
    }

    @Override
    public String obtenerNombre() {
        return "Escudo y Espada";
    }
}
