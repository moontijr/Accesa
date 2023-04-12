package org.example.controller;

import org.example.model.Badge;
import org.example.model.Quest;
import org.example.model.Ranking;
import org.example.model.User;
import org.example.repository.inmemory.BadgeRepositoryMemory;
import org.example.repository.inmemory.QuestRepositoryMemory;
import org.example.repository.inmemory.RankingRepositoryMemory;
import org.example.repository.inmemory.UserRepositoryMemory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private final UserRepositoryMemory userRepositoryMemory;
    private final QuestRepositoryMemory questRepositoryMemory;

    private final RankingRepositoryMemory rankingRepositoryMemory;

    public UserController(UserRepositoryMemory userRepositoryMemory, QuestRepositoryMemory questRepositoryMemory, RankingRepositoryMemory rankingRepositoryMemory)
    {
        this.userRepositoryMemory=userRepositoryMemory;
        this.questRepositoryMemory=questRepositoryMemory;
        this.rankingRepositoryMemory=rankingRepositoryMemory;
    }

    public boolean addQuest(User user, Quest quest)
    {
        if(user.getNumberTokens() > 10)
            if (user.getBadges().size()>5)
                for(int i=0 ; i< rankingRepositoryMemory.getRanking().getSize();i++)
                {
                    if (i<=10)
                    {
                        for(User user1 : rankingRepositoryMemory.getRanking().getUsers())
                            if(user1 == user) {
                                questRepositoryMemory.add(quest);
                                return true;
                            }
                    }
                }
        return false;
    }

    public void completeQuest(User user, Quest quest)
    {
        int numberTokensAtTheMoment=user.getNumberTokens();
        numberTokensAtTheMoment += quest.getPoints();
        user.setNumberTokens(numberTokensAtTheMoment);
        refreshBadges(user);

    }

    public void refreshBadges(User user)
    {
        String csvFile = "C:\\Users\\munte\\IdeaProjects\\Accesa\\src\\main\\java\\org\\example\\files\\badges.csv";
        String line = "";
        String cvsSplitBy=",";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))) {
            while((line = bufferedReader.readLine()) != null)
            {
                String[] fields = line.split(cvsSplitBy);
                Badge badge = new Badge(fields[0],fields[1], Badge.Type.valueOf(fields[2]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(user.getNumberTokens()>50)
        {
            for(Badge badge : BadgeRepositoryMemory.getInstance().getBadges())
                if(badge.getId().equals("B1") && !user.getBadges().contains(badge))
                {
                    user.addBadge(badge);
                }
        }
        if(user.getNumberTokens()>100)
        {
            for(Badge badge : BadgeRepositoryMemory.getInstance().getBadges())
                if(badge.getId().equals("B2") && !user.getBadges().contains(badge))
                {
                    user.addBadge(badge);
                }

        }
        if(user.getNumberTokens()>150)
        {
            for(Badge badge : BadgeRepositoryMemory.getInstance().getBadges())
                if(badge.getId().equals("B3") && !user.getBadges().contains(badge))
                {
                    user.addBadge(badge);
                }
        }

        if(user.getNumberTokens()>200)
        {
            for(Badge badge : BadgeRepositoryMemory.getInstance().getBadges())
                if(badge.getId().equals("B4"))
                {
                    user.addBadge(badge);
                }
        }
    }

    public void refreshBadgesAndTokens(User user)
    {

                String filename = "C:\\Users\\munte\\IdeaProjects\\Accesa\\src\\main\\java\\org\\example\\files\\users.csv";
                int lineNumber = 2; // The line number you want to update
                String newValue = String.valueOf(user.getNumberTokens());
                // The new value you want to set
                List<String> myList = new ArrayList<String>();
                for(Badge badge : user.getBadges())
                    myList.add(badge.getId());
                String newBadges = String.join(";", myList);
                try {
                    // Create a BufferedReader object to read the CSV file
                    BufferedReader reader = new BufferedReader(new FileReader(filename));

                    // Create a StringBuilder object to hold the updated contents of the CSV file
                    StringBuilder sb = new StringBuilder();

                    String line;
                    boolean found=false;

                    // Use a loop to read each line of the CSV file
                    while ((line = reader.readLine()) != null) {
                        if (line.contains(user.getId())) {
                            // Split the line into an array of values
                            String[] values = line.split(",");

                            // Update the value in the array
                            values[4] = newValue;
                            if (values.length>5)
                            values[5] = newBadges;
                            else
                            {
                                values[4] = values[4] + ","  + newBadges;
                            }



                            // Join the values in the array back into a comma-separated String
                            line = String.join(",", values);
                            found=true;
                        }

                        // Append the line to the StringBuilder object
                        sb.append(line).append("\n");

                    }

                    // Close the BufferedReader object
                    reader.close();

                    // Write the updated contents back to the CSV file
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                    writer.write(sb.toString());
                    writer.close();

                    System.out.println("CSV file updated successfully.");

                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }






