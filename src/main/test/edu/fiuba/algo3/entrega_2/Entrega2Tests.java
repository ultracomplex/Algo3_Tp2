package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.AlgoRoma.*;
import edu.fiuba.algo3.modelo.AlgoRoma.jugador.Jugador;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.fiuba.algo3.modelo.dados.Dado;
import edu.fiuba.algo3.modelo.dados.DadoPosta;
import edu.fiuba.algo3.modelo.dados.DadoTrucado;
import edu.fiuba.algo3.modelo.errores.EnemigoNoValido;
import edu.fiuba.algo3.modelo.errores.PremioNoValido;
import edu.fiuba.algo3.modelo.premio.equipamiento.Nada;
import edu.fiuba.algo3.modelo.seniority.Novato;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class Entrega2Tests {
    @Test
    public void test13_verificarFormatoTableroValido() throws IOException, ParseException, EnemigoNoValido, PremioNoValido, EnemigoNoValido, PremioNoValido, ParseException {
        // Arrange
        String pathTablero = "src/main/java/edu/fiuba/algo3/resources/mapa.json";
        Tablero tablero = new Tablero(pathTablero);

        // Act and assert
        assertEquals(39, tablero.cantidadDeCasillas());
    }

    @Test
    public void test14verificarFormatoValidoDeObstaculosYPremios() throws IOException, ParseException, EnemigoNoValido, PremioNoValido { //-------------------------------------
        String pathTablero = "src/main/java/edu/fiuba/algo3/resources/mapa.json";
        Dado dadoTrucado = new DadoTrucado(9);
        Tablero tablero = new Tablero(pathTablero);
        Jugador jugador = new Jugador("Jugador", tablero.salida());
        Gladiador gladiador = jugador.obtenerGladiador();

        jugador.jugarTurno(dadoTrucado, tablero);//Cae con fiera y equipamiento

        assertTrue(gladiador.puedeMoverse());

        gladiador.recibirDanio(5);

        assertFalse(gladiador.puedeMoverse());
    }
    @Test
    public void test15verificarLecturaYConversionDeEnemigos() throws IOException, ParseException, EnemigoNoValido, PremioNoValido {
        String pathTablero = "src/main/java/edu/fiuba/algo3/resources/mapa.json";
        Tablero tablero = new Tablero(pathTablero);
        Jugador jugador = new Jugador("nicolas", tablero.salida());
        Gladiador gladiador = jugador.obtenerGladiador();
        Dado dado = new DadoTrucado(4);

        jugador.jugarTurno(dado, tablero);//Jugador cae en fiera

        assertFalse(gladiador.puedeMoverse());
    }
    @Test
    public void test16verificarLecturaYConversionDeMapa() throws IOException, ParseException, EnemigoNoValido, PremioNoValido {
        String pathTablero = "src/main/java/edu/fiuba/algo3/resources/mapa.json";
        Dado dadoTrucado = new DadoTrucado(9);
        Tablero tablero = new Tablero(pathTablero);
        Jugador jugador = new Jugador("Jugador", tablero.salida());
        Gladiador gladiador = jugador.obtenerGladiador();

        jugador.jugarTurno(dadoTrucado, tablero);//Cae con fiera y equipamiento

        assertTrue(gladiador.puedeMoverse());

        gladiador.recibirDanio(5);

        assertFalse(gladiador.puedeMoverse());
    }

    @Test
    public void test17verificaQueJsonSeIgualTablero() throws IOException, ParseException, EnemigoNoValido, PremioNoValido {
        String pathTablero = "src/main/java/edu/fiuba/algo3/resources/mapa.json";
        Tablero tablero = new Tablero(pathTablero);
        Jugador jugador = new Jugador("Jugador", tablero.salida());

        Casilla casilla1= tablero.moverJugador(jugador,1);
        System.out.println("-------------------------------------------------!"+casilla1);

        //assertEquals(5, gladiador.obtenerEnergia());
    }

}