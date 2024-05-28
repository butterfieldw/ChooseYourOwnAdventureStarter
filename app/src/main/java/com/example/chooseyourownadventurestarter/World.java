package com.example.chooseyourownadventurestarter;

public class World
{
    private Place[][] map;
    private int playerRowPosition;
    private int playerColPosition;

    public World(String[][] descriptions) {
        int rows = descriptions.length;
        int cols = descriptions[0].length;
        if (descriptions == null) {
            throw new IllegalArgumentException("The world must be passed a non null 2D String array.");
        }
        if (rows < 1 || cols < 1) {
            throw new IllegalArgumentException("Rows and columns must both be greater than zero.");
        }
        // Must be a rectangular array:
        for (String[] str : descriptions) {
            if (str.length != cols) {
                throw new IllegalArgumentException("All rows must have the same number of columns.");
            }
        }
        map = new Place[rows][cols];
        playerRowPosition = 0;
        playerColPosition = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                String description = descriptions[r][c];
                map[r][c] = new Place(description, r, c);
            }
        }
    }
    public Place[][] getMap() {
        return map;
    }

    public Place getPlayerLocation() {
        return map[playerRowPosition][playerColPosition];
    }

    public void setPlayerPosition(int row, int col) {
        if ((row < 0 || row >= map.length) || (col < 0 || col >= map[0].length)) {
            throw new IllegalArgumentException("Row and column must be a position in the array.");
        }
        playerRowPosition = row;
        playerColPosition = col;
    }
}