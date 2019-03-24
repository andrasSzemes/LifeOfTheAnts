package com.codecool;

public class Util {

    /**
     * Randomly returns -1 or +1.
     *
     * @return an integer: -1 or +1
     */
    static public int plusMinus() {
        return (Math.round(Math.random()) == 0) ? -1 : 1;
    }
}
