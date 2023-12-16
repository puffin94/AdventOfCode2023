package com.puffin94;

import java.io.BufferedReader;
import java.io.FileReader;

public class Day15 {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/main/resources/challengeInputs/day15.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            int verificationNumber = 0;
            while ((line = br.readLine()) != null) {
                String[] seperatedValues = line.split(",");
                for (String value : seperatedValues) {
                    verificationNumber += hashAlgorithm(value);
                }
            }
            System.out.println(verificationNumber);
        } catch (Exception e) {
            System.err.println("Issue Reading Challenge file");
            System.err.println(e);
        }
    }

    public static int hashAlgorithm(String valueToBeHashed) {
        int hashValue = 0;
        for (String split : valueToBeHashed.split("")) {
            char[] splitValue = split.toCharArray();
            hashValue += splitValue[0];
            hashValue *= 17;
            hashValue %= 256;
        }

        return hashValue;
    }

}
