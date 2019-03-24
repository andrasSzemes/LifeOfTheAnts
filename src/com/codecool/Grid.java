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
        grid.addWorker(grid.getCreaturesPositions(), 20);
        grid.addSoldier(grid.getCreaturesPositions(), 10);
        grid.addDrone(grid.getCreaturesPositions(), 5);

        Terminal.clearScreen();
        grid.drawFrame();
        grid.showGridFor(100);
        Terminal.clearScreen();
    }

    public void addQueen() {
        creatures.add(new Queen());
    }

    public void addWorker(ArrayList<int[]> creaturePositions, int numberOfWorkers) {
        for (int i=0; i<numberOfWorkers; i++) {
            creatures.add(new Worker(creaturePositions));
        }
    }

    public void addSoldier(ArrayList<int[]> creaturePositions, int numberOfSoldiers) {
        for (int i=0; i<numberOfSoldiers; i++) {
            creatures.add(new Soldier(creaturePositions));
        }
    }

    public void addDrone(ArrayList<int[]> creaturePositions, int numberOfDrones) {
        for (int i=0; i<numberOfDrones; i++) {
            creatures.add(new Drone(creaturePositions));
        }
    }

    public void fillFrame() {
        int borderWidth = 1;

        for (Creature creature : creatures) {
            Terminal.moveTo(Direction.NORTH.border + creature.getyPosition() + 1 + borderWidth,
                            Direction.EAST.border + creature.getxPosition() + 1 + borderWidth);
            Terminal.setChar(creature.getStatus().charAt(0));
        }
    }

    public void clearFrame() {
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

    public void sleep() {
        try { Thread.sleep(this.frameRate); }
        catch (InterruptedException e) {}
    }

    public void showGridFor(int time) {
        for (int i=0; i<100; i++) {
            this.clearFrame();

            for (Creature creature : this.creatures) {
                creature.move(this.getCreaturesPositions());
            }
            this.fillFrame();
            this.sleep();
        }
    }
}
