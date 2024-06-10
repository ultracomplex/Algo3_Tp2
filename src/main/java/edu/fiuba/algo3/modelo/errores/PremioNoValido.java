package edu.fiuba.algo3.modelo.errores;

public class PremioNoValido extends RuntimeException{
    public PremioNoValido(String mensaje) {
        super(mensaje);
    }
}
