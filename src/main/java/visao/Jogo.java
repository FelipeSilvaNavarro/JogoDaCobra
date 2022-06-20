package main.java.visao;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.modelo.Cobra;
import main.java.modelo.Comida;
import main.java.modelo.LoopConfig;

public class Jogo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Cobra cobra = new Cobra();
        Comida comida = new Comida();
        Cenario cenario = new Cenario(primaryStage, cobra, comida);
        new LoopConfig(cenario, cobra, comida);
    }
}