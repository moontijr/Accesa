package org.example.repository.inmemory;

import org.example.model.Badge;
import org.example.model.User;
import org.example.repository.UserRepository;

import java.io.*;
import java.util.ArrayList;

import org.example.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * a simple Singleton class for Repository, that has the basic CRUD operations with the help of an ID, that takes care of Users
 */

public class UserRepositoryMemory implements UserRepository {

    private static UserRepositoryMemory single_instance = null;

    private final ArrayList<User> users = new ArrayList<>();

    public static UserRepositoryMemory getInstance()
    {
        if (single_instance == null) {
            single_instance=new UserRepositoryMemory();
            populate();
        }
        return single_instance;
    }

    private static void populate()
    {
        String csvFile = "C:\\Users\\munte\\IdeaProjects\\Accesa\\src\\main\\java\\org\\example\\files\\users.csv";
        String line = "";
        String cvsSplitBy=",";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))) {
            while((line = bufferedReader.readLine()) != null)
            {
                String[] fields = line.split(cvsSplitBy);
                User user = new User(fields[0],fields[1],fields[2],fields[3]);
                user.setNumberTokens(Integer.parseInt(fields[4]));
                if(fields.length>5) {
                    String[] badges = fields[5].split(";");
                    for (String badge : badges) {
                        Badge badge1 = BadgeRepositoryMemory.getInstance().findById(badge);
                        user.getBadges().add(badge1);
                    }
                }
                UserRepositoryMemory.getInstance().add(user);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //UserRepositoryMemory.getInstance().add(new User("U1","User","user","1234"));
    }

    @Override
    public void add(User entity) {
        this.users.add(entity);
//        String csvFile = "C:\\Users\\munte\\IdeaProjects\\Accesa\\src\\main\\java\\org\\example\\files\\users.csv";
//        String[] values = {entity.getId(),entity.getName(),entity.getEmail(),entity.getPassword()};
//        try {
//            FileWriter csvWriter = new FileWriter(csvFile, true);
//            csvWriter.append(String.join("," , values));
//            csvWriter.append("\n");
//            csvWriter.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }

    @Override
    public void remove(String s) {
        this.users.remove(findById(s));
    }

    @Override
    public void update(String s, User newEntity) {
        this.findById(s).setName(newEntity.getName());
        this.findById(s).setEmail(newEntity.getEmail());
        this.findById(s).setNumberTokens(newEntity.getNumberTokens());
        this.findById(s).setPassword(newEntity.getPassword());
        this.findById(s).setBadges(newEntity.getBadges());
        this.findById(s).setId(newEntity.getId());
    }

    public static UserRepositoryMemory getSingle_instance() {
        return single_instance;
    }

    public static void setSingle_instance(UserRepositoryMemory single_instance) {
        UserRepositoryMemory.single_instance = single_instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public User findById(String s) {
        for(User user : users)
            if (s.equals(user.getEmail()))
                return user;
        return null;
    }
}
