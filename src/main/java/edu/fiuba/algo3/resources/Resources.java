package edu.fiuba.algo3.resources;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;

public class Resources {

    public static Hashtable<Integer, Color> coloresGladiadores;

    public static Color ROJO = Color.rgb(179,4,4);
    public static Color VERDE = Color.rgb(42,204,53);
    public static Color AZUL = Color.rgb(41,51,227);

    public static Color VIOLETA = Color.rgb(183,40,235);

    public static Color NARANJA = Color.rgb(241,116,39);

    public static Color AQUA = Color.rgb(27,122,108);

    public static final Resources recursos = new Resources();

    public Resources() {
        this.coloresGladiadores = new Hashtable<Integer, Color>();
        this.coloresGladiadores.put(0, ROJO);
        this.coloresGladiadores.put(1, AZUL);
        this.coloresGladiadores.put(2, VERDE);
        this.coloresGladiadores.put(3, VIOLETA);
        this.coloresGladiadores.put(4, NARANJA);
        this.coloresGladiadores.put(5, AQUA);
    }

    public static <T extends Parent> T getVista(URL vista, Object controlador){
        FXMLLoader loader = new FXMLLoader(vista);
        T view;
        try {
            loader.setController(controlador); // seteamos el controlador
            view = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return view;

    }
}
