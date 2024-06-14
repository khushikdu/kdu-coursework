package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class Operations {
    public List<Profile> loadCSVData(String filePath) {
        List<Profile> profiles = new ArrayList<>();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).withSkipLines(1).build()) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                try {
                    String playerName=line[0];
                    String team=line[1];
                    String role=line[2];
                    int matches=Integer.parseInt(line[3]);
                    int runs=Integer.parseInt(line[4]);
                    double average=isNumeric(line[5]) ? Double.parseDouble(line[5]) : 0.0;
                    double strikeRate=isNumeric(line[6]) ? Double.parseDouble(line[6]) : 0.0;
                    int wickets=Integer.parseInt(line[7]);
                    Profile profile=new Profile(playerName,team,role,matches,runs,average,
                            strikeRate,wickets);
                    profiles.add(profile);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing csv file: " + e.getMessage());
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return profiles;
    }


    public List<Profile> getBowler(List<Profile> profiles) {
        return profiles.stream()
                .filter(profile -> profile.getWickets()>40)
                .collect(Collectors.toList());

    }
    public List<Profile> getTopBowlers(List<Profile> profiles,int topN) {
        return profiles.stream()
                .sorted(Comparator.comparingInt(Profile::getWickets).reversed())
                .limit(topN)
                .collect(Collectors.toList());

    }
    public List<Profile> getTopBatters(List<Profile> profiles,int topN) {
        return profiles.stream()
                .sorted(Comparator.comparingInt(Profile::getRuns).reversed())
                .limit(topN)
                .collect(Collectors.toList());

    }
    public List<Profile> getBestBowler(List<Profile> profiles,String team) {
        try {
            return profiles.stream()
                    .filter(profile -> profile.getTeam().equalsIgnoreCase(team))
                    .sorted(Comparator.comparingInt(Profile::getWickets).reversed())
                    .limit(1)
                    .collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<Profile> getBestBatsman(List<Profile> profiles,String team) {
        try {
        return profiles.stream()
                .filter(profile -> profile.getTeam().equalsIgnoreCase(team))
                .sorted(Comparator.comparingDouble(Profile::getAverage).reversed())
                .limit(1)
                .collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
