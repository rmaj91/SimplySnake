package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import org.example.domain.Direction;
import org.example.domain.Snake;
import org.example.domain.SnakeFood;
import org.example.engine.GraphicTilesEngine;

public class SecondaryController implements Initializable {


    //=============================================================================================
    // Dependencies
    //=============================================================================================
    private Snake snake;
    private Direction direction;
    private Thread gameThread;
    private SnakeFood snakeFood;
    private GraphicTilesEngine graphicTilesEngine;


    @FXML
    private TilePane snakeBoardPane;
    @FXML
    private VBox mainPane;
    @FXML
    private Label pointsLabel;


    public void keyPressed(KeyEvent event) {
        direction = getDirection(event.getCode().toString());
    }

    public void keyReleased(KeyEvent event) {
        //todo disable for now
        //direction = null;
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.getStage().setMaxHeight(350);
        App.getStage().setMaxWidth(200);
        App.getStage().setMinHeight(350);
        App.getStage().setMinWidth(200);
        if (gameThread != null)
            gameThread.interrupt();
        App.setRoot("primary");
    }


    public void startClicked() {
        if (gameThread != null)
            return;

        gameThread = new Thread(() -> {
            initDependencies();

            long lastTime = System.nanoTime();
            double delta = 0;
            double fps = GraphicTilesEngine.fps;
            double tickPerSecond = 1000000000 / fps;

            while (true) {
                long now = System.nanoTime();
                delta += (now - lastTime) / tickPerSecond;
                lastTime = now;
                if (delta >= 1) {

                    graphicTilesEngine.eraseFood(snakeFood);
                    graphicTilesEngine.eraseSnake(snake);
                    snakeFood.randLocation(snake);
                    snake.changeDirection(direction);
                    snake.move(snakeFood);
                    if (snake.isSnakeOutOfBoard() || snake.isSnakeBiteHimself())
                        break;
                    graphicTilesEngine.displayFood(snakeFood);
                    graphicTilesEngine.displaySnake(snake);
                    System.out.println("Size: "+snake.getSnakeList().size());
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
        pointsLabel.setText("000");
    }


    //=============================================================================================
    // Private Methods
    //=============================================================================================
    private void initDependencies() {
        direction = null;
        snake = new Snake();
        graphicTilesEngine.initView();
        snakeFood = new SnakeFood(snake);
        pointsLabel.setText("000");
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