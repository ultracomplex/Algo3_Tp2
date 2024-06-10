package edu.fiuba.algo3.modelo.obstaculo;

import edu.fiuba.algo3.modelo.AlgoRoma.Gladiador;

public class Lesion implements Obstaculo {
    @Override
    public void afectar(Gladiador gladiador){
        gladiador.lesionar();
    }

    @Override
    public String obtenerImagen() {
        return "file:src/main/resources/sprites/lesion.png";
    }
}
