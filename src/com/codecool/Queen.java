package com.codecool;

import java.util.ArrayList;

public class Queen extends Ant {

    private int matingMood = 10;
    private final int[] RELAXTIME = new int[]{10,20};

    Queen() {
        xPosition = 0;
        yPosition = 0;
        this.status = "Queen";
    }

    /**
     * The queen does not move.
     */
    @Override
    public void move(ArrayList<int[]> creaturePositions) {}

    public boolean getMatingMood() {
        return (matingMood == 0);
    }

    public void getIntoMood() {
        if (matingMood > 0) { matingMood--; }
    }

    public void relaxAfterMating() {
        matingMood = RELAXTIME[0] + (int) (Math.random() * (RELAXTIME[1] - RELAXTIME[0]));
    }

}
