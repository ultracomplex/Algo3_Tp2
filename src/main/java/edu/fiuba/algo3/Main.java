package edu.fiuba.algo3;

import edu.fiuba.algo3.controladores.ControladorInicio;
import edu.fiuba.algo3.modelo.AlgoRoma.AlgoRoma;
import edu.fiuba.algo3.modelo.AlgoRoma.jugador.Jugador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.util.Collection;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class Main extends Application {

    public static final int INITIAL_WIDTH = 535;
    public static final int INITIAL_HEIGHT = 400;

    public static AlgoRoma algoRoma;
    public static Collection<String> nombres;
    public static ArrayList<Jugador> jugadores;


    @Override
    public void start(Stage stage) {
        try {
            Main.jugadores = new ArrayList<Jugador>();
            Main.nombres = new ArrayList<>();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistaInicio.fxml"));

            Scene scene = new Scene(loader.load(), INITIAL_WIDTH, INITIAL_HEIGHT);
            stage.setScene(scene);

            ControladorInicio controlador = loader.getController();

            stage.setTitle("AlgoRoma");
            stage.show();

            controlador.inicializar(stage, scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}