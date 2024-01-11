package com.example.recc;

import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class obstaclehandler {
    private AnchorPane plane;
    private double PlaneHeight;
    private double PlaneWidth;
    Random random =new Random();
    public obstaclehandler(AnchorPane plane,double planeHeight , double planeWidth){
        this.plane = plane;
        this.PlaneHeight = planeHeight;
        this.PlaneWidth = planeWidth;
    }

}
