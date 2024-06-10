package edu.fiuba.algo3.modelo.obstaculo;

import edu.fiuba.algo3.modelo.AlgoRoma.Gladiador;

public interface Obstaculo {
    void afectar(Gladiador gladiador);

    String obtenerImagen();
}
