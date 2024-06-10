package edu.fiuba.algo3.modelo.AlgoRoma;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.fiuba.algo3.modelo.AlgoRoma.jugador.Jugador;
import edu.fiuba.algo3.modelo.obstaculo.Obstaculo;
import edu.fiuba.algo3.modelo.obstaculo.FieraSalvaje;
import edu.fiuba.algo3.modelo.obstaculo.Bacanal;
import edu.fiuba.algo3.modelo.obstaculo.Lesion;
import edu.fiuba.algo3.modelo.premio.Premio;
import edu.fiuba.algo3.modelo.premio.Comida;
import edu.fiuba.algo3.modelo.premio.equipamiento.Nada;

public class Tablero {
    private int ancho;
    private int largo;
    private ArrayList<Casilla> posicionesJugables;

    public Tablero(String pathMapa) {
        int x, y;
        this.posicionesJugables = new ArrayList<Casilla>();
        String strObstaculo, strPremio;
        Obstaculo obstaculo;
        Premio premio;

        JsonParser parser = new JsonParser();

        // Obtengo objeto general.
        JsonObject objGral = null;
        try {
            objGral = parser.parse(new FileReader(pathMapa)).getAsJsonObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Obtengo el objeto mapa y asigno dimensiones.
        JsonObject objMapa = objGral.get("mapa").getAsJsonObject();
        this.ancho = objMapa.get("ancho").getAsInt();
        this.largo = objMapa.get("largo").getAsInt();

        // Obtengo el objeto camino y asigno las celdas del mapa.
        JsonObject objCamino = objGral.get("camino").getAsJsonObject();
        JsonArray arrCeldas = objCamino.get("celdas").getAsJsonArray();

        // Por cada celda del mapa:
        for (JsonElement celda : arrCeldas) {
            obstaculo = null;
            premio = null;

            // Obtengo el objeto celda.
            JsonObject objCelda = celda.getAsJsonObject();

            // Primitives elements of object.
            x = objCelda.get("x").getAsInt();
            y = objCelda.get("y").getAsInt();
            strObstaculo = objCelda.get("obstaculo").getAsString();
            strPremio = objCelda.get("premio").getAsString();


            //Aca se podría hacer un constructor de obstaculo que interprete un string y devuelva un obstaculo.
            switch (strObstaculo){
                case "Fiera":{
                    obstaculo = new FieraSalvaje();
                    break;
                }
                case "Bacanal":{
                    obstaculo = new Bacanal();
                    break;
                }
                case "Lesion":{
                    obstaculo = new Lesion();
                    break;
                }
            }

            switch (strPremio){
                case "Comida":{
                    premio = new Comida();
                    break;
                }
                case "Equipamiento":{
                    premio = new Nada();
                    break;
                }
            }

            this.aniadirPosicionJugable(new Casilla(x, y, obstaculo, premio));
        }
        Logger.info("Tablero creado correctamente.");
    }

    public Casilla moverJugador(Jugador jugador, int resultadoDado){
        int casillaActual = this.buscarNumeroCasilla(jugador.obtenerPosicion());

        if(casillaActual + resultadoDado >= this.cantidadDeCasillas()){
            if(jugador.tieneLlave()){
                casillaActual = this.cantidadDeCasillas();
            }
            else{
                casillaActual = this.cantidadDeCasillas() / 2;
            }
        }
        else{
            casillaActual += resultadoDado;
        }

        Casilla casilla = this.posicionesJugables.get(casillaActual - 1);
        casilla.activarCasilla(jugador.obtenerGladiador());
        return casilla;
    }

    public int buscarNumeroCasilla(Casilla casilla){
        int indexActual = 0;
        for (Casilla casillaActual : this.posicionesJugables) {
            indexActual ++;
            if(casillaActual == casilla){
                return indexActual;
            }
        }
        return indexActual;
    }

    private Casilla casillaSegunNumero(int x, int y){
        Casilla casillaAux = new Casilla(x, y, null, null);
        for (Casilla casillaActual : this.posicionesJugables) {
            if(casillaActual.esIgualA(casillaAux)){
                return casillaActual;
            }
        }
        return null;
    }

    public Casilla llegada(){
        return this.posicionesJugables.get(this.cantidadDeCasillas() - 1);
    }
    public Casilla salida(){
        return this.posicionesJugables.get(0);
    }

    public void aniadirPosicionJugable(Casilla casilla){
        this.posicionesJugables.add(casilla);
    }

    public int cantidadDeCasillas(){
        return this.posicionesJugables.size();
    }

    public ArrayList<String> obtenerArrayImagenes(){
        ArrayList<String> imagenes = new ArrayList<String>();
        Casilla casillaAux;

        for(int fila = 1; fila <= this.largo; fila++){
            for(int columna = 1; columna <= this.ancho; columna++){
                casillaAux = casillaSegunNumero(columna, fila);
                if (casillaAux != null){
                    imagenes.add(casillaAux.obtenerImagen());
                }
                else{
                    imagenes.add("file:src/main/resources/sprites/casilla_default.png");
                }
            }
        }

        return imagenes;
    }

    public int obtenerIndiceCasilla(Casilla casilla){
        return ((casilla.y-1) * this.ancho) + (casilla.x-1);
    }
}


