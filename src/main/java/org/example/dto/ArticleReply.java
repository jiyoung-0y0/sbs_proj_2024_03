package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleReply extends dto {
    private  int articleId;
    private int memberId;
    private String body;
}