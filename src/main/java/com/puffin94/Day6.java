package com.puffin94;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day6 {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/main/resources/challengeInputs/day6.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] times = new String[4];
            int realTime = 0;
            long realDistance = 0;
            String[] distances = new String[4];
            while ((line = br.readLine()) != null) {
                if (line.contains("Time")) {
                    times = line.replace("Time:", "").trim().replace("     ", ",").split(",");
                    realTime = Integer.parseInt(line.replace("Time:", "").trim().replace(" ", "").trim());
                }
                if (line.contains("Distance:")) {
                    distances = line.replace("Distance:", "").trim().replace("   ", ",").split(",");
                    realDistance = Long.parseLong(line.replace("Distance:", "").trim().replace(" ", "").trim());
                }
            }
            int totalRaceNumber = 0;
            for (int i = 0; i < times.length; i++) {
                ArrayList<Integer> recordBeatingTimes = new ArrayList<>();
                int timeInRace = Integer.parseInt(times[i]);
                int recordDistance = Integer.parseInt(distances[i]);
                for (int j = 0; j <= timeInRace; j++) {
                    if(j*(timeInRace-j)>recordDistance){
                        recordBeatingTimes.add(j);
                    }
                }
                if(totalRaceNumber ==0){
                    totalRaceNumber = recordBeatingTimes.size();
                }else{
                    totalRaceNumber *= recordBeatingTimes.size();
                }

            }
            int count = 0;
            for (long j = 0; j <= realTime; j++) {
                if( j *(realTime-j)>realDistance){
                    count++;
                }
            }

            System.out.println("part 1 answer: "+totalRaceNumber);
            System.out.println("part 2 answer: "+count);
        } catch (Exception e) {
            System.err.println("Issue Reading Challenge file");
            System.err.println(e);
        }
    }

}
