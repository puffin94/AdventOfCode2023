package com.puffin94;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day4 {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/main/resources/challengeInputs/day4.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            int totalPoints = 0;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(":");
                String cardName = split[0];
                String wals = split[1];
                totalPoints += countPoints(wals);
            }
            System.out.println("Total Points: " + totalPoints);
        } catch (Exception e) {
            System.err.println("Issue Reading Challenge file");
            System.err.println(e);
        }
    }

    public static int countPoints(String wals) {
        int numberOfMatches = 0;
        String[] split = wals.split("\\|");
        List<String> winners = new ArrayList<>(Arrays.asList(split[0].split(" ")));
        List<String> numbers = new ArrayList<>(Arrays.asList(split[1].split(" ")));
        winners.removeIf(String::isBlank);
        numbers.removeIf(String::isBlank);

        for (String winner : winners) {
            if (numbers.contains(winner.trim())) {
                numberOfMatches++;
            }
        }

        if (numberOfMatches > 0) {
            return (int) Math.pow(2, (numberOfMatches - 1));
        }
        return numberOfMatches;

    }
}
