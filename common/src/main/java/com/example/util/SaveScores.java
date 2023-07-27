package com.example.util;
import java.util.ArrayList;
import java.util.Collections;
import com.codename1.io.Storage;
import com.example.statemanager.PlayingScene;

public class SaveScores {
    public static ArrayList<Integer> highscores;
    private static final String HIGHSCORES_KEY = "highscores";

    public static void saveScore() {
        SaveScores.highscores = readScores(); //adds file to arraylist
        cleanScores(); //clean up the scores
        int holder = (int) (PlayingScene.gameScore * 5);
        if (isTop5(holder)) { //check if top 5
            highscores.add(holder);
            saveScores(); //write down the score for future reference
        }
        cleanScores(); //clean them up at the end
        for (int i = 0; i < highscores.size(); i++) {
            System.out.println(highscores.get(i));
        }
    }

    private static Boolean isTop5(int score) { //determines if a number is top 5 and also adds it to our arraylist 
        if (SaveScores.highscores.size() <= 4) { //check if less than 5 numbers, just add automatically
            return true; 
        }

        for (int i = 0; i < highscores.size(); i++) { //add score if it is more than an existing score
            if (score > highscores.get(i)) {
                return true;
            }
        }

        return false;
    } 

    private static ArrayList<Integer> readScores() { //run this first to get the text file into highscores arraylist
        ArrayList<Integer> highscores = new ArrayList<Integer>();
        String scoresString = (String) Storage.getInstance().readObject(HIGHSCORES_KEY);
        if (scoresString != null && !scoresString.isEmpty()) {
            int commaIndex = scoresString.indexOf(',');
            while (commaIndex >= 0) { //split the string beCAUSE can't use String.split()
                String scoreStr = scoresString.substring(0, commaIndex); //add first score before first comma 
                highscores.add(Integer.parseInt(scoreStr));
                scoresString = scoresString.substring(commaIndex + 1); //return new string where the score added to highscores is deleted
                commaIndex = scoresString.indexOf(','); //find next comma in updates scoresString
            }
            // Add the last score after the last comma
            if (!scoresString.isEmpty()) {
                highscores.add(Integer.parseInt(scoresString));
            }
        }
        return highscores;
    }

    private static void cleanScores() { //clean scores up
        Collections.sort(SaveScores.highscores, Collections.reverseOrder());
        if (SaveScores.highscores.size() > 5) {
            for (int i = SaveScores.highscores.size() - 1; i > 4; i--) {
                SaveScores.highscores.remove(i);
            }
        }
    }
    private static void saveScores() {
        // Sort the high scores and keep the top 5
        Collections.sort(SaveScores.highscores, Collections.reverseOrder());
        if (SaveScores.highscores.size() > 5) {
            SaveScores.highscores.subList(5, SaveScores.highscores.size()).clear();
        }

        // Save the updated high scores to Storage
        StringBuilder sb = new StringBuilder();
        for (int score : highscores) {
            sb.append(score).append(",");
        }
        Storage.getInstance().writeObject(HIGHSCORES_KEY, sb.toString());
    }
}