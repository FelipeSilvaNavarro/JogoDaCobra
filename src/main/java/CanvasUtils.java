package main.java;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class CanvasUtils {

    public static Canvas buildSnake(Paint color) {
        Canvas square = new Canvas(Config.SQUARE_SIZE, Config.SQUARE_SIZE);
        GraphicsContext gc = square.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(2, 2, 45, 45);
        return square;
    }

    public static Canvas buildFood(Paint color) {
        Canvas square = new Canvas(Config.SQUARE_SIZE, Config.SQUARE_SIZE);
        GraphicsContext gc = square.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillOval(2, 2, 30, 30);
        return square;
    }
}
