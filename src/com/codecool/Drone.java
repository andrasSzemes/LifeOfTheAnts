package com.codecool;

import java.util.ArrayList;

public class Drone extends Ant {
    private int queenTime = 0;
    private boolean saidSomething = false;

    Drone(ArrayList<int[]> creaturePositions) {
        super(creaturePositions);
        status = "Drone";
    }

    @Override
    public void move(ArrayList<int[]> creaturePositions) {
        if (queenTime == 0) {
            if (distanceFromQueen >= 3) {
                Direction queenWay = getQueenWay(creaturePositions);

                if (queenWay != null) {
                    takeStep(queenWay);
                    setDistanceFromQueen();
                }
            }
        }
    }

    public void mate(Queen queen, ArrayList<int[]> creaturePositions, int queenDistance) {
        if (saidSomething) { removeMessage(); }

        if (distanceFromQueen < 3) {
            if (queenTime == 0) {
                if (queen.getInTheMood()) { mateSuccessfully(); }
                else { matePoorly(creaturePositions, queenDistance); }
            }
            else if (queenTime == 1) { doElseElsewhere(creaturePositions, queenDistance); }

            else if (queenTime > 0) { queenTime--; }
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

    private void mateSuccessfully() {
        queenTime = 10;
        Util.antSay("HALLELUJAH");
        saidSomething = true;
    }

    private void matePoorly(ArrayList<int[]> creaturePositions, int queenDistance) {
        Util.antSay("D’OH");
        saidSomething = true;
        placeCreature(creaturePositions, queenDistance);
        setDistanceFromQueen();
    }

    private void removeMessage() {
        Util.antSay("          ");
        saidSomething = false;
    }

    private void doElseElsewhere(ArrayList<int[]> creaturePositions, int queenDistance) {
        placeCreature(creaturePositions, queenDistance);
        setDistanceFromQueen();
        queenTime--;
    }
}
