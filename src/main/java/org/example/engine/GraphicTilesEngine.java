package org.example.engine;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import org.example.App;
import org.example.domain.Element;
import org.example.domain.Snake;
import org.example.domain.SnakeFood;

public class GraphicTilesEngine {

    //=============================================================================================
    // Properties
    //=============================================================================================
    public static int xBoardDimension;
    public static int yBoardDimension;
    public static int cellSize;
    public static int gridWidth;
    public static double fps;

    public static final String TILES_COLOR = "-fx-background-color: white;";

    //=============================================================================================
    // Dependencies
    //=============================================================================================

    private TilePane snakeBoardPane;
    private VBox mainPane;


    //=============================================================================================
    // Constructors
    //=============================================================================================
    public GraphicTilesEngine(TilePane snakeBoardPane, VBox mainPane) {
        this.snakeBoardPane = snakeBoardPane;
        this.mainPane = mainPane;
        initBoard(snakeBoardPane);
        initBoardCells(snakeBoardPane);
        setMainPaneSize();
    }



    //=============================================================================================
    // Public
    //=============================================================================================
    public void eraseSnake(Snake snake){
        for (int i = 0; i < snake.getSnakeList().size(); i++) {
            int x = snake.getSnakeList().get(i).getX();
            int y = snake.getSnakeList().get(i).getY();
            snakeBoardPane.getChildren().get(y*xBoardDimension+x).setStyle(TILES_COLOR);
        }
    }

    public void displaySnake(Snake snake) {
        for (int i = 0; i < snake.getSnakeList().size(); i++) {
            int x = snake.getSnakeList().get(i).getX();
            int y = snake.getSnakeList().get(i).getY();
            String color = "-fx-background-color:"+snake.getSnakeList().get(i).getColor()+";";
            snakeBoardPane.getChildren().get(y*xBoardDimension+x).setStyle(color);
        }
    }

    public void displayFood(SnakeFood snakeFood) {
            int x = snakeFood.getX();
            int y = snakeFood.getY();
            String color = "-fx-background-color:"+SnakeFood.COLOR+";";
            snakeBoardPane.getChildren().get(y*xBoardDimension+x).setStyle(color);
    }

    public void eraseFood(SnakeFood snakeFood) {
        int x = snakeFood.getX();
        int y = snakeFood.getY();
        snakeBoardPane.getChildren().get(y*xBoardDimension+x).setStyle(TILES_COLOR);
    }

    public void initView() {
        ObservableList<Node> children = snakeBoardPane.getChildren();
        for (Node child : children) {
            child.setStyle(TILES_COLOR);
        }
    }

    public void fillRed() {
        ObservableList<Node> children = snakeBoardPane.getChildren();
        for (Node child : children) {
            child.setStyle("-fx-background-color: red;");
        }
    }


    public static int getxBoardDimension() {
        return xBoardDimension;
    }

    public static int getyBoardDimension() {
        return yBoardDimension;
    }

    //=============================================================================================
    // Private Methods
    //=============================================================================================
    private void initBoard(TilePane snakeBoardPane) {
        snakeBoardPane.setPrefColumns(xBoardDimension);
        snakeBoardPane.setPrefRows(yBoardDimension);
        Insets insets = new Insets(gridWidth, gridWidth, gridWidth, gridWidth);
        snakeBoardPane.setPadding(insets);
        snakeBoardPane.setHgap(gridWidth);
        snakeBoardPane.setVgap(gridWidth);

        int snakeBoardPaneWidth = (xBoardDimension + 1) * gridWidth + xBoardDimension * cellSize;
        int snakeBoardPaneHeight = (yBoardDimension + 1) * gridWidth + yBoardDimension * cellSize;
        snakeBoardPane.setMinSize(snakeBoardPaneWidth, snakeBoardPaneHeight);
        snakeBoardPane.setMaxSize(snakeBoardPaneWidth, snakeBoardPaneHeight);
    }

    private void setMainPaneSize() {
        double minWidth = snakeBoardPane.getMinWidth() + 40;
        double minHeight = snakeBoardPane.getMinHeight() + 200;
        //mainPane.setMinSize(minWidth, minHeight);
        App.getStage().setMinWidth(minWidth);
        App.getStage().setMinHeight(minHeight);

        App.getStage().setMaxWidth(minWidth);
        App.getStage().setMaxHeight(minHeight);
    }

    private void initBoardCells(TilePane snakeBoardView) {
        for (int i = 0; i < xBoardDimension * yBoardDimension; i++) {
            Pane pane = new Pane();
            pane.setPrefSize(cellSize, cellSize);
            pane.setMinSize(cellSize, cellSize);
            pane.setStyle(TILES_COLOR);
            snakeBoardView.getChildren().add(pane);
        }
    }

}
