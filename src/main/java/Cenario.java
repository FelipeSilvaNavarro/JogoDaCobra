package main.java;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Cenario {

    private final Scene scene;
    private final javafx.scene.Group root = new javafx.scene.Group();
    private final Snake snake;
    private final Food food;

    public Cenario(Stage primaryStage, Snake snake, Food food) {
        this.scene = new Scene(root, Config.WIDTH, Config.HEIGHT);

        String css = getClass().getResource("StyleSheets.css").toExternalForm();
        Font.loadFont(getClass().getResource("Koulen-Regular.ttf").toExternalForm(), 10);
        scene.getStylesheets().add(css);
        scene.setFill(Color.GREY);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game - By Felipe Navarro");
        primaryStage.show();

        this.snake = snake;
        this.food = food;

        root.getChildren().add(snake.getHead());
        root.getChildren().add(food.getFood());
    }

    public void setKeyPressed(EventHandler<? super KeyEvent> action) {
        this.scene.setOnKeyPressed(action);
    }

    public void showGameOver(EventLoop eventLoop) {
        TryAgainButton tryAgainButton = new TryAgainButton(e -> {
            clean();
            add(this.snake.reset());
            add(this.food.getFood());
            eventLoop.starLoop();
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