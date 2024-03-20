package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("== 프로그램 시작 ==");
        Scanner sc = new Scanner(System.in);


        int lastArticleId = 0;
        List<Article> articles = new ArrayList<>();

        while (true){
            System.out.printf("명령어: ");
            String cmd = sc.nextLine();
            cmd = cmd.trim(); // 공백 정렬

            if(cmd.length()==0){
                continue;
            }
            else if (cmd.startsWith("exit")){
                break;
            }
                if(cmd.equals("article write")){
                int id = lastArticleId +1;
                lastArticleId = id;
                System.out.printf("제목: ");
                String title = sc.nextLine();
                System.out.printf("내용: ");
                String body = sc.nextLine();
                Article article = new Article(id, title, body);
                articles.add(article);
                System.out.printf("%d번 글이 생성되었습니다.\n",id);

            }
            else if (cmd.equals("article list")){
                if(articles.size()==0){
                    System.out.println("게시물이 없습니다.");
                    continue;
                }
                System.out.println("번호 | 제목");
                for(int i = articles.size()-1; i>=0 ; i--){
                    Article article = articles.get(i);

                    System.out.printf("%d   |  %s\n", article.id, article.title);

                }
            }
            else if (cmd.startsWith("article detail")){
                String[] cmdBits = cmd.split(" ");
                int id = Integer.parseInt(cmdBits[2]);

                    Article foundArticle = null;

                    for (int i=0; i<articles.size(); i++){
                        Article article = articles.get(i);

                        if(article.id == id){
                            foundArticle = article;
                            break;
                        }
                    }
                    if (foundArticle == null){
                        System.out.printf("%d번 게시물은 존재하지 않습니다.\n",id);
                        continue;
                    }

                    System.out.printf("번호: %d\n",foundArticle.id);
                    System.out.printf("날짜: %s\n", "2024-03-20 12:12:12");
                    System.out.printf("제목: %s\n",foundArticle.title);
                    System.out.printf("내용: %s\n",foundArticle.body);
                }
                else if (cmd.startsWith("article delete")){
                    String[] cmdBits = cmd.split(" ");
                    int id = Integer.parseInt(cmdBits[2]);

                    int foundIndex = -1;

                    for (int i=0; i<articles.size(); i++){
                        Article article = articles.get(i);

                        if(article.id == id){
                            foundIndex = -1;
                            break;
                        }
                    }
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
}
class Article{
    int id;
    String title;
    String body;

    public Article(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }
}