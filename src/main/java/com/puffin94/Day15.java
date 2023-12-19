package com.puffin94;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day15 {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/main/resources/challengeInputs/day15.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            int verificationNumber = 0;
            HashMap<Integer, ArrayList<Lens>> lensMap = new HashMap<>();
            while ((line = br.readLine()) != null) {
                String[] seperatedValues = line.split(",");
                for (String value : seperatedValues) {
                    verificationNumber += hashAlgorithm(value);
                    addToHashMap(lensMap, value);
                }
            }
            System.out.println("Verification Number: " + verificationNumber);
            System.out.println("Total Focal Power: " + getTotalFocalPower(lensMap));
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

    public static void addToHashMap(Map<Integer, ArrayList<Lens>> hashMap, String value) {

        if (value.contains("-")) {
            String[] valueBreakdown = value.split("-");
            int labelNumber = hashAlgorithm(valueBreakdown[0]);
            if (hashMap.containsKey(labelNumber)) {
                ArrayList<Lens> lensList = hashMap.get(labelNumber);
                for (Lens lens : lensList) {
                    if (valueBreakdown[0].equals(lens.getLabel())) {
                        lensList.remove(lens);
                        break;
                    }
                }
            }
        } else {
            String[] valueBreakdown = value.split("=");
            Lens newLens = new Lens(valueBreakdown[0], Integer.parseInt(valueBreakdown[1]));
            int labelNumber = hashAlgorithm(newLens.getLabel());
            if (hashMap.containsKey(labelNumber)) {
                ArrayList<Lens> lensList = hashMap.get(labelNumber);
                boolean lensNotIncluded = true;
                for (Lens lens : lensList) {
                    if (newLens.getLabel().equals(lens.getLabel())) {
                        lens.setFocalLength(newLens.getFocalLength());
                        lensNotIncluded = false;
                        break;
                    }
                }
                if (lensNotIncluded) {
                    lensList.add(newLens);
                }
            } else {
                ArrayList<Lens> newLensList = new ArrayList<>();
                newLensList.add(newLens);
                hashMap.put(labelNumber, newLensList);
            }
        }

    }

    public static int getTotalFocalPower(HashMap<Integer, ArrayList<Lens>> lensMap) {
        int focalPower = 0;
        for (int i = 0; i < 256; i++) {
            if (lensMap.containsKey(i)) {
                ArrayList<Lens> lensList = lensMap.get(i);
                for (int j = 0; j < lensList.size(); j++) {
                    focalPower += ((i + 1) * (j + 1) * lensList.get(j).getFocalLength());
                }
            }
        }
        return focalPower;
    }


    public static class Lens {
        private String label;
        private int focalLength;

        public Lens(String label, int focalLength) {
            this.label = label;
            this.focalLength = focalLength;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getFocalLength() {
            return focalLength;
        }

        public void setFocalLength(int focalLength) {
            this.focalLength = focalLength;
        }
    }

}
