package com.puffin94;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4 {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/main/resources/challengeInputs/day4.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            int totalPoints = 0;
            List<Card> cardList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] split = line.split(":");
                String cardNumber = split[0];
                String wals = split[1];
                totalPoints += countPoints(wals);
                cardList.add(new Card(cardNumber, wals));
            }
            System.out.println("Total Points: " + totalPoints);
            System.out.println("Total Cards: "+ countCards(cardList));
        } catch (Exception e) {
            System.err.println("Issue Reading Challenge file");
            System.err.println(e);
        }
    }

    public static int countNumberOfMatches(String wals) {
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
        return numberOfMatches;
    }

    public static int countPoints(String wals) {
        int numberOfMatches = countNumberOfMatches(wals);
        if (numberOfMatches > 0) {
            return (int) Math.pow(2, (numberOfMatches - 1));
        }
        return numberOfMatches;
    }

    public static int countCards(List<Card> cardList) {
        int copyCount = 0;
        for (int i = 0; i < cardList.size(); i++) {
            Card card = cardList.get(i);
            copyCount+= card.getCopyCount();
            if (card.getNumberOfWinners() > 0) {
                for (int j = 0; j < card.getCopyCount(); j++) {
                    for (int l = 0; l < card.getNumberOfWinners(); l++) {
                        cardList.get(i + l + 1).increaseCopyCount();
                    }
                }
            }
        }
        return copyCount;
    }

    public static class Card {
        private int cardNumber;
        private int numberOfWinners;
        private int copyCount = 1;

        public Card(String cardNumber, String wals) {
            setCardNumber(cardNumber);
            setNumberOfMatches(wals);
        }

        private void setNumberOfMatches(String wals) {
            this.numberOfWinners = countNumberOfMatches(wals);
        }

        private void setCardNumber(String cardNumber) {
            String[] split = cardNumber.split(" ");
            this.cardNumber = Integer.parseInt(split[split.length - 1]);
        }

        public void increaseCopyCount() {
            this.copyCount++;
        }

        public int getCardNumber() {
            return this.cardNumber;
        }

        public int getNumberOfWinners() {
            return this.numberOfWinners;
        }

        public int getCopyCount() {
            return this.copyCount;
        }
    }
}
