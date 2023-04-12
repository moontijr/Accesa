package org.example;

import org.example.controller.UserController;
import org.example.repository.inmemory.BadgeRepositoryMemory;
import org.example.repository.inmemory.QuestRepositoryMemory;
import org.example.repository.inmemory.RankingRepositoryMemory;
import org.example.repository.inmemory.UserRepositoryMemory;
import org.example.view.UI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BadgeRepositoryMemory badgeRepositoryMemory = BadgeRepositoryMemory.getInstance();
        QuestRepositoryMemory questRepositoryMemory = QuestRepositoryMemory.getInstance();
        RankingRepositoryMemory rankingRepositoryMemory = RankingRepositoryMemory.getInstance();
        UserRepositoryMemory userRepositoryMemory = UserRepositoryMemory.getInstance();
        Scanner scanner = new Scanner(System.in);
        UserController userController=new UserController(userRepositoryMemory,questRepositoryMemory,rankingRepositoryMemory);

        UI ui = new UI(scanner,badgeRepositoryMemory,questRepositoryMemory,rankingRepositoryMemory,userRepositoryMemory,userController);
        ui.loginMenu();

    }
}