package org.example.model;

import java.util.List;

public class Ranking {

    private String id;
    private List<User> users;

    public Ranking(List<User> users, String id) {
        this.id = id;
        this.users = users;
    }

    public int getSize()
    {
        return users.size();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
