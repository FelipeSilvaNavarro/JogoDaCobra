package main.java.visao;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.java.modelo.Config;
import main.java.modelo.LoopConfig;

import java.awt.*;
import java.util.Objects;

public class Cenario {

    private final Scene scene;
    private final javafx.scene.Group root = new javafx.scene.Group();
    private final Cobra cobra;
    private final Comida comida;

    public Cenario(Stage primaryStage, Cobra cobra, Comida comida) {
        this.scene = new Scene(root, Config.WIDTH, Config.HEIGHT);
        Button pause = new Button();

        String css = Objects.requireNonNull(getClass().getResource("../modelo/StyleSheets.css")).toExternalForm();
        Font.loadFont(Objects.requireNonNull(getClass().getResource("../modelo/Koulen-Regular.ttf")).toExternalForm(), 10);
        scene.getStylesheets().add(css);
        scene.setFill(Color.GREY);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game - By Felipe Navarro");
        primaryStage.show();

        this.cobra = cobra;
        this.comida = comida;

        root.getChildren().add(cobra.getHead());
        root.getChildren().add(comida.getFood());
    }

    public void setKeyPressed(EventHandler<? super KeyEvent> action) {
        this.scene.setOnKeyPressed(action);
    }

    public void showGameOver(LoopConfig loopConfig) {
        TryAgainButton tryAgainButton = new TryAgainButton(e -> {
            clean();
            add(this.cobra.reset());
            add(this.comida.getFood());
            loopConfig.starLoop();
        });

        this.root.getChildren().add(tryAgainButton);
    }

    private void clean() {
        this.root.getChildren().remove(0, this.root.getChildren().size());
    }

    public void add(Node node) {
        this.root.getChildren().add(node);
    }

}