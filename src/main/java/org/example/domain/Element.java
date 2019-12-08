package org.example.domain;

import javafx.scene.paint.Color;

public class Element {

    //=============================================================================================
    // Properties
    //=============================================================================================
    public static final String DEFAULT_COLOR = "green";
    private int x;
    private int y;
    private String color;


    //=============================================================================================
    // Constructors
    //=============================================================================================
    public Element(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = DEFAULT_COLOR;
    }

    /**
     * eg .color "white" or "#123456"
     */
    public Element(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }


    //=============================================================================================
    // Assessors
    //=============================================================================================
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String  getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
