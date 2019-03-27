package com.codecool;

import com.codecool.termlib.Terminal;

public class Util {

    /**
     * Randomly returns -1 or +1.
     *
     * @return an integer: -1 or +1
     */
    static public int plusMinus() {
        return (Math.round(Math.random()) == 0) ? -1 : 1;
    }

    static public void antSay(String message) {
        Terminal.moveTo(Direction.NORTH.border * 2 + 3, 1);
        System.out.print(message);
    }
}
