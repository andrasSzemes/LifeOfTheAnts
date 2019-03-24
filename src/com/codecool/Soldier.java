package com.codecool;

import java.util.ArrayList;

public class Soldier extends Ant {

    private Direction prevDirection;

    Soldier(ArrayList<int[]> creaturePositions) {
        super(creaturePositions);
        status = "Soldier";
        this.prevDirection = Direction.NORTH;
    }

    @Override
    public void move(ArrayList<int[]> creaturePositions) {
        Direction chosenWay = prevDirection.getNextDirection();
        if (canMoveThere(creaturePositions, chosenWay)) {
            takeStep(chosenWay);
            prevDirection = chosenWay;
            setDistanceFromQueen();
        }
    }
}
