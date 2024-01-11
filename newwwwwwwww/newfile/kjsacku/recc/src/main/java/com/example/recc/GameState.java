package com.example.recc;

public class GameState {
    private static int present_score;
    private static int present_cherries;
    private static int highest_score;

    public static int getPresent_score() {
        return present_score;
    }

    public static void setPresent_score(int present_score) {
        GameState.present_score = present_score;
    }

    public static int getPresent_cherries() {
        return present_cherries;
    }

    public static void setPresent_cherries(int present_cherries) {
        GameState.present_cherries = present_cherries;
    }

    public static int getHighest_score() {
        return highest_score;
    }

    public static void setHighest_score(int a) {
        if(a > GameState.highest_score){
            GameState.highest_score = a;
        }
        FileWriteExample.writeHighScoreToFile();
    }

    public GameState(int a, int b) {
        present_score = a;
        present_cherries = b;
        if(a>highest_score){
            highest_score = a;
        }
    }
}
