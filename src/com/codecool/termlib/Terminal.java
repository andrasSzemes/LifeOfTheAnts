package com.codecool.termlib;

import java.io.Console;

public class Terminal {
    private static final String CONTROL_CODE = "\u001b[";
    private static final String CLEAR = "2J";
    private static final String MOVE = "H";
    private static final String STYLE = "m";

    public Terminal() {
    }

    public static void resetStyle() {
    }

    public static void clearScreen() {
        command("\u001b[2J");
    }

    public static void moveTo(Integer x, Integer y) {
        command(CONTROL_CODE + x + ";" + y + MOVE);
    }

    public static void setColor(Color var0) {
        String var1;
        switch(var0) {
            case BLACK:
                var1 = "30";
                break;
            case RED:
                var1 = "31";
                break;
            case GREEN:
                var1 = "32";
                break;
            case YELLOW:
                var1 = "33";
                break;
            case BLUE:
                var1 = "34";
                break;
            case MAGENTA:
                var1 = "35";
                break;
            case CYAN:
                var1 = "36";
                break;
            case WHITE:
                var1 = "37";
                break;
            default:
                var1 = "";
        }

        command(CONTROL_CODE + var1 + STYLE);
    }

    public static void setBgColor(Color var0) {
        String var1;
        switch(var0) {
            case BLACK:
                var1 = "40";
                break;
            case RED:
                var1 = "41";
                break;
            case GREEN:
                var1 = "42";
                break;
            case YELLOW:
                var1 = "43";
                break;
            case BLUE:
                var1 = "44";
                break;
            case MAGENTA:
                var1 = "45";
                break;
            case CYAN:
                var1 = "46";
                break;
            case WHITE:
                var1 = "47";
                break;
            default:
                var1 = "";
        }

        command(var1);
    }

    public static void setUnderline() {
    }

    public static void moveCursor(Direction var0, Integer var1) {
        Integer var2 = var1;
        if (var0 == Direction.UP) {
            var2 = var1;
        } else if (var0 == Direction.DOWN) {
            var2 = var1;
        } else if (var0 == Direction.FORWARD) {
            var2 = var1;
        } else if (var0 == Direction.BACKWARD) {
            var2 = var1;
        }

        command(var2 + "");
    }

    public static void setChar(char var0) {
        command(String.valueOf(var0));
    }

    public int[] getConsoleDimensions() {
        Console var1 = System.console();
        command("\u001b[H\u001b[2J");
        command("\u001b[5000;5000H");
        System.out.print("\u001b[6n");
        String var2 = var1.readLine();
        var2 = var2.substring(var2.indexOf("[") + 1, var2.indexOf("R"));
        String[] var3 = var2.split(";");
        int[] var4 = new int[]{Integer.parseInt(var3[0]), Integer.parseInt(var3[1])};
        return var4;
    }

    private static void command(String var0) {
        System.out.print(var0);
    }
}
