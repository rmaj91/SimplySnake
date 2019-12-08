package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import org.example.domain.Direction;
import org.example.domain.Snake;
import org.example.engine.GraphicTilesEngine;

public class SecondaryController implements Initializable {

    private GraphicTilesEngine graphicTilesEngine;

    @FXML
    private TilePane snakeBoardPane;
    @FXML
    private VBox mainPane;
    @FXML
    private Button startButtton;

    private Snake snake;
    private Direction direction;
    private Thread  gameThread;


    public void keyPressed(KeyEvent event) {
        direction = getDirection(event.getCode().toString());
    }

    public void keyReleased(KeyEvent event) {
        //todo disable for now
        //direction = null;
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.getStage().setMinWidth(200);
        App.getStage().setMinHeight(220);
        App.setRoot("primary");
    }

    public void startClicked() {


        gameThread = new Thread(() -> {
            direction = null;
            snake = new Snake();
            graphicTilesEngine.initView();
            int step = 1;

            long lastTime = System.nanoTime();
            double delta = 0;
            double fps = 2.5;
            double tickPerSecond = 1000000000 / fps;

            while (true) {
                long now = System.nanoTime();
                delta += (now - lastTime) / tickPerSecond;
                lastTime = now;
                if (delta >= 1) {

                    snake.changeDirection(direction);
                    graphicTilesEngine.eraseSnake(snake);
                    snake.move();
                    if(snake.ifSnakeOutOfBoard())
                        break;
                    graphicTilesEngine.displaySnake(snake);

                    System.out.println(step++);
                    delta--;
                }
            }
            System.out.println("GAME OVER");
            graphicTilesEngine.fillRed();
            gameThread = null;
        });
        gameThread.start();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        snakeBoardPane.getChildren().clear();
        graphicTilesEngine = new GraphicTilesEngine(snakeBoardPane, mainPane);
    }


    private Direction getDirection(String code) {
        Direction direction = null;
        if (code.equalsIgnoreCase("w"))
            direction = Direction.UP;
        else if (code.equalsIgnoreCase("s"))
            direction = Direction.DOWN;
        else if (code.equalsIgnoreCase("a"))
            direction = Direction.LEFT;
        else if (code.equalsIgnoreCase("d"))
            direction = Direction.RIGHT;

        System.out.println(direction);
        return direction;
    }
}