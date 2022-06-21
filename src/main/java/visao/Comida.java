package main.java.visao;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import main.java.modelo.Config;
import main.java.modelo.UtilidadeCanvas;

public class Comida {

    private Canvas food;

    public Comida() {
        createFood();
        setRandomPosition();
    }
    private void createFood() {
        this.food = UtilidadeCanvas.buildFood(Color.RED);
    }

    public void setRandomPosition() {
        this.food.setTranslateX(randomNumber(0, Config.WIDTH - Config.SQUARE_SIZE));
        this.food.setTranslateY(randomNumber(0, Config.HEIGHT - Config.SQUARE_SIZE));
    }

    private Integer randomNumber(Integer min, Integer max) {
        int value = (int) (Math.random() * ((max - min) + 1)) + min;
        return value - (value % Config.SQUARE_SIZE);
    }

    public Canvas getFood() {
        return food;
    }

    public Integer getPositionX() {
        return (int) this.food.getTranslateX();
    }

    public Integer getPositionY() {
        return (int) this.food.getTranslateY();
    }
}
