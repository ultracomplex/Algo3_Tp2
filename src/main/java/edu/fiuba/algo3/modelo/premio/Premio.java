package edu.fiuba.algo3.modelo.premio;

import edu.fiuba.algo3.modelo.AlgoRoma.Gladiador;

public interface Premio {
    void premiar(Gladiador gladiador);

    String obtenerImagen();
}
