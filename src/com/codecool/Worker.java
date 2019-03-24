package com.codecool;

import java.util.ArrayList;

public class Worker extends Ant {

    Worker(ArrayList<int[]> creaturePositions) {
        super(creaturePositions);
        status = "Worker";
    }

    @Override
    public void move(ArrayList<int[]> creaturePositions) {
        boolean needToMove = true;
        int tries = 0;

        while (needToMove) {
            Direction chosenWay = Direction.getRandom();
            if (canMoveThere(creaturePositions, chosenWay)) {
                takeStep(chosenWay);
                setDistanceFromQueen();
                needToMove = false;
            }
            if (tries == 10) {
                needToMove = false;
            }
            tries++;
        }
    }
}
