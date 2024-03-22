package org.example;

import org.example.controller.ArticleController;
import org.example.controller.MemberController;
import org.example.dto.Article;
import org.example.util.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private List<Article> articles;
    public App() {
        articles = new ArrayList<>();
    }
    public void start() {

        System.out.println("== 프로그램 시작 ==");

        makeTestData();

        Scanner sc = new Scanner(System.in);
        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        // article.size();
        while (true) {
            System.out.printf("명령어: ");
            String cmd = sc.nextLine();
            cmd = cmd.trim(); // 공백 정렬


            if (cmd.length() == 0) {
                continue;
            }
            if (cmd.startsWith("exit")) {
                break;
            }
            if (cmd.equals("member join")) {
                memberController.dojoin();
            }

        else if (cmd.equals("article write")) {
               articleController.doWrite();

            }
        else if (cmd.startsWith("article list")) {
                articleController.showList(cmd);
                }

        else if (cmd.startsWith("article modify")) {
                articleController.doModify(cmd);

            } else if (cmd.startsWith("article detail")) {
                articleController.showdetail(cmd);


            } else if (cmd.startsWith("article delete")) {
                articleController.dodelete(cmd);
        }
            else {
                System.out.printf("%s(은)는 존재하지 않는 명령어 입니다.\n", cmd);
            }
        sc.close();
        System.out.println("== 프로그램 끝 ==");
    }
    }
    private void makeTestData() {
        System.out.println("테스트를 위한 게시물 데이터를 생성합니다");
        articles.add(new Article(1, util.getNowDateStr(), "제목 1", "내용 1", 12));
        articles.add(new Article(2, util.getNowDateStr(), "제목 2", "내용 2", 103));
        articles.add(new Article(3, util.getNowDateStr(), "제목 3", "내용 3", 3));
    }
}