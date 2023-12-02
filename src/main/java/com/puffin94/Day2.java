package com.puffin94;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day2 {

    public static void main(String[] args) {

        ArrayList<Game> successfulGames = new ArrayList<>();
        ArrayList<Game> allGames = new ArrayList<>();
        try {
            FileReader fr = new FileReader("src/main/resources/challengeInputs/day2.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                Game game = new Game(line,12,14,13);
                allGames.add(game);
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
        int sumOfGamePowers = 0;
        for(Game game:allGames){
            sumOfGamePowers += game.gamePower;
        }

        System.out.println("Sum of Successful Games: "+sumOfSuccessfulGames);
        System.out.println("Sum of Game Powers: "+sumOfGamePowers);
    }

    static class Game {
        String gameNumber;
        String gameString;
        boolean isPossible = true;
        int redCubes;
        int blueCubes;
        int greenCubes;
        int minRed=0;
        int minBlue=0;
        int minGreen=0;
        int gamePower=0;

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
                    }
                    if(redValue>minRed){
                        minRed = redValue;
                    }
                }
                if (value.contains("blue")) {
                    value = value.replace("blue","").trim();
                    blueValue = Integer.parseInt(value);
                    if (blueValue>blueCubes){
                        isPossible = false;
                    }
                    if(blueValue>minBlue){
                        minBlue = blueValue;
                    }
                }
                if (value.contains("green")) {
                    value = value.replace("green","").trim();
                    greenValue = Integer.parseInt(value);
                    if (greenValue>greenCubes){
                        isPossible = false;
                    }
                    if(greenValue>minGreen){
                        minGreen=greenValue;
                    }
                }

            }
            gamePower = minBlue*minGreen*minRed;
        }
        public Integer getGameNumber(){
            return Integer.parseInt(gameNumber);
        }
    }
}
