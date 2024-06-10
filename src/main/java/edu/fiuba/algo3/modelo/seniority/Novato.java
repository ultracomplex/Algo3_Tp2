package edu.fiuba.algo3.modelo.seniority;

public class Novato implements Seniority {

    public int bonificarEnergia() {
        return 0; // Bonifica 0 energía.
    }

    public Seniority sumarTurno(int turno) {
        if (turno >= 8) return new SemiSenior();
        return this;
    }

    @Override
    public String obtenerNombre() {
        return "Novato";
    }
}
