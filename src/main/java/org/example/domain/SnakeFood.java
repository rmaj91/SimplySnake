package org.example.domain;

import org.example.engine.GraphicTilesEngine;

public class SnakeFood {

    //=============================================================================================
    // Properties
    //=============================================================================================
    private int x;
    private int y;
    private boolean eaten;
    public static final String COLOR = "blue";

    //=============================================================================================
    // Constructors
    //=============================================================================================
    public SnakeFood(Snake snake) {
        this.eaten = true;
        randLocation(snake);
    }


    //=============================================================================================
    // Public Methods
    //=============================================================================================
    public void randLocation(Snake snake) {
        if (!eaten)
            return;
//        do {
            this.x = (int) (Math.random() * GraphicTilesEngine.getxBoardDimension());
            this.y = (int) (Math.random() * GraphicTilesEngine.getyBoardDimension());
            System.out.println("Rand " + x + "\t" + y);
//        } while (foodOnSnake(snake));
        eaten = false;
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    //=============================================================================================
    // Private Methods
    //=============================================================================================

    private boolean foodOnSnake(Snake snake) {
        for (Element snakePart : snake.getSnakeList()) {
            if (snakePart.getX() == x || snakePart.getY() == y)
                return true;
        }
        return false;
    }
}
