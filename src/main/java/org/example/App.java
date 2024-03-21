package org.example;

import org.example.dto.Article;
import org.example.util.util;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private List<Article> articles;
    private List<Article> members;
    public App() {
        articles = new ArrayList<>();
        members = new ArrayList<>();
    }
    public void start() {

        System.out.println("== 프로그램 시작 ==");
        makeTestData();

        Scanner sc = new Scanner(System.in);
        int id = members.size() +1;
        String regDate = util.getNowDateStr();
        System.out.printf("로그인 아이디: ");
        String loginId = sc.nextLine();
        String loginPw = null;
        String loginPwConfirm = null;

        // article.size();
        while (true){
            System.out.printf("명령어: ");
            String cmd = sc.nextLine();
            cmd = cmd.trim(); // 공백 정렬

            System.out.printf("로그인 비번: ");
            String loginPw = sc.nextLine();
            System.out.printf("로그인 비번확인: ");
            String loginPwConfirm = sc.nextLine();

            if (loginPw.equals(loginPwConfirm)==false){
                System.out.println("비밀번호가 일치하지 않습니다. 다시 작성해주세요.");
                continue;
            }
            break;
            if(cmd.length()==0){
                continue;
            }
            if (cmd.startsWith("exit")){
                break;
            }
            if(cmd.equals("member join")){
                int id = members.size() +1;
                String regDate = util.getNowDateStr();
                while (true){
                    System.out.println("로그인 아이디: ");
                    String loginId = sc.nextLine();
                    if (isJoinableLoginId(loginId)==false){
                        System.out.println("사용중인 로그인 아이디가 아닙니다.");
                    }


                }

                System.out.printf("로그인 아이디: ");
                String loginId = sc.nextLine();
                System.out.printf("이름: ");
                String name = sc.nextLine();

                Member member = new Member(id, regDate, loginId, loginPw, name);
                members.add(article);


            }
            if(cmd.equals("article write")){
                int id = articles.size() +1;
                String regDate = util.getNowDateStr();
                System.out.printf("제목: ");
                String title = sc.nextLine();
                System.out.printf("내용: ");
                String body = sc.nextLine();

                Article article = new Article(id, regDate, title,body);

                articles.add(article);
                System.out.printf("%d번 글이 생성되었습니다.\n",id);

            }
            else if (cmd.startsWith("article list")){
                if(articles.size()==0){
                    System.out.println("게시물이 없습니다.");
                    continue;
                }
                String searchKeyword = cmd.substring("article list".length()).trim();

                List<Article> forListArticles = articles;

                if (searchKeyword.length()>0){
                    forListArticles = new ArrayList<>();

                    for (Article article: articles){
                        if (article.title.contains(searchKeyword)){
                            forListArticles.add(article);
                        }
                    }
                    if(articles.size()==0){
                        System.out.println("검색 결과가 존재하지 않습니다.");
                        continue;
                    }
                }
                System.out.println(" 번호 | 조회 | 제목 ");
                for(int i = forListArticles.size()-1; i>=0 ; i--){
                    Article article = forListArticles.get(i);

                    System.out.printf("%4d | %4d | %s\n", article.id,article.hit, article.title);

                }
            }
            else if (cmd.startsWith("article modify")){
                String[] cmdBits = cmd.split(" ");
                int id = Integer.parseInt(cmdBits[2]);
                Article foundArticle = getArticleById(id);

                if (foundArticle == null){
                    System.out.printf("%d번 게시물은 존재하지 않습니다.\n",id);
                    continue;
                }

                System.out.printf("제목: ");
                String title = sc.nextLine();
                System.out.printf("내용: ");
                String body = sc.nextLine();
                foundArticle.title = title;
                foundArticle.body = body;

                System.out.printf("%d번 게시물이 수정되었습니다.\n",id);
            }
            else if (cmd.startsWith("article detail")){
                String[] cmdBits = cmd.split(" ");
                int id = Integer.parseInt(cmdBits[2]);

                Article foundArticle = getArticleById(id);


                if (foundArticle == null){
                    System.out.printf("%d번 게시물은 존재하지 않습니다.\n",id);
                    continue;
                }
                foundArticle.increaseHit();

                System.out.printf("번호: %d\n",foundArticle.id);
                System.out.printf("날짜: %s\n", foundArticle.regDate);
                System.out.printf("제목: %s\n",foundArticle.title);
                System.out.printf("내용: %s\n",foundArticle.body);
                System.out.printf("조회: %d\n",foundArticle.hit);

            }
            else if (cmd.startsWith("article delete")){
                String[] cmdBits = cmd.split(" ");
                int id = Integer.parseInt(cmdBits[2]);

                int foundIndex = getArticleIndexById(id);


                if (foundIndex == -1){
                    System.out.printf("%d번 게시물은 존재하지 않습니다.\n",id);
                    continue;
                }
                // size() => 3
                // index: 0,1,2
                // id   : 1,2,3
                articles.remove(id-1);
                System.out.printf("%d번 게시물이 삭제되었습니다.\n",id);
            }
            else{
                System.out.printf("%s는 존재하지 않는 명령어 입니다.\n",cmd);
            }
        }
        System.out.println("== 프로그램 끝 ==");
        sc.close();
    }

    private boolean isJoinableLoginId(String loginId) {
        int index = getMemberIddexByLogin(loginId);
    }
    private int getArticleIndexById(int id) {
        int i =0;
        for(Member member : members){
            if (member.loginId.equals()){
                return i;
            }
            i++;
        }
        return -1;
    }
    private int getArticleIndexById(int id) {
        int i =0;
        for(Article article : articles){
            if (article.id == id){
                return i;
            }
            i++;
        }
        return -1;
    }

    private Article getArticleById(int id) {
        int index = getArticleIndexById(id);
        if(index != -1){
            return articles.get(index);
            }
        return null;
    }

    private void makeTestData() {
        System.out.println("테스트를 위한 게시물 데이터를 생성합니다.");
        articles.add(new Article(1, util.getNowDateStr(), "제목 1", "내용1",12));
        articles.add(new Article(2, util.getNowDateStr(), "제목 2", "내용2",13));
        articles.add(new Article(3, util.getNowDateStr(), "제목 3", "내용3",43));
    }
}
