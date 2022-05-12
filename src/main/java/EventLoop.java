package main.java;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;

public class EventLoop {

    private final Cenario cenario;
    private final Snake snake;
    private final Food food;
    private KeyCode currentDirection;
    private Timeline timeline;

    public EventLoop(Cenario cenario, Snake snake, Food food) {
        this.cenario = cenario;
        this.snake = snake;
        this.food = food;
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
        Integer positionX = snake.getPositionX();
        Integer positionY = snake.getPositionY();
        if (KeyCode.RIGHT.equals(currentDirection)) {
            positionX = positionX + main.java.Config.SQUARE_SIZE;
        }
        if (KeyCode.LEFT.equals(currentDirection)) {
            positionX = positionX - main.java.Config.SQUARE_SIZE;
        }
        if (KeyCode.UP.equals(currentDirection)) {
            positionY = positionY - main.java.Config.SQUARE_SIZE;
        }
        if (KeyCode.DOWN.equals(currentDirection)) {
            positionY = positionY + main.java.Config.SQUARE_SIZE;
        }
        // Saber se a cobra bateu nas paredes
        if (positionX < 0 ||
                positionY < 0 ||
                positionX > main.java.Config.WIDTH - main.java.Config.SQUARE_SIZE ||
                positionY > main.java.Config.HEIGHT - main.java.Config.SQUARE_SIZE ||
                this.snake.checkColision(positionX, positionY)) {

            gameOver();

        } else {

            // Se a cobra bateu na comida
            if (positionX.equals(food.getPositionX()) && positionY.equals(food.getPositionY())) {
                this.food.setRandomPosition();
                this.snake.eat(this.cenario);
            }

            this.snake.setPosition(positionX, positionY);
        }
    }

    public void gameOver() {
        this.timeline.stop();
        this.currentDirection = null;
        this.snake.die();
        this.cenario.showGameOver(this);
    }

}