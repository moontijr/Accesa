package org.example.repository.inmemory;

import org.example.model.Badge;
import org.example.repository.BadgeRepository;

import java.util.ArrayList;

public class BadgeRepositoryMemory implements BadgeRepository {
    private static BadgeRepositoryMemory single_instance=null;

    private final ArrayList <Badge> badges= new ArrayList<>();

    public static BadgeRepositoryMemory getInstance()
    {
        if(single_instance == null)
        {
            single_instance=new BadgeRepositoryMemory();
            populate();
        }
        return single_instance;
    }

    private static void populate()
    {
        BadgeRepositoryMemory.getInstance().add(new Badge("B1","Badge1", Badge.Type.BRONZE));
    }
    @Override
    public void add(Badge entity) {
        this.badges.add(entity);

    }

    @Override
    public void remove(String s) {
        this.badges.remove(findById(s));

    }

    @Override
    public void update(String s, Badge newEntity) {
        this.findById(s).setName(newEntity.getName());
        this.findById(s).setType(newEntity.getType());
        this.findById(s).setId(newEntity.getId());

    }

    @Override
    public Badge findById(String s) {
        for(Badge badge : badges)
            if(s.equals(badge.getId()))
                return badge;
        return null;
    }
}
