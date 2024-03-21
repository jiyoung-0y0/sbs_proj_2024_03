package org.example.dto;

public class member extends dto {
    public int id;
    public String regDate;
    public String loginId;
    public String loginPw;
    public String name;

    public member(int id, String regDate, String loginId, String loginPw, String name) {

        this.id = id;
        this.regDate = regDate;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
    }

    public member(int id, String regDate, String title, String body) {
        this(id, regDate, title, body, 0);

    }
}


