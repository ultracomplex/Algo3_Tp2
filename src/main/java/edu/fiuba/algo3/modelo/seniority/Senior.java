package edu.fiuba.algo3.modelo.seniority;

public class Senior implements Seniority {

    public int bonificarEnergia() {
        return 10; // Bonifica 10 de energía.
    }

    public Seniority sumarTurno(int turno) {
        return this;
    }

    @Override
    public String obtenerNombre() {
        return "Senior";
    }
}
