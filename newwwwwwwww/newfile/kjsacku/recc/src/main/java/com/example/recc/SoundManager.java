package com.example.recc;
import java.awt.Toolkit;

public class SoundManager {
    public static void playBeepSound(int numberOfTimes) {
        for (int i = 0; i < numberOfTimes; i++) {
            Toolkit.getDefaultToolkit().beep();
            try {
                // Pause for a short time between beeps
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Example usage in the main method
        System.out.println("Playing beep sound multiple times...");
        playBeepSound(3); // Change the number to the desired repetition
    }
}