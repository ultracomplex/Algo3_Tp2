package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.AlgoRoma.*;
import edu.fiuba.algo3.modelo.AlgoRoma.jugador.Jugador;
import edu.fiuba.algo3.modelo.dados.Dado;
import edu.fiuba.algo3.modelo.dados.DadoPosta;
import edu.fiuba.algo3.modelo.dados.DadoTrucado;
import edu.fiuba.algo3.modelo.errores.EnemigoNoValido;
import edu.fiuba.algo3.modelo.errores.PremioNoValido;
import edu.fiuba.algo3.modelo.obstaculo.FieraSalvaje;
import edu.fiuba.algo3.modelo.premio.equipamiento.Nada;
import edu.fiuba.algo3.modelo.seniority.Novato;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class entrega1Tests {

    @Test
    public void test01jugadorEmpiezaConEnergiayEquipamientoCorrespondiente() {
        Gladiador gladiador = new Gladiador(20, new Nada(), new Novato());
        FieraSalvaje fiera = new FieraSalvaje();

        fiera.afectar(gladiador);

        assertFalse(gladiador.puedeMoverse());
    }

    @Test
    public void test02jugadorSaleDeCasillaCorrectamente() throws IOException, ParseException, EnemigoNoValido, PremioNoValido {
        Tablero tablero = new Tablero("src/main/java/edu/fiuba/algo3/resources/mapa.json");
        Jugador jugador = new Jugador("Jugador", tablero.salida());
        DadoTrucado dado = new DadoTrucado(1);

        jugador.jugarTurno(dado, tablero);

        assertFalse(jugador.obtenerPosicion() == tablero.salida());
    }

    @Test
    public void test03jugadorSinEnergiaPierdeTurno() throws IOException, ParseException, EnemigoNoValido, PremioNoValido {
        Tablero tablero = new Tablero("src/main/java/edu/fiuba/algo3/resources/mapa.json");
        Jugador jugador = new Jugador("Jugador", tablero.salida());
        Gladiador gladiador = jugador.obtenerGladiador();
        Dado dado = new DadoPosta();

        gladiador.recibirDanio(20);
        jugador.jugarTurno(dado, tablero);

        assertTrue(jugador.obtenerPosicion() == tablero.salida());
    }

    @Test
    public void test04jugadorIncrementaEnergiaAlRecibirComida(){
        Gladiador gladiador = new Gladiador(20, new Nada(), new Novato());

        gladiador.recibirComida();
        gladiador.recibirAtaque(20);

        assertTrue(gladiador.puedeMoverse());
    }

    @Test
    public void test05jugadorRecibePrimerPremioYsuEquipamientoEsActualizadoACasco(){
        Nada equipamiento = new Nada();
        Gladiador gladiador = new Gladiador(20, equipamiento, new Novato());
        FieraSalvaje fiera = new FieraSalvaje();

        equipamiento.premiar(gladiador);
        fiera.afectar(gladiador);

        assertTrue(gladiador.puedeMoverse());
    }

    @Test
    public void test06jugadorRecibePremio3vecesYRecibeEscudoYEspada() {
        Nada equipamiento = new Nada();
        Gladiador gladiador = new Gladiador(20, equipamiento, new Novato());
        FieraSalvaje fiera = new FieraSalvaje();

        equipamiento.premiar(gladiador);
        equipamiento.premiar(gladiador);
        equipamiento.premiar(gladiador);
        fiera.afectar(gladiador);

        assertTrue(gladiador.puedeMoverse());

        gladiador.recibirDanio(18);

        assertFalse(gladiador.puedeMoverse());
    }

    @Test
    public void test07jugadorRecibeAtaqueDeFieraTeniendoCascoYPierde15PuntosDeEnergia(){
        Nada equipamiento = new Nada();
        Gladiador gladiador = new Gladiador(20, equipamiento, new Novato());
        FieraSalvaje fiera = new FieraSalvaje();

        equipamiento.premiar(gladiador);
        fiera.afectar(gladiador);

        assertTrue(gladiador.puedeMoverse());

        gladiador.recibirDanio(15);

        assertFalse(gladiador.puedeMoverse());
    }

    @Test
    public void test08jugadorJuega8TurnosYsuSeniorityAumentaASemiSenior() throws IOException, ParseException, EnemigoNoValido, PremioNoValido {
        Tablero tablero = new Tablero("src/main/java/edu/fiuba/algo3/resources/mapa.json");
        AlgoRoma algoRoma = new AlgoRoma(tablero, new DadoTrucado(0));
        Jugador jugador1 = new Jugador("Rama", tablero.salida());
        Jugador jugador2 = new Jugador("Juan", tablero.salida());
        int contador = 0;

        while (contador<8){
            algoRoma.jugarTurno(jugador1);

            contador++;
        }//aca cambia su seniority

        algoRoma.jugarTurno(jugador1);
        algoRoma.jugarTurno(jugador2);
        algoRoma.jugarTurno(jugador1);

        assertTrue(jugador1.obtenerGladiador().puedeMoverse());
    }

    @Test
    public void test09jugadorLlegaAMetaSinLlaveYRetrocedeALaMitad() throws IOException, ParseException, EnemigoNoValido, PremioNoValido {
        Tablero tablero = new Tablero("src/main/java/edu/fiuba/algo3/resources/mapa.json");
        Jugador jugador = new Jugador("Tito", tablero.salida());
        int casillaLlegada = tablero.buscarNumeroCasilla(tablero.llegada());
        Dado dado = new DadoTrucado(casillaLlegada);

        jugador.jugarTurno(dado, tablero);

        assertEquals(casillaLlegada/2, tablero.buscarNumeroCasilla(jugador.obtenerPosicion()));
    }

    @Test
    public void test10jugadorEsAtacadoPorFieraConEquipamientoDeLlaveYNoRecibeDanio(){
        Nada equipamiento = new Nada();
        Gladiador gladiador = new Gladiador(10, equipamiento, new Novato());
        FieraSalvaje fiera = new FieraSalvaje();

        equipamiento.premiar(gladiador);
        equipamiento.premiar(gladiador);
        equipamiento.premiar(gladiador);
        equipamiento.premiar(gladiador);
        fiera.afectar(gladiador);

        assertTrue(gladiador.puedeMoverse());

        gladiador.recibirDanio(20);

        assertFalse(gladiador.puedeMoverse());
    }

    @Test
    public void test11jugadorTieneLLaveYRecibePremioNoDeberiaCambiar() throws IOException, ParseException, EnemigoNoValido, PremioNoValido
    {
        String pathTablero = "src/main/java/edu/fiuba/algo3/resources/mapa.json";
        Dado dadoTrucado1 = new DadoTrucado(1);
        Dado dadoTrucado2 = new DadoTrucado(14);
        Dado dadoTrucado3 = new DadoTrucado(1);
        Dado dadoTrucado4 = new DadoTrucado(3);
        Dado dadoTrucado5 = new DadoTrucado(2);
        Tablero tablero = new Tablero(pathTablero);
        Jugador jugador = new Jugador("Jugador", tablero.salida());
        Gladiador gladiador = jugador.obtenerGladiador();

        jugador.jugarTurno(dadoTrucado1, tablero);
        jugador.jugarTurno(dadoTrucado2, tablero);
        jugador.jugarTurno(dadoTrucado3, tablero);
        jugador.jugarTurno(dadoTrucado4, tablero);

        assertTrue(gladiador.tieneLlave());

        jugador.jugarTurno(dadoTrucado5, tablero); //nuevo premio

        assertTrue(gladiador.tieneLlave());

    }

    @Test
    public void test12pasanTreintaTurnosNadieLLegaALaMetaSeTerminaElJuego() throws IOException, ParseException, EnemigoNoValido, PremioNoValido
    {
        Tablero tablero = new Tablero("src/main/java/edu/fiuba/algo3/resources/mapa.json");
        AlgoRoma algoRoma = new AlgoRoma(tablero, new DadoTrucado(0));
        Jugador jugador1 = new Jugador("Ramiro", tablero.salida());
        Jugador jugador2 = new Jugador("Tomas", tablero.salida());
        int contador = 0;
        while (contador <= 30) {
            algoRoma.jugarTurno(jugador1);
            algoRoma.jugarTurno(jugador2);

            contador++;
        }

        assertTrue(jugador1.obtenerEstado().esPerdedor() && jugador2.obtenerEstado().esPerdedor());
    }

}