package org.example.repository.inmemory;

import org.example.model.User;
import org.example.repository.UserRepository;

import java.util.ArrayList;

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
        UserRepositoryMemory.getInstance().add(new User("U1","User","user","1234"));
    }

    @Override
    public void add(User entity) {
        this.users.add(entity);
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

    @Override
    public User findById(String s) {
        for(User user : users)
            if (s.equals(user.getId()))
                return user;
        return null;
    }
}
