package com.puffin94;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day1 {
    public static void main(String[] args) {
        ArrayList<CalibrationValue> calibrationValues = new ArrayList<>();
        try {
            FileReader fr = new FileReader("src/main/resources/challengeInputs/day1.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                calibrationValues.add(new CalibrationValue(line));

            }
        } catch (Exception e) {
            System.err.println("Issue Reading Challenge file");
            System.err.println(e);
        }

        System.out.println("Summing all calibration Values...");
        int sumOfValues = 0;
        int updatedSum = 0;
        for (CalibrationValue cv : calibrationValues) {
            sumOfValues += cv.getCalibrationValue();
            updatedSum += cv.getUpdatedCalibrationValue();
        }
        System.out.println("Value of all calibration values is :" + sumOfValues);
        System.out.println("Updated value of all calibration values is :" + updatedSum);
    }

    static class CalibrationValue {
        private String[] valuesArray;
        private String valueString;
        private String calibrationValue;
        private String updatedCV;

        private String[] numbersAsWords = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        private String[] wordsWithNumbers = {"o1e","t2o","t3e","f4r","f5e","s6x","s7n","e8t","n9e"};

        public CalibrationValue(String values) {
            valueString = values;
            this.valuesArray = values.split("");
            calibrationValue = assignCalibrationValues(valuesArray);
            updatedCV = assignCalibrationValues(replaceWordsAsNumbers().split(""));
        }

        private String assignCalibrationValues(String[] arrayOfValues) {
            String value1 = null, value2 = null;
            for (String character : arrayOfValues) {
                try {
                    if (character == null) {
                        continue;
                    }
                    Integer.parseInt(character);
                } catch (NumberFormatException e) {
                    continue;
                }
                value1 = character;
                break;
            }
            for (int i = arrayOfValues.length - 1; i >= 0; i--) {
                try {
                    if (arrayOfValues[i] == null) {
                        continue;
                    }
                    Integer.parseInt(arrayOfValues[i]);
                } catch (NumberFormatException e) {
                    continue;
                }
                value2 = arrayOfValues[i];
                break;
            }

            return value1 + value2;
        }

        public Integer getCalibrationValue() {
            return Integer.parseInt(calibrationValue);
        }

        public Integer getUpdatedCalibrationValue() {
            return Integer.parseInt(updatedCV);
        }

        private String replaceWordsAsNumbers() {
            String replacementValue = valueString;
            for (int i = numbersAsWords.length-1;i>=0 ;i--) {
                while(replacementValue.contains(numbersAsWords[i])){
                    replacementValue = replacementValue.replace(numbersAsWords[i],wordsWithNumbers[i]);
                }
            }
            return replacementValue;
        }
    }
}
