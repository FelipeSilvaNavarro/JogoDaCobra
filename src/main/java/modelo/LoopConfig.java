package main.java.modelo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import main.java.visao.Cenario;

public class LoopConfig {

    private final Cenario cenario;
    private final Cobra cobra;
    private final Comida comida;
    private KeyCode currentDirection;
    private Timeline timeline;

    public LoopConfig(Cenario cenario, Cobra cobra, Comida comida) {
        this.cenario = cenario;
        this.cobra = cobra;
        this.comida = comida;
        this.cenario.setKeyPressed(e -> {
            KeyCode keyPressed = e.getCode();

            // Obter a tecla pressionada e agir sobre isto
            if (keyPressed.equals(KeyCode.RIGHT) && !KeyCode.LEFT.equals(currentDirection)) {
                currentDirection = keyPressed;
            }

            if (keyPressed.equals(KeyCode.LEFT) && !KeyCode.RIGHT.equals(currentDirection)) {
                currentDirection = keyPressed;
            }

            if (keyPressed.equals(KeyCode.UP) && !KeyCode.DOWN.equals(currentDirection)) {
                currentDirection = keyPressed;
            }

            if (keyPressed.equals(KeyCode.DOWN) && !KeyCode.UP.equals(currentDirection)) {
                currentDirection = keyPressed;
            }
        });

        starLoop();
    }

    // Fazer a cobra se movimentar
    public void starLoop() {
        timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.millis(150), e -> extracted());

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Integer.MAX_VALUE);
        timeline.play();
    }

    private void extracted() {
        Integer positionX = cobra.getPositionX();
        Integer positionY = cobra.getPositionY();
        if (KeyCode.RIGHT.equals(currentDirection)) {
            positionX = positionX + Config.SQUARE_SIZE;
        }
        if (KeyCode.LEFT.equals(currentDirection)) {
            positionX = positionX - Config.SQUARE_SIZE;
        }
        if (KeyCode.UP.equals(currentDirection)) {
            positionY = positionY - Config.SQUARE_SIZE;
        }
        if (KeyCode.DOWN.equals(currentDirection)) {
            positionY = positionY + Config.SQUARE_SIZE;
        }
        // Saber se a cobra bateu nas paredes
        if (positionX < 0 ||
                positionY < 0 ||
                positionX > Config.WIDTH - Config.SQUARE_SIZE ||
                positionY > Config.HEIGHT - Config.SQUARE_SIZE ||
                this.cobra.checkColision(positionX, positionY)) {

            gameOver();

        } else {

            // Se a cobra bateu na comida
            if (positionX.equals(comida.getPositionX()) && positionY.equals(comida.getPositionY())) {
                this.comida.setRandomPosition();
                this.cobra.eat(this.cenario);
            }

            this.cobra.setPosition(positionX, positionY);
        }
    }

    public void gameOver() {
        this.timeline.stop();
        this.currentDirection = null;
        this.cobra.die();
        this.cenario.showGameOver(this);
    }

}