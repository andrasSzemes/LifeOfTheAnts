package com.codecool;

import java.util.ArrayList;
import java.lang.Thread;
import com.codecool.termlib.*;

public class Grid {
    int frameRate = 100;

    int[][] creaturesPositions;

    ArrayList<Creature> creatures = new ArrayList<Creature>();

    /** Returns all creatures positions in the grid
     *
     * @return ArrayList<int[]> contains all creatures positions in the grid
     */
    public ArrayList<int[]> getCreaturesPositions() {
        ArrayList<int[]> positions = new ArrayList<int[]>();

        for (Creature creature : creatures) {
            positions.add(new int[]{creature.getxPosition(), creature.getyPosition()});
        }

        return positions;
    }

    public static void main(String[] args) {
        Grid grid = new Grid();

        grid.addQueen();

        for (int i=0; i<20; i++) {
            grid.addWorker(grid.getCreaturesPositions());
        }

        for (int i=0; i<20; i++) {
            grid.addSoldier(grid.getCreaturesPositions());
        }

        Terminal.clearScreen();
        for (int i=0; i<100; i++) {
            grid.hideGrid();

            for (Creature creature : grid.creatures) {
                creature.move(grid.getCreaturesPositions());
            }
            grid.showGrid();

            try { Thread.sleep(grid.frameRate); }
            catch (InterruptedException e) {}
        }

        Terminal.clearScreen();
    }

    public void addQueen() {
        creatures.add(new Queen());
    }

    public void addWorker(ArrayList<int[]> creaturePositions) {
        creatures.add(new Worker(creaturePositions));
    }

    public void addSoldier(ArrayList<int[]> creaturePositions) {
        creatures.add(new Soldier(creaturePositions));
    }

    public void showGrid() {
        for (Creature creature : creatures) {
            Terminal.moveTo(Direction.NORTH.border + creature.getyPosition() + 1, Direction.EAST.border + creature.getxPosition() + 1);
            Terminal.setChar(creature.getStatus().charAt(0));
        }
    }

    public void hideGrid() {
        for (Creature creature : creatures) {
            Terminal.moveTo(Direction.NORTH.border + creature.getyPosition() + 1, Direction.EAST.border + creature.getxPosition() + 1);
            Terminal.setChar(' ');
        }
    }
}
