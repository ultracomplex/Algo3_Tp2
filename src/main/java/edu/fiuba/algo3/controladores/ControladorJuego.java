package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.Main;
import edu.fiuba.algo3.modelo.AlgoRoma.AlgoRoma;
import edu.fiuba.algo3.modelo.AlgoRoma.Tablero;
import edu.fiuba.algo3.modelo.AlgoRoma.jugador.Jugador;
import edu.fiuba.algo3.modelo.dados.DadoPosta;
import edu.fiuba.algo3.resources.Resources;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorJuego implements Initializable {
    private int jugadorActual;
    private Tablero tablero;
    private ArrayList<StackPane> casillas;
    private ArrayList<ImageView> imagenesGladiadores;

    private ArrayList<Color> coloresGladiadores;
    private final String PATH_GLADIADOR = "file:src/main/resources/sprites/gladiador";

    @FXML
    private TilePane grillaMapa;

    @FXML
    private HBox datosJugadores;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.imagenesGladiadores = new ArrayList<ImageView>();
        this.coloresGladiadores = new ArrayList<Color>();

        this.tablero = new Tablero("src/main/java/edu/fiuba/algo3/resources/mapa.json");

        this.casillas = new ArrayList<StackPane>();

        ArrayList<String> arrayImagenes = this.tablero.obtenerArrayImagenes();

        for (String pathImagen : arrayImagenes) {
            StackPane casillaView = new StackPane();
            casillaView.setPrefSize(50, 50);
            Background fondo = new Background(new BackgroundImage(new Image(pathImagen, 50, 50, false, false), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
            casillaView.setBackground(fondo);
            grillaMapa.getChildren().add(casillaView);
            casillas.add(casillaView);
        }

        for (String nombre : Main.nombres) {
            Main.jugadores.add(new Jugador(nombre, this.tablero.salida()));
        }

        int indiceAux = 0;
        for (Jugador jugador : Main.jugadores) {
            this.imagenesGladiadores.add(new ImageView(new Image(PATH_GLADIADOR + indiceAux + ".png", 50, 50, false, false)));
            casillas.get(108).getChildren().add(this.imagenesGladiadores.get(indiceAux));
            datosJugadores.getChildren().add(Resources.getVista(getClass().getResource("/fxml/vistaDatosJugador.fxml"), new ControladorDatosJugador(jugador, Resources.coloresGladiadores.get(indiceAux))));
            indiceAux ++;
        }


        Main.algoRoma = new AlgoRoma(this.tablero, new DadoPosta());

        this.jugadorActual = 0;
    }

//    public static <T extends Parent> T getVista(URL vista, Object controlador){
//        FXMLLoader loader = new FXMLLoader(vista);
//        T view;
//        try {
//            loader.setController(controlador); // seteamos el controlador
//            view = loader.load();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        return view;
//
//    }

    @FXML
    void tirarDado(ActionEvent event) throws IOException {
        Jugador jugador = Main.jugadores.get(this.jugadorActual);
        int posicionJugador = this.tablero.obtenerIndiceCasilla(jugador.obtenerPosicion());
        ImageView imagenGladiador = this.imagenesGladiadores.get(this.jugadorActual);
        FXMLLoader loader;
        Stage stage = (Stage) grillaMapa.getScene().getWindow();


        //Sacar la imagen del gladiador, buscando que casillero es con la funcion en tablero.
        //imagenGladiador.setVisible(false);
        this.casillas.get(posicionJugador).getChildren().remove(imagenGladiador);

        Main.algoRoma.jugarTurno(jugador);
        this.jugadorActual = (this.jugadorActual + 1) % Main.jugadores.size();
        
        if (jugador.obtenerEstado().esGanador()) {
            loader = new FXMLLoader(getClass().getResource("/fxml/vistaGanador.fxml"));
            loader.setController(new ControladorGanador(jugador.obtenerNombre()));
            Parent root = loader.load();
            stage.setTitle("Ganador: " + jugador.obtenerNombre());
            stage.setResizable(false);
            stage.setScene(new Scene(root));
        } else if (jugador.obtenerEstado().esPerdedor()) {
            Main.jugadores.remove(jugador);
            this.jugadorActual--;
            if (Main.jugadores.size() == 0) {
                loader = new FXMLLoader(getClass().getResource("/fxml/vistaGameOver.fxml"));
                Parent root = loader.load();
                stage.setTitle("Game over");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
            }
        }
        else{
            posicionJugador = this.tablero.obtenerIndiceCasilla(jugador.obtenerPosicion());
            //imagenGladiador = new ImageView(new Image(PATH_GLADIADOR, 50, 50, false, false));
            this.casillas.get(posicionJugador).getChildren().add(imagenGladiador);
        }
    }
}

