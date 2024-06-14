package org.example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Operations operations=new Operations();
        List<Profile> profiles= operations.loadCSVData("src/main/resources/IPL_2021-data.csv");
        logger.info("CSV data loaded Successfully!");

        while (true) {
            List<Profile> profiles1;
            logger.info("\n1. Retrieve Bowlers\n2. Highest Wicket Taker and Highest run Maker\n3. Top 3 Run Scorrers and Top 3 Wicket Takers\n4.Generate match fixture\5.Exit\nEnter your Choice : ");
            int choice = scanner.nextInt();
            try {

                switch (choice) {
                    case 1:
                        List<Profile> bowler = operations.getBowler(profiles);
                        logger.info("Bowlers having more than 40 wickets");
                        for (Profile profile : bowler)
                            logger.info("Name : {} | Wicktes : {} ", profile.getPlayerName(), profile.getWickets());
                        break;

                    case 2:
                        logger.info("Enter the team name : ");
                        String teamName = scanner.next();
                        profiles1 = operations.getBestBowler(profiles, teamName);
                        if (profiles1 == null) {
                            logger.info("Invalid team name");
                            break;
                        }
                        logger.info("Best Bowler in {} is {} ", teamName, profiles1.get(0).getPlayerName());
                        profiles1 = operations.getBestBatsman(profiles, teamName);
                        logger.info("Best Batsman in {} is {} ", teamName, profiles1.get(0).getPlayerName());
                        break;
                    case 3:
                        profiles1 = operations.getTopBowlers(profiles, 3);
                        logger.info("Top 3 Bowlers :");
                        for (Profile profile : profiles1)
                            logger.info("{}", profile.getPlayerName());

                        profiles1 = operations.getTopBatters(profiles, 3);
                        logger.info("\nTop 3 Batsman : ");
                        for (Profile profile : profiles1)
                            logger.info("{}", profile.getPlayerName());
                        break;
                    case 4:
                        break;
                    case 5:
                        logger.info("Terminating program");
                        return;
                    default:
                        logger.info("Invalid choice.");
                }
            }catch (Exception e){
                logger.info("Exception occured for the choice : {}\n",choice);
            }
        }
    }
}