package com.codecool;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Creature {
    protected int xPosition;
    protected int yPosition;
    protected String status;

    Creature(ArrayList<int[]> creaturePositions) {
        placeCreature(creaturePositions);
    }

    Creature() {

    }

    public void placeCreature(ArrayList<int[]> creaturePositions) {
        boolean needPlacement = true;
        boolean canBePlaced = false;

        while (needPlacement) {
            int xNum = Util.plusMinus() * (int) (Math.random() * Direction.EAST.border);
            int yNum = Util.plusMinus() * (int) (Math.random() * Direction.NORTH.border);

            canBePlaced = !creatInWay(creaturePositions, new int[]{xNum, yNum});
            if (canBePlaced) {
                xPosition = xNum;
                yPosition = yNum;

                needPlacement = false;
            }
        }
    }

    public void placeCreature(ArrayList<int[]> creaturePositions, int queenDistance) {
        boolean needPlacement = true;
        boolean canBePlaced = false;

        while (needPlacement) {
            int xNum = Util.plusMinus() * (int) (Math.random() * queenDistance);
            if (queenDistance - Math.abs(xNum) > Direction.NORTH.border - 1) { continue; }
            int yNum = Util.plusMinus() * (queenDistance - Math.abs(xNum));

            canBePlaced = !creatInWay(creaturePositions, new int[]{xNum, yNum});
            if (canBePlaced) {
                xPosition = xNum;
                yPosition = yNum;

                needPlacement = false;
            }
        }
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public String getStatus() {
        return status;
    }

    /**
     * Specific movement patterns that a creature does.
     *
     * @param creaturePositions all creatures' coordinates
     */
    public abstract void move(ArrayList<int[]> creaturePositions);

    /**
     * Returns if the creature can move in a direction or not based on other creatures and the grid's borders.
     *
     * @param creaturePositions actual position of all creatures
     * @param direction which direction the creature wants to move
     * @return true or false based on validation
     */
    public boolean canMoveThere(ArrayList<int[]> creaturePositions, Direction direction) {
        int[] nextCoords = this.getNextCoords(direction);

        return (! creatInWay(creaturePositions, nextCoords)) && stayInGrid(nextCoords);
    }

    /**
     * Changes the creature's x or y position accordingly.
     *
     * @param direction which way the creatures wants to move
     */
    public void takeStep(Direction direction) {
        switch (direction) {
            case EAST:
                this.xPosition += 1;
                break;
            case SOUTH:
                this.yPosition -= 1;
                break;
            case WEST:
                this.xPosition -= 1;
                break;
            case NORTH:
                this.yPosition += 1;
        }
    }

    private int[] getNextCoords(Direction direction) {
        int[] nextPosition = new int[]{xPosition, yPosition};

        switch (direction) {
            case EAST:
                nextPosition[0] += 1;
                break;
            case SOUTH:
                nextPosition[1] -= 1;
                break;
            case WEST:
                nextPosition[0] -= 1;
                break;
            case NORTH:
                nextPosition[1] += 1;
        }

        return nextPosition;
    }

    private boolean stayInGrid(int[] nextPosition) {
        boolean outEastBorder = nextPosition[0] < Direction.EAST.border;
        boolean outWestBorder = nextPosition[0] > Direction.WEST.border;
        boolean outSouthBorder = nextPosition[1] > Direction.SOUTH.border;
        boolean outNorthBorder = nextPosition[1] < Direction.NORTH.border;

        return outEastBorder && outSouthBorder && outWestBorder && outNorthBorder;
    }

    private boolean creatInWay(ArrayList<int[]> creaturePositions, int[] nextPosition) {
        boolean creatInWay = false;

        for (int[] position : creaturePositions) {
            if (Arrays.equals(position, nextPosition)) {
                creatInWay = true;
                break;
            }
        }

        return creatInWay;
    }
}
