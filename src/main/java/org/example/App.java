package org.example;

import org.example.container.Container;
import org.example.controller.ArticleController;
import org.example.controller.Controller;
import org.example.controller.ExportController;
import org.example.controller.MemberController;
import org.example.dto.Article;
import org.example.util.util;
import org.example.db.DBConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {
    public App() {
        DBConnection.DB_NAME = "sbs_proj";
        DBConnection.DB_USER = "sbsst";
        DBConnection.DB_PASSWORD = "sbs123414";
        DBConnection.DB_PORT = 3306;

        Container.getDBConnection().connect();
        Container.getSession().setCurrentBoard(Container.articleService.getBoard(2));
    }

    public void start() {

        System.out.println("== 프로그램 시작 ==");
        System.out.println("= 명령어 모음 =");
        System.out.println("1. 회원가입 : member join");
        System.out.println("2. 로그인/로그아웃 : member login/logout");
        System.out.println("3. 현재 게시판 확인 : article currentBoard");
        System.out.println("4. 게시판 변경 : article changeBoard");
        System.out.println("5. 게시판 리스트 : article list");
        System.out.println("6. 게시판 상세 : article detail [게시물 번호]");
        System.out.println("7. 게시판 작성(로그인 후 이용가능) : article write");
        System.out.println("8. 게시판 수정/삭제(로그인 후 이용가능) : article modify/delete [게시물 번호] ");



        MemberController memberController = new MemberController();
        ArticleController articleController = new ArticleController();
        ExportController exportController = new ExportController();

        // article.size();
        while (true) {
            System.out.printf("명령어: ");
            String cmd = Container.getScanner().nextLine();
            cmd = cmd.trim(); // 공백 정렬


            if (cmd.length() == 0) {
                continue;
            }
            if (cmd.startsWith("exit")) {
                break;
            }
            String[] cmdBits = cmd.split(" "); // article write

            if (cmdBits.length == 1){
                System.out.println("존재하지 않는 명령어 입니다.");
                continue;
            }
            String controllerName = cmdBits[0]; // article / member
            String actionMethodName = cmdBits[1]; //write / join
            // article detail 1
            // cmdBits[0]=> article
            // cmdBits[1]=> detail
            // cmdBits[2]=>  1
            Controller controller = null;

            if (controllerName.equals("article")) {
                controller = articleController;
            }

            else if (controllerName.equals("member")) {
                controller = memberController;
            }
            else if (controllerName.equals("export")) {
                controller = exportController;
            }
            else {
                System.out.println("존재하지 않는 명령어입니다.");
                continue;
            }
            String actionName = controllerName + "/" + actionMethodName;
            switch ( actionName){
                case "article/write":
                case "article/delete":
                case "article/modify":
                case "member/logout":
                    if (Container.getSession().isLogined() == false){
                        System.out.println("로그인 후 이용해주세요.");
                        continue;
                    }
                    break;
            }
            switch (actionName){
                case "member/login":
                case "member/join":
                    if (Container.getSession().isLogined() ){
                        System.out.println("로그아웃 후 이용해주세요.");
                        continue;
                    }
                    break;
            }

            controller.doAction(cmd, actionMethodName);
        }

        Container.getDBConnection().close();
        Container.getScanner().close();
        System.out.println("== 프로그램 끝 ==");
    }
    }
