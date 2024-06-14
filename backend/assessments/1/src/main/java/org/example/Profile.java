package org.example;

public class Profile {
    //Name,Team,Role,Matches,Runs,Average,SR,Wickets
    private String playerName;
    private String team;
    private String role;
    private int matches;
    private int runs;
    private double average;
    private double strikeRate;
    private int wickets;

    public Profile(String playerName, String team, String role, int matches, int runs, double average, double strikeRate, int wickets) {
        this.playerName = playerName;
        this.team = team;
        this.role = role;
        this.matches = matches;
        this.runs = runs;
        this.average = average;
        this.strikeRate = strikeRate;
        this.wickets = wickets;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getRuns() {
        return runs;
    }

    public int getWickets() {
        return wickets;
    }

    public String getTeam() {
        return team;
    }

    public double getAverage() {
        return average;
    }

    @Override
    public String toString() {

        return "Profile{" +
                "name : " + playerName +
                "team : " + team +
                "role : " + role +
                "matches : " + matches +
                "runs : " + runs +
                "average : " + average +
                "strike rate : " + strikeRate +
                "wicket : " + wickets +
                '}';

    }
}
