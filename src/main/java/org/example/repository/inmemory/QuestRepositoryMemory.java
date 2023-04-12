package org.example.repository.inmemory;

import org.example.model.Badge;
import org.example.model.Quest;
import org.example.repository.QuestRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    public static QuestRepositoryMemory getSingle_instance() {
        return single_instance;
    }

    public static void setSingle_instance(QuestRepositoryMemory single_instance) {
        QuestRepositoryMemory.single_instance = single_instance;
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    private static void populate()
    {
        //org.example.repository.inmemory.QuestRepositoryMemory.getInstance().add(new Quest("Q1","Quest1"));
        String csvFile = "C:\\Users\\munte\\IdeaProjects\\Accesa\\src\\main\\java\\org\\example\\files\\quests.csv";
        String line = "";
        String cvsSplitBy=",";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))) {
            while((line = bufferedReader.readLine()) != null)
            {
                String[] fields = line.split(cvsSplitBy);
                Quest quest = new Quest(fields[0],fields[1],Integer.parseInt(fields[2]));
                QuestRepositoryMemory.getInstance().add(quest);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public Quest findByName(String name)
    {
        for(Quest quest : quests)
            if(name.equals(quest.getName()))
                return quest;
        return null;
    }
}
