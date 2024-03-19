package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("== 프로젝트 시작 ==");
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        String cm = sc.nextLine();
        int num = sc.nextInt();
        System.out.printf("입력된 명령어 : %s %s %d\n", cmd, cm, num);
        sc.close();
        System.out.println("== 프로젝트 끝 ==");
    }
}