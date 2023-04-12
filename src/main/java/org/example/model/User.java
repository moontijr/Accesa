package org.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * a simple User class, that has information such as id, name , email, password,number of tokens, and a list of badges, for each user to enter our app
 */

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private int numberTokens;
    private List <Badge> badges = new ArrayList<>();

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumberTokens() {
        return numberTokens;
    }

    public void setNumberTokens(int numberTokens) {
        this.numberTokens = numberTokens;
    }

    public void addBadge(Badge badge)
    {
        this.badges.add(badge);
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }
}
