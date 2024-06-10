package edu.fiuba.algo3.modelo.dados;

public class DadoPosta implements Dado{
    public int tirarDado() {
        int min = 1;
        int max = 6;
        return (int)Math.floor(Math.random() * (max - min + 1) + min);
    }
}
