package edu.fiuba.algo3.modelo.seniority;

public class SemiSenior implements Seniority {

    public int bonificarEnergia() {
        return 5; // Bonifica 5 de energía.
    }

    public Seniority sumarTurno(int turno) {
        if (turno >= 12) return new Senior();
        return this;
    }

    @Override
    public String obtenerNombre() {
        return "SemiSenior";
    }
}
