package main.java;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TryAgainButton extends VBox {

    public TryAgainButton(javafx.event.EventHandler<ActionEvent> action) {
        Label gameOver = new Label("GAME OVER");
        gameOver.setFont(Font.font(50));
        gameOver.setTextFill(Color.RED);
        this.getChildren().add(gameOver);

        
        Button tryAgain = new Button("Try Again");
        tryAgain.setFont(Font.font(25));
        tryAgain.setOnAction(action);
        this.getChildren().add(tryAgain);
        
        this.setMinWidth(main.java.Config.WIDTH);
        this.setMinHeight(main.java.Config.HEIGHT);
        this.setAlignment(Pos.CENTER);
        
        tryAgain.getStyleClass().add("gameOverButton");
        gameOver.getStyleClass().add("gameOverLabel");
        getStyleClass().add("gameOverScene");
    }
}