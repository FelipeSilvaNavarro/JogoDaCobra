package main.java.modelo;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import main.java.visao.Cenario;
import main.java.visao.UtilidadeCanvas;

public class Cobra {

    private final List<Canvas> body = new ArrayList<>();
    private final List<Integer[]> positionHistory = new ArrayList<>();
    private Canvas head;

    public Cobra() {
        createSnake();
    }

    private void createSnake() {
        // Pintar a cabeça da cobra de azul
        this.head = UtilidadeCanvas.buildSnake(Color.GREEN);
        // Setar a cobra no meio da tela
        resetPosition();
    }

    private void resetPosition() {
        this.head.setTranslateX(Config.WIDTH / 2 - Config.SQUARE_SIZE);
        this.head.setTranslateY(Config.HEIGHT / 2 - Config.SQUARE_SIZE);
    }


    public Canvas getHead() {
        return head;
    }

    public Integer getPositionX() {
        return (int) this.head.getTranslateX();
    }

    public Integer getPositionY() {
        return (int) this.head.getTranslateY();
    }

    public void setPosition(Integer x, Integer y) {
        this.head.setTranslateX(x);
        this.head.setTranslateY(y);

        for (int i = 0; i < body.size(); i++) {
            Canvas bodyPart = body.get(i);
            Integer[] position = this.positionHistory.get(this.positionHistory.size() - (i + 1));
            bodyPart.setTranslateX(position[0]);
            bodyPart.setTranslateY(position[1]);
        }

        this.positionHistory.add(new Integer[]{x, y});

        if (positionHistory.size() > body.size() + 1) {
            positionHistory.remove(0);
        }
    }

    // Resetar a cobra logo após o botão TryAgain
    public Canvas reset() {
        resetPosition();
        changeColor(Color.GREEN);
        this.positionHistory.clear();
        this.body.clear();
        return this.head;
    }

    public void die() {
        changeColor(Color.RED);
    }

    private void changeColor(Paint color) {
        GraphicsContext gc = this.getHead().getGraphicsContext2D();
        gc.clearRect(0, 0, Config.SQUARE_SIZE, Config.SQUARE_SIZE);
        gc.setFill(color);
        gc.fillRect(0, 0, Config.SQUARE_SIZE, Config.SQUARE_SIZE);
    }

    public void eat(Cenario scenario) {
        Canvas bodyPart = UtilidadeCanvas.buildSnake(Color.rgb(0, 51, 0));
        scenario.add(bodyPart);
        this.body.add(bodyPart);
    }

    public boolean checkColision(Integer x, Integer y) {
        for (Canvas bodyPart : this.body) {
            Integer bodyPositionX = (int) bodyPart.getTranslateX();
            Integer bodyPositionY = (int) bodyPart.getTranslateY();
            if (x.equals(bodyPositionX) && y.equals(bodyPositionY)) {
                return true;
            }
        }

        return false;
    }
}