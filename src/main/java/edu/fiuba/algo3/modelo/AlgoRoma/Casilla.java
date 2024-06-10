package edu.fiuba.algo3.modelo.AlgoRoma;

import edu.fiuba.algo3.modelo.obstaculo.Obstaculo;
import edu.fiuba.algo3.modelo.premio.Premio;

public class Casilla {
    private Obstaculo obstaculo;
    private Premio premio;
    public int x;
    public int y;


    public Casilla(int x, int y, Obstaculo obstaculo, Premio premio){
        this.x = x;
        this.y = y;
        this.obstaculo = obstaculo;
        this.premio = premio;
    }

    public void activarCasilla(Gladiador gladiador){
        if(this.premio != null){
            Logger.info("El jugador cayo en una casilla con un premio");
            premio.premiar(gladiador);
        }
        if(this.obstaculo != null){
            Logger.info("El jugador cayo en una casilla con un obstaculo");
            obstaculo.afectar(gladiador);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Casilla)) {
            return false;
        }
        Casilla casilla = (Casilla) o;
        return x == casilla.x && y == casilla.y;
    }

    public boolean esIgualA(Casilla casilla){
        return casilla.x == this.x && casilla.y == this.y;
    }

    public String obtenerImagen() {
        if (this.obstaculo != null) {
            return this.obstaculo.obtenerImagen();
        } else if (this.premio != null) {
            return this.premio.obtenerImagen();
        } else {
            return "file:src/main/resources/sprites/fondo_normal.png";
        }
    }
}
