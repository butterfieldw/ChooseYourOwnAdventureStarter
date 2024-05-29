package com.example.chooseyourownadventurestarter;

public class Place {

    private String description;
    private int row;
    private int col;

    private boolean[] exits;    // North, East, South, West

    public Place(String description, int row, int col) {
        this.description = description;
        this.row = row;
        this.col = col;
        exits = new boolean[] { false, false, false, false };
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

    public void setExits(String[] validDirections) {
        if (validDirections == null) {
            throw new IllegalArgumentException("String array cannot be null.");
        }
        exits = new boolean[] { false, false, false, false };
        for (String direction : validDirections) {
            if (direction.equalsIgnoreCase("north")) {
                exits[0] = true;
            } else if (direction.equalsIgnoreCase("east")) {
                exits[1] = true;
            } else if (direction.equalsIgnoreCase("south")) {
                exits[2] = true;
            } else if (direction.equalsIgnoreCase("west")) {
                exits[3] = true;
            }
        }
    }

    public boolean canExit(String direction) {
        if (direction.equalsIgnoreCase("north")) {
            return exits[0];
        } else if (direction.equalsIgnoreCase("east")) {
            return exits[1];
        } else if (direction.equalsIgnoreCase("south")) {
            return exits[2];
        }  else if (direction.equalsIgnoreCase("west")) {
            return exits[3];
        } else {
            return false;
        }
    }

    public String toString() {
        return "Your position is: (" + row + ", " + col + ")" +
                "\n\n" + description;
    }
}
