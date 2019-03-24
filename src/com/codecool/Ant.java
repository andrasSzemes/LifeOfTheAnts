package com.codecool;

import java.util.ArrayList;

public abstract class Ant
        extends Creature {

    protected int distanceFromQueen;

    public void setDistanceFromQueen() {
        this.distanceFromQueen = Math.abs(this.xPosition) + Math.abs(this.yPosition);
    }

    Ant(ArrayList<int[]> creaturePositions) {
        super(creaturePositions);
        setDistanceFromQueen();
    }

    Ant() {

    }
}
