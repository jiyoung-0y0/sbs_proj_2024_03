package org.example.controller;

import org.example.Container;
import org.example.dto.Member;
import org.example.util.util;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller{
    private Scanner sc;
    private List<Member> members;
    private String cmd;
    private String actionMethodName;

    public MemberController(Scanner sc){
        this.sc = sc;
        members = Container.memberDao.members;
    }
    public void doAction(String cmd, String actionMethodName){
        this.cmd = cmd;
        this.actionMethodName = actionMethodName;

        switch (actionMethodName){
            case "join":
            dojoin();
            break;
            case "login":
                doLogin();
                break;
            case "logout":
                doLoginout();
                break;
            default:
                System.out.println("존재하지 않는 명령어 입니다.");
                break;
        }
        }



    public void makeTestData() {
        System.out.println("테스트를 위한 회원 데이터를 생성합니다");

        Container.memberDao.add(new Member(Container.memberDao.getNewId(), util.getNowDateStr(), "admin", "admin", "관리자"));
        Container.memberDao.add(new Member(Container.memberDao.getNewId(), util.getNowDateStr(), "user1", "user1", "홍길동"));
        Container.memberDao.add(new Member(Container.memberDao.getNewId(), util.getNowDateStr(), "user2", "user2", "홍길순"));
    }

    public void dojoin() {
        int id = Container.memberDao.getNewId();
        String regDate = util.getNowDateStr();
        String loginId = null;

        while (true) {
            System.out.println("로그인 아이디: ");
            loginId = sc.nextLine();

            if (isJoinableLoginId(loginId) == false) {
                System.out.printf("%s 사용중인 로그인 아이디입니다.", loginId);
                continue;
            }
            break;
        }

        String loginPw = null;
        String loginPwConfirm = null;

        while (true) {
            System.out.printf("로그인 비번 : ");
            loginPw = sc.nextLine();
            System.out.printf("로그인 비번확인 : ");
            loginPwConfirm = sc.nextLine();

            if (loginPw.equals(loginPwConfirm) == false) {
                System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
                continue;
            }

            break;
        }

        System.out.printf("이름 : ");
        String name = sc.nextLine();

        Member member = new Member(id, regDate, loginId, loginPw, name);
        Container.memberDao.add(member);

        System.out.printf("%d번 회원이 생성되었습니다. 환영합니다!\n", id);
    }
    public void doLogin(){


        System.out.println("로그인 아이디:");
        String loginId = sc.nextLine();
        System.out.println("로그인 비번:");
        String loginPw = sc.nextLine();
        // 입력받은 아이디에 해당하는 회원이 존재하는지

        Member member = getMemberByLoginId(loginId);
        if(member == null){
            System.out.println("해당회원은 존재하지 않습니다.");
            return;
        }
        if (member.loginPw.equals(loginPw)==false){
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }
        loginedMember = member;
        System.out.printf("로그인 성공! %s님 환영합니다.\n",loginedMember.name);
    }


    private void doLoginout() {

         loginedMember = null;
            System.out.println("로그아웃 되었습니다.");
    }
    private boolean isJoinableLoginId(String loginId) {
        int index = getMemberIndexByLoginId(loginId);

        if (index == -1) {
            return true;
        }
        return false;
    }


    private int getMemberIndexByLoginId(String loginId) {
        int i = 0;

        for (Member member : members) {
            if (member.loginId.equals(loginId)) {
                return i;
            }
            i++;
        }

        return -1;
    }
    private Member getMemberByLoginId(String loginId){
        int index = getMemberIndexByLoginId(loginId);
        if (index == -1){
            return null;
        }
        return members.get(index);
    }
}

