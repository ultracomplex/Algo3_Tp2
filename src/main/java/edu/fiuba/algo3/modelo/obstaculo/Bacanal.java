package edu.fiuba.algo3.modelo.obstaculo;

import edu.fiuba.algo3.modelo.AlgoRoma.Logger;
import edu.fiuba.algo3.modelo.dados.Dado;
import edu.fiuba.algo3.modelo.AlgoRoma.Gladiador;
import edu.fiuba.algo3.modelo.dados.DadoPosta;

public class Bacanal implements Obstaculo {
    @Override
    public void afectar(Gladiador gladiador) {
        Dado dado = new DadoPosta();
        int tragos = dado.tirarDado();
        gladiador.recibirDanio(4*tragos);
        Logger.info("El Gladiador fue a un bacanal y recibio " + tragos + " tragos");
    }

    @Override
    public String obtenerImagen() {
        return "file:src/main/resources/sprites/bacanal.png";
    }
}
