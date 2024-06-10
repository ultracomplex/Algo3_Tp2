package edu.fiuba.algo3.modelo.dados;

public class DadoTrucado implements Dado{

    private int valor;
    public DadoTrucado(int valor) {
        this.valor = valor;
    }

    public int tirarDado() {
        return this.valor;
    }
}
