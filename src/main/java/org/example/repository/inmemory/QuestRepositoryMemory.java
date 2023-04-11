package org.example.repository.inmemory;

import org.example.model.Quest;
import org.example.repository.QuestRepository;

import java.util.ArrayList;

public class QuestRepositoryMemory implements QuestRepository {
    private static org.example.repository.inmemory.QuestRepositoryMemory single_instance=null;

    private final ArrayList <Quest> quests= new ArrayList<>();

    public static QuestRepositoryMemory getInstance()
    {
        if(single_instance == null)
        {
            single_instance=new org.example.repository.inmemory.QuestRepositoryMemory();
            populate();
        }
        return single_instance;
    }

    private static void populate()
    {
        org.example.repository.inmemory.QuestRepositoryMemory.getInstance().add(new Quest("Q1","Quest1"));
    }
    @Override
    public void add(Quest entity) {
        this.quests.add(entity);

    }

    @Override
    public void remove(String s) {
        this.quests.remove(findById(s));

    }

    @Override
    public void update(String s, Quest newEntity) {
        this.findById(s).setName(newEntity.getName());
        this.findById(s).setId(newEntity.getId());

    }

    @Override
    public Quest findById(String s) {
        for(Quest quest : quests)
            if(s.equals(quest.getId()))
                return quest;
        return null;
    }
}
