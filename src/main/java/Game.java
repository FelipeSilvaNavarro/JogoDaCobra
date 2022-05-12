package main.java;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Snake snake = new Snake();
        Food food = new Food();
        Cenario cenario = new Cenario(primaryStage, snake, food);
        new EventLoop(cenario, snake, food);
    }
}