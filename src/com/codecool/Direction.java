package com.codecool;

public enum Direction {
    EAST(40),
    SOUTH(-10),
    WEST(-40),
    NORTH(10);

    public int border;

    Direction(int border) {
        this.border = border;
    }

    public static Direction getRandom() {
        return Direction.values()[(int) (Math.random() * 4)];
    }
}
