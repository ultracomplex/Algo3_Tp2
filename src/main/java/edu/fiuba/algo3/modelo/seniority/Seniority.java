package edu.fiuba.algo3.modelo.seniority;

public interface Seniority {

    int bonificarEnergia();

    Seniority sumarTurno(int turno);

    String obtenerNombre();
}