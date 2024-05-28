package com.example.chooseyourownadventurestarter;

public class Place {

    private String description;
    private int row;
    private int col;

    public Place(String description, int row, int col) {
        this.description = description;
        this.row = row;
        this.col = col;
    }

    public String getDescription() {
        return description;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String toString() {
        return description + "\nRow index: " + row + " Column index: " + col;
    }
}
