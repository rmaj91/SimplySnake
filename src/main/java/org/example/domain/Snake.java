package org.example.domain;

import javafx.scene.canvas.GraphicsContext;
import org.example.engine.GraphicTilesEngine;

import java.util.LinkedList;

public class Snake {

    //=============================================================================================
    // Properties
    //=============================================================================================
    private LinkedList<Element> snakeList;
    private Direction direction;


    //=============================================================================================
    // Constructors
    //=============================================================================================

    /**
     * Snake tail has index 0.
     */
    public Snake() {
        this.snakeList = new LinkedList<>();
        this.snakeList.add(new Element(0, 0));
        this.snakeList.add(new Element(0, 1));
        direction = Direction.DOWN;
    }


    //=============================================================================================
    // Assessors
    //=============================================================================================


    public LinkedList<Element> getSnakeList() {
        return snakeList;
    }

    //=============================================================================================
    // Public Methods
    //=============================================================================================
    public void move() {
        movebody();
//        if(isFoodEaten())
//            eatFood;
//        else
        moveHead();
    }


    //=============================================================================================
    // Private Methods
    //=============================================================================================

    private void movebody() {
        for (int i = 0; i < snakeList.size() - 1; i++) {
            Element snakePart = snakeList.get(i + 1);
            int x = snakePart.getX();
            int y = snakePart.getY();
            snakeList.get(i).setX(x);
            snakeList.get(i).setY(y);
        }
    }

    private void moveHead() {
        Element snakePart = snakeList.getLast();
        snakePart.setX(snakePart.getX() + direction.getX());
        snakePart.setY(snakePart.getY() + direction.getY());
    }

    public void changeDirection(Direction direction) {
        if (direction == null)
            return;
        if (this.direction.getX() + direction.getX() != 0 && this.direction.getY() + direction.getY() != 0)
            this.direction = direction;
    }

    public boolean ifSnakeOutOfBoard() {
        if (snakeList.getLast().getX() == GraphicTilesEngine.getxBoardDimension()
                || snakeList.getLast().getY() == GraphicTilesEngine.getyBoardDimension())
            return true;
        if (snakeList.getLast().getX() < 0 || snakeList.getLast().getY() < 0)
            return true;
        return false;
    }
}
