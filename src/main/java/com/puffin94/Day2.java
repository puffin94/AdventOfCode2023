package com.puffin94;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day2 {

    public static void main(String[] args) {

        ArrayList<Game> successfulGames = new ArrayList<>();
        try {
            FileReader fr = new FileReader("src/main/resources/challengeInputs/day2.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                Game game = new Game(line,12,14,13);
                if(game.isPossible){
                    successfulGames.add(game);
                }
            }
        } catch (Exception e) {
            System.err.println("Issue Reading Challenge file");
            System.err.println(e);
        }

        int sumOfSuccessfulGames = 0;
        for (Game game:successfulGames){
            sumOfSuccessfulGames+=game.getGameNumber();
        }

        System.out.println("Sum of Successful Games: "+sumOfSuccessfulGames);
    }

    static class Game {
        String gameNumber;
        String gameString;
        boolean isPossible = true;
        int redCubes;
        int blueCubes;
        int greenCubes;

        public Game(String gameString, int redCubes, int blueCubes, int greenCubes) {
            this.gameString = gameString;
            this.redCubes = redCubes;
            this.blueCubes = blueCubes;
            this.greenCubes = greenCubes;
            checkGame();
        }

        void checkGame() {
            String[] firstSplit = gameString.split(":");
            gameNumber = firstSplit[0].replace("Game ", "").replace(":", "");
            String[] secondSplit = firstSplit[1].split(";");
            for(String gameRound: secondSplit) {
                checkCubes(gameRound);
            }
        }

        void checkCubes(String gameRound) {
            String[] numbersAndColours = gameRound.split(",");
            for (String rawValue : numbersAndColours) {
                String value = rawValue.trim();
                int redValue = 0;
                int blueValue = 0;
                int greenValue = 0;
                if (value.contains("red")) {
                    value = value.replace("red","").trim();
                    redValue = Integer.parseInt(value);
                    if (redValue>redCubes){
                        isPossible = false;
                        break;
                    }
                }
                if (value.contains("blue")) {
                    value = value.replace("blue","").trim();
                    blueValue = Integer.parseInt(value);
                    if (blueValue>blueCubes){
                        isPossible = false;
                        break;
                    }
                }
                if (value.contains("green")) {
                    value = value.replace("green","").trim();
                    greenValue = Integer.parseInt(value);
                    if (greenValue>greenCubes){
                        isPossible = false;
                        break;
                    }
                }

            }
        }
        public Integer getGameNumber(){
            return Integer.parseInt(gameNumber);
        }
    }
}
