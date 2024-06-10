package edu.fiuba.algo3.pruebas_Unitarias;

import edu.fiuba.algo3.modelo.AlgoRoma.*;
import edu.fiuba.algo3.modelo.AlgoRoma.jugador.Jugador;
import edu.fiuba.algo3.modelo.dados.Dado;
import edu.fiuba.algo3.modelo.dados.DadoTrucado;
import edu.fiuba.algo3.modelo.errores.CantidadDeJugadoresInvalidaException;
import edu.fiuba.algo3.modelo.errores.EnemigoNoValido;
import edu.fiuba.algo3.modelo.errores.NombreInvalidoException;
import edu.fiuba.algo3.modelo.errores.PremioNoValido;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class TestsUnitarios {


    @Test
    public void test01_pruebaCreacionAlgoRoma() throws IOException, ParseException, EnemigoNoValido, PremioNoValido {
        Tablero tablero = new Tablero("src/main/java/edu/fiuba/algo3/resources/mapa.json");
        AlgoRoma algoRoma = new AlgoRoma(tablero, new DadoTrucado(0));
        Jugador jugador = new Jugador("Rama", tablero.salida());
        algoRoma.jugarTurno(jugador);
        assertEquals(jugador.obtenerTurno(),1);
    }

    @Test
    public void test02_pruebaAgregaJugadorAlgoRoma() throws IOException, ParseException, EnemigoNoValido, PremioNoValido {
        Tablero tablero = new Tablero("src/main/java/edu/fiuba/algo3/resources/mapa.json");
        AlgoRoma algoRoma = new AlgoRoma(tablero, new DadoTrucado(0));
        Jugador jugador = new Jugador("Rama", tablero.salida());
        try {
            algoRoma.jugarTurno(jugador);
        }
        catch (CantidadDeJugadoresInvalidaException e){
            System.out.println("No se puede empezar con Un solo jugador");
        }
    }

    @Test
    public void test04pruebaCrearCasillaConPremioEquipamiento()throws IOException, ParseException, EnemigoNoValido, PremioNoValido{
        String pathTablero = "src/main/java/edu/fiuba/algo3/resources/mapa.json";
        Dado dadoTrucado1 = new DadoTrucado(1);
        Dado dadoTrucado2 = new DadoTrucado(3);
        Tablero tablero = new Tablero(pathTablero);
        Jugador jugador = new Jugador("Jugador", tablero.salida());
        Gladiador gladiador = jugador.obtenerGladiador();

        jugador.jugarTurno(dadoTrucado1, tablero);
        jugador.jugarTurno(dadoTrucado2, tablero);

        assertTrue(gladiador.puedeMoverse());
        gladiador.recibirDanio(5);
        assertFalse(gladiador.puedeMoverse());
    }

    @Test
    public void test05pruebaCrearCasillaConPremioComida() throws IOException, ParseException, EnemigoNoValido, PremioNoValido{ //es el mismo que el test4 de la entrega 1
        String pathTablero = "src/main/java/edu/fiuba/algo3/resources/mapa.json";
        Dado dadoTrucado1 = new DadoTrucado(2);
        Tablero tablero = new Tablero(pathTablero);
        Jugador jugador = new Jugador("Jugador", tablero.salida());
        Gladiador gladiador = jugador.obtenerGladiador();

        jugador.jugarTurno(dadoTrucado1, tablero);

        gladiador.recibirDanio(34);
        assertTrue(gladiador.puedeMoverse());
        gladiador.recibirDanio(1);
        assertFalse(gladiador.puedeMoverse());
    }
    @Test
    public void test06jugadorCaeEnCasillaConFieraSalvaje() throws IOException, ParseException, EnemigoNoValido, PremioNoValido{ // es el mismo que el test05 de la entrega 1
        String pathTablero = "src/main/java/edu/fiuba/algo3/resources/mapa.json";
        Dado dadoTrucado1 = new DadoTrucado(4);
        Tablero tablero = new Tablero(pathTablero);
        Jugador jugador = new Jugador("Jugador", tablero.salida());
        Gladiador gladiador = jugador.obtenerGladiador();

        jugador.jugarTurno(dadoTrucado1, tablero);
        assertFalse(gladiador.puedeMoverse());
    }
    @Test
    public void  test07jugadorCaeEnCasillaConBacanal()  throws IOException, ParseException, EnemigoNoValido, PremioNoValido{
        String pathTablero = "src/main/java/edu/fiuba/algo3/resources/mapa.json";
        Dado dadoTrucado = new DadoTrucado(5);
        Tablero tablero = new Tablero(pathTablero);
        Jugador jugador = new Jugador("Jugador", tablero.salida());
        Gladiador gladiador = jugador.obtenerGladiador();

        jugador.jugarTurno(dadoTrucado, tablero);
        //como el dado de vacanal sale un numeor random no puedo poner un assert
    }


    @Test
    public void test08_prueaAlgoRomaEmpezarConMaDeSeisJugadores() throws Exception {
        Tablero tablero = new Tablero("src/main/java/edu/fiuba/algo3/resources/mapa.json");
        AlgoRoma algoRoma = new AlgoRoma(tablero, new DadoTrucado(0));
        Jugador jugador1 = new Jugador("Rama", tablero.salida());
        Jugador jugador2 = new Jugador("Rama", tablero.salida());
        Jugador jugador3 = new Jugador("Rama", tablero.salida());
        Jugador jugador4 = new Jugador("Rama", tablero.salida());
        Jugador jugador5 = new Jugador("Rama", tablero.salida());
        Jugador jugador6 = new Jugador("Rama", tablero.salida());
        Jugador jugador7 = new Jugador("Rama", tablero.salida());

        try {
            algoRoma.jugarTurno(jugador1);
        }
        catch (CantidadDeJugadoresInvalidaException e){
            System.out.print("No se puede empezar con 7 jugadores");
        }
    }

    @Test
    public void test09GladiadorGanaElJuego()throws IOException, ParseException, EnemigoNoValido, PremioNoValido{
        String pathTablero = "src/main/java/edu/fiuba/algo3/resources/mapa.json";
        Dado dadoTrucado1 = new DadoTrucado(1);
        Dado dadoTrucado2 = new DadoTrucado(14);
        Dado dadoTrucado3 = new DadoTrucado(1);
        Dado dadoTrucado4 = new DadoTrucado(3);
        Dado dadoTrucado5 = new DadoTrucado(20);
        Tablero tablero = new Tablero(pathTablero);
        Jugador jugador = new Jugador("Jugador", tablero.salida());
        Gladiador gladiador = jugador.obtenerGladiador();

        jugador.jugarTurno(dadoTrucado1, tablero);
        jugador.jugarTurno(dadoTrucado2, tablero);
        jugador.jugarTurno(dadoTrucado3, tablero);
        jugador.jugarTurno(dadoTrucado4, tablero);
        jugador.jugarTurno(dadoTrucado5, tablero); //lega a la meta con llave

        //assertTrue(tablero.CheckearGanador(jugador));

    }

    @Test
    public void test10GladiadorSeQuedaSinEnergiaYSeLeRecargaLaEnergiaACinco()throws IOException, ParseException, EnemigoNoValido, PremioNoValido{
        String pathTablero = "src/main/java/edu/fiuba/algo3/resources/mapa.json";
        Dado dadoTrucado1 = new DadoTrucado(4);
        Dado dadoTrucado2 = new DadoTrucado(2);

        Tablero tablero = new Tablero(pathTablero);
        Jugador jugador = new Jugador("Jugador", tablero.salida());
        Gladiador gladiador = jugador.obtenerGladiador();

        jugador.jugarTurno(dadoTrucado1, tablero); //se queda sin energia
        jugador.jugarTurno(dadoTrucado2, tablero);

        gladiador.recibirEnergia(-5);
        assertFalse(gladiador.puedeMoverse());

    }

    @Test
    public void test11GladiadorSeQuedaSinEnergiaYSaltaUnTurno()throws IOException, ParseException, EnemigoNoValido, PremioNoValido{
        String pathTablero = "src/main/java/edu/fiuba/algo3/resources/mapa.json";
        Dado dadoTrucado1 = new DadoTrucado(4);
        Dado dadoTrucado2 = new DadoTrucado(2);

        Tablero tablero = new Tablero(pathTablero);
        Jugador jugador = new Jugador("Jugador", tablero.salida());
        Gladiador gladiador = jugador.obtenerGladiador();

        jugador.jugarTurno(dadoTrucado1, tablero); //se queda sin energia
        jugador.jugarTurno(dadoTrucado2, tablero);

        assertEquals(jugador.obtenerTurno(),1);

    }
    @Test
    public void test12GladiadorSeQuedaSinEnergiaYSaltaUnTurnoYjuegaOtroTurno()throws IOException, ParseException, EnemigoNoValido, PremioNoValido{
        Tablero tablero = new Tablero("src/main/java/edu/fiuba/algo3/resources/mapa.json");
        AlgoRoma algoRoma = new AlgoRoma(tablero, new DadoTrucado(4));
        Jugador jugador = new Jugador("Rama", tablero.salida());



        algoRoma.jugarTurno(jugador);//se queda sin energia
        algoRoma.jugarTurno(jugador);
        algoRoma.jugarTurno(jugador);



        assertTrue(jugador.obtenerGladiador().puedeMoverse());

    }

    @Test
    public void test13SeCrearJugaodrConNombreInvalido()throws IOException, ParseException, EnemigoNoValido, PremioNoValido{
        Tablero tablero = new Tablero("src/main/java/edu/fiuba/algo3/resources/mapa.json");
        AlgoRoma algoRoma = new AlgoRoma(tablero, new DadoTrucado(0));
        try {
            Jugador jugador = new Jugador("Ram", tablero.salida());
        }
        catch (NombreInvalidoException e){
            System.out.println("Nombre de jugador invalido");
        }
    }


}