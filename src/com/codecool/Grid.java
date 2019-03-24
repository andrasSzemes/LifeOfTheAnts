package com.codecool;

import java.util.ArrayList;
import java.lang.Thread;
import com.codecool.termlib.*;

public class Grid {
    int frameRate = 150;

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
        grid.drawFrame();

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
        int borderWidth = 1;

        for (Creature creature : creatures) {
            Terminal.moveTo(Direction.NORTH.border + creature.getyPosition() + 1 + borderWidth,
                            Direction.EAST.border + creature.getxPosition() + 1 + borderWidth);
            Terminal.setChar(creature.getStatus().charAt(0));
        }
    }

    public void hideGrid() {
        int borderWidth = 1;

        for (Creature creature : creatures) {
            Terminal.moveTo(Direction.NORTH.border + creature.getyPosition() + 1 + borderWidth,
                            Direction.EAST.border + creature.getxPosition() + 1 + borderWidth);
            Terminal.setChar(' ');
        }
    }

    public void drawFrame() {
        Terminal.moveTo(2,1);
        for (int i=0; i<Direction.EAST.border*2 + 2; i++) {
            System.out.print("-");
        }
        Terminal.moveTo(Direction.NORTH.border*2 + 2, 1);
        for (int i=0; i<Direction.EAST.border*2 + 2; i++) {
            System.out.print("-");
        }

        for (int i=2; i<Direction.NORTH.border*2 + 3; i++) {
            Terminal.moveTo(i, 1);
            System.out.print("|");
            Terminal.moveTo(i,Direction.EAST.border*2 + 2);
            System.out.print("|");
        }
    }
}
