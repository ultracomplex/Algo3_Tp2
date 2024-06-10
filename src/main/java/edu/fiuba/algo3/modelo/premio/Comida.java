package edu.fiuba.algo3.modelo.premio;

import edu.fiuba.algo3.modelo.AlgoRoma.Gladiador;

public class Comida implements Premio {
    public void premiar(Gladiador gladiador){
        gladiador.recibirComida();
    }

    @Override
    public String obtenerImagen() {
        return "file:src/main/resources/sprites/casilla_comida.png";
    }

}
