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

    public Direction getNextDirection() {
        Direction newWay = null;

        if (Direction.EAST.equals(this)) { newWay = SOUTH; }
        else if (Direction.SOUTH.equals(this)) { newWay = WEST; }
        else if (Direction.WEST.equals(this)) { newWay = NORTH; }
        else if (Direction.NORTH.equals(this)) { newWay = EAST; }

        return newWay;
    }
}
