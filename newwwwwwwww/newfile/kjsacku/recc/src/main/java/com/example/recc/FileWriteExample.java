package com.example.recc;

import java.io.*;

public class FileWriteExample {
    public static void writeHighScoreToFile() {
        // Get the high score from GameState
        int highScore = GameState.getHighest_score();
        // Specify the file name
        String fileName = "C:\\Users\\HP\\Downloads\\newfile\\kjsacku\\recc\\src\\main\\java\\com\\example\\recc\\input.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Write the high score to the file
            writer.write(Integer.toString(highScore));
            System.out.println("High score has been written to " + fileName);
            System.out.println(highScore);
        } catch (IOException e) {
            // Handle any IO exceptions that may occur
            e.printStackTrace();
        }
    }
    public static int readHighScoreFromFile() {
        // Specify the file name
        String fileName = "C:\\Users\\HP\\Downloads\\newfile\\kjsacku\\recc\\src\\main\\java\\com\\example\\recc\\input.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Read the high score from the file
            String highScoreString = reader.readLine();
            if (highScoreString != null) {
                int highScore = Integer.parseInt(highScoreString);
                System.out.println("Read high score from " + fileName + ": " + highScore);
                return highScore;
            } else {
                System.out.println("File " + fileName + " is empty.");
            }
        } catch (IOException | NumberFormatException e) {
            // Handle IO exceptions or parsing errors
            e.printStackTrace();
        }

        // Return a default value if there's an issue reading the file
        return -1;
    }
}
