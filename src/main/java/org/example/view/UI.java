package org.example.view;

import org.example.controller.UserController;
import org.example.model.Badge;
import org.example.model.Quest;
import org.example.model.User;
import org.example.repository.QuestRepository;
import org.example.repository.inmemory.BadgeRepositoryMemory;
import org.example.repository.inmemory.QuestRepositoryMemory;
import org.example.repository.inmemory.RankingRepositoryMemory;
import org.example.repository.inmemory.UserRepositoryMemory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UI {

    private final Scanner userInput;

    private final BadgeRepositoryMemory badgeRepositoryMemory;

    private final QuestRepositoryMemory questRepositoryMemory;

    private final RankingRepositoryMemory rankingRepositoryMemory;

    private final UserRepositoryMemory userRepositoryMemory;

    private final UserController userController;

    public UI(Scanner userInput, BadgeRepositoryMemory badgeRepositoryMemory, QuestRepositoryMemory questRepositoryMemory, RankingRepositoryMemory rankingRepositoryMemory, UserRepositoryMemory userRepositoryMemory, UserController userController)
    {
        this.userInput=userInput;
        this.badgeRepositoryMemory=badgeRepositoryMemory;
        this.questRepositoryMemory=questRepositoryMemory;
        this.rankingRepositoryMemory=rankingRepositoryMemory;
        this.userRepositoryMemory=userRepositoryMemory;
        this.userController=userController;
    }

    public void loginMenu()
    {
        System.out.println("""
                Welcome to our application!
                1.Login
                2.Register
                3.Exit
                """);
        boolean validator = false;
        try {
            int choice = this.userInput.nextInt();
            while (!validator) {
                if (choice > 0 && choice <4)
                    validator=true;
                else
                {
                    System.out.println("Please provide a number between 0 and 3");
                    this.loginMenu();
                    choice=this.userInput.nextInt();
                }
            }
            switch (choice) {
                case 1-> {
                    this.userInput.nextLine();
                    System.out.println("Email:");
                    String email = this.userInput.nextLine();
                    System.out.println("Password:");
                    String password = this.userInput.nextLine();
                    for(User user : userRepositoryMemory.getUsers())
                        if(userRepositoryMemory.findById(email)==null)
                        {
                            System.out.println("No such email in our System");
                            System.out.println(userRepositoryMemory.getUsers().size());
                            loginMenu();
                        }
                        else
                        {
                            if(Objects.equals(password, userRepositoryMemory.findById(email).getPassword()))
                            {
                                userMenu(userRepositoryMemory.findById(email));
                            }
                            else
                            {
                                System.out.println("Incorrect Password");
                                loginMenu();
                            }
                        }
                }

                case 2 -> {
                    this.userInput.nextLine();
                    System.out.println("Now we are gonna ask you about your account creation information");
                    System.out.println("Name: ");
                    String name = this.userInput.nextLine();
                    System.out.println("Email: ");
                    String email = this.userInput.nextLine();
                    System.out.println("Password: ");
                    String password = this.userInput.nextLine();
                    if(userRepositoryMemory.findById(email)!=null)
                    {
                        System.out.println("Email Already in Use");
                        this.loginMenu();
                    }
                    else
                    {
                        User user=new User("User" + userRepositoryMemory.getUsers().size(), name , email , password);
                        userRepositoryMemory.add(user);
                        String csvFile = "C:\\Users\\munte\\IdeaProjects\\Accesa\\src\\main\\java\\org\\example\\files\\users.csv";
                        String[] values = {user.getId(),user.getName(),user.getEmail(),user.getPassword(),"0"};
                        try {
                            FileWriter csvWriter = new FileWriter(csvFile, true);
                        csvWriter.append(String.join("," , values));
                        csvWriter.append("\n");
                        csvWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
                        userMenu(user);
                    }

                }
                case 3->{
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void userMenu(User user)
    {
        System.out.println("""
                1.See your Tokens
                2.See your badges
                3.See your ranking
                4.Create a Quest
                5.Solve a Quest
                6.Go back
                7.Exit
                """);

        boolean validator = false;
        try
        {
            int choice=this.userInput.nextInt();
            while(!validator) {
                if(choice > 0 && choice <8 )
                    validator=true;
                else
                {
                    System.out.println("Please provide a number between 1 and 7");
                    this.userMenu(user);
                    choice=this.userInput.nextInt();
                }
            }
            switch (choice)
            {
                case 1-> {
                    System.out.println ((user.getNumberTokens() + "tokens"));
                    this.userMenu(user);
                }
                case 2->{
                    if(user.getBadges()==null)
                    {
                        System.out.println("No such badges");
                    }
                    else {
                        for (Badge badge : user.getBadges())
                            System.out.println(badge.getName() + " | " + badge.getType());
                    }
                    this.userMenu(user);
                }
                case 3->{
                    List<User> users = rankingRepositoryMemory.getRanking().getUsers();
                    users.sort(Comparator.comparing(User::getNumberTokens));
                    for(User user1 : users)
                        System.out.println(user1.getName() + " | " + user1.getNumberTokens() + "tokens ");
                    this.userMenu(user);
                }
                case 4-> {
                    this.userInput.nextLine();
                    System.out.println("Quest Name :");
                    String questName = this.userInput.nextLine();
                    System.out.println("Points:");
                    int points=this.userInput.nextInt();

                    int size=questRepositoryMemory.getQuests().size()+1;
                    Quest quest = new Quest("Q" + size , questName,points);
                    questRepositoryMemory.add(quest);
                    String csvFile = "C:\\Users\\munte\\IdeaProjects\\Accesa\\src\\main\\java\\org\\example\\files\\quests.csv";
                    String[] values = {quest.getId(), quest.getName(), String.valueOf(quest.getPoints())};
                    try {
                        FileWriter csvWriter = new FileWriter(csvFile, true);
                        csvWriter.append(String.join(",", values));
                        csvWriter.append("\n");
                        csvWriter.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    this.userMenu(user);
                }

                    case 5->{
                    System.out.println("Which quest of the following would you like to solve? Just type the Name of the Quest you wish to complete");
                    for(Quest quest : questRepositoryMemory.getQuests())
                    {
                        System.out.println(quest.getName() + " | " + quest.getPoints() + " Reward Points ");
                    }

                    this.userInput.nextLine();
                    String name = this.userInput.nextLine();
                    if(questRepositoryMemory.findByName(name) == null)
                    {
                        System.out.println("No such Quest");
                    }
                    else {
                        this.userController.completeQuest(user, questRepositoryMemory.findByName(name));
                        this.userController.refreshBadgesAndTokens(user);
                        this.userMenu(user);
                    }



                }
                case 6->{
                        this.loginMenu();
                }

                case 7->{
                    System.exit(0);

                }


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
