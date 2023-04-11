package org.example.repository.inmemory;

import org.example.model.Ranking;
import org.example.model.User;
import org.example.repository.RankingRepository;

import java.util.ArrayList;

public class RankingRepositoryMemory implements RankingRepository {

    private static RankingRepositoryMemory single_instance = null;

    private final ArrayList <Ranking> rankings = new ArrayList<>();

    public static RankingRepositoryMemory getInstance(){
        if (single_instance == null)
        {
            single_instance=new RankingRepositoryMemory();
            populate();
        }
        return single_instance;
    }

    private static void populate(){
        RankingRepositoryMemory.getInstance().add(new Ranking(new ArrayList<User>(),"R1"));
    }
    @Override
    public void add(Ranking entity) {
        this.rankings.add(entity);
    }

    @Override
    public void remove(String s) {
        this.rankings.remove(findById(s));

    }

    @Override
    public void update(String s, Ranking newEntity) {
        this.findById(s).setUsers(newEntity.getUsers());
        this.findById(s).setId(newEntity.getId());
    }

    @Override
    public Ranking findById(String s) {
        for (Ranking ranking : this.rankings)
            if (s.equals(ranking.getId()))
                return ranking;
        return null;
    }
}
