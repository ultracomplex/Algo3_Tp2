package edu.fiuba.algo3.modelo.obstaculo;

import edu.fiuba.algo3.modelo.AlgoRoma.Gladiador;
import edu.fiuba.algo3.modelo.AlgoRoma.Logger;

public class FieraSalvaje implements Obstaculo{
    public void afectar(Gladiador gladiador) {
        Logger.info("El Gladiador fue atacado por una fiera salvaje");
        gladiador.recibirAtaque(20);
    }

    @Override
    public String obtenerImagen() {
        return "file:src/main/resources/sprites/fiera.png";
    }
}
