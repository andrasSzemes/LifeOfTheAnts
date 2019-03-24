package com.codecool;

import java.util.ArrayList;

public class Drone extends Ant {

    Drone(ArrayList<int[]> creaturePositions) {
        super(creaturePositions);
        status = "Drone";
    }

    @Override
    public void move(ArrayList<int[]> creaturePositions) {
        if (distanceFromQueen > 3) {
            Direction queenWay = getQueenWay(creaturePositions);


            if (queenWay != null) {
                takeStep(queenWay);
                setDistanceFromQueen();
            }
        }
    }

    private Direction getQueenWay(ArrayList<int[]> creaturePositions) {
        Direction queenWay = null;

        if (xPosition > 0) { queenWay = Direction.WEST; }
        else if (xPosition < 0) { queenWay = Direction.EAST; }

        if (queenWay != null && canMoveThere(creaturePositions, queenWay)) { return queenWay; }

        if (yPosition > 0) { queenWay = Direction.SOUTH; }
        else if (yPosition < 0) { queenWay = Direction.NORTH; }

        if (queenWay != null && canMoveThere(creaturePositions, queenWay)) { return queenWay; }

        return queenWay;
    }
}
