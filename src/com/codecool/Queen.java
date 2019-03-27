package com.codecool;

import java.util.ArrayList;

public class Queen extends Ant {

    private boolean inTheMood = false;

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

    public boolean getInTheMood() {
        return inTheMood;
    }


}
