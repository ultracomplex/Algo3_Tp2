package edu.fiuba.algo3.controladores;
import edu.fiuba.algo3.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorJugadores {

    private Stage stage;

    @FXML
    private Button addPlayerButton;

    @FXML
    private TextField playerNameIn;

    @FXML
    private ListView<String> playerNames;

    @FXML
    private Button startGameButton;

    @FXML
    private Label errorNombreJugador;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void addPlayerActionEvent(ActionEvent actionEvent) {
        if (playerNameIn.getText().length() < 4) {
            errorNombreJugador.setText("El nombre debe tener al menos 4 caracteres");
            errorNombreJugador.setVisible(true);
            return;
        }
        errorNombreJugador.setVisible(false);


        playerNames.getItems().add(playerNameIn.getText());

        if (playerNames.getItems().size() >= 2) {
            startGameButton.setDisable(false);
        }

        if (playerNames.getItems().size() == 6) {
            addPlayerButton.setDisable(true);
            playerNameIn.setDisable(true);
        }

        playerNameIn.clear();
    }

    @FXML
    void addPlayerKeyEvent(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            addPlayerActionEvent(new ActionEvent());
        }

    }

    @FXML
    void removePlayer(ActionEvent event) {
        playerNames.getItems().remove(playerNames.getSelectionModel().getSelectedItem());

        if (playerNames.getItems().size() < 2) {
            startGameButton.setDisable(true);
        }

        if (playerNames.getItems().size() < 6) {
            addPlayerButton.setDisable(false);
            playerNameIn.setDisable(false);
        }
    }

    @FXML
    void startGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistaJuego.fxml"));
        Main.jugadores.clear();
        Main.nombres.clear();
        for (String nombre : playerNames.getItems()) {
            Main.nombres.add(nombre);
        }

        Stage stage1 = (Stage) startGameButton.getScene().getWindow();
        Parent root = loader.load();
        stage1.setTitle("Game");
        stage1.setResizable(false);
        stage1.setScene(new Scene(root));
        System.out.println("Start game");
    }

}
