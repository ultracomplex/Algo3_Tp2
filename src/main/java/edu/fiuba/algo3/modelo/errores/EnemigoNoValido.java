package edu.fiuba.algo3.modelo.errores;

public class EnemigoNoValido extends RuntimeException{
    public EnemigoNoValido(String mensaje) {
        super(mensaje);
    }
}
