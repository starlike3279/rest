package com.example.rest.domain.article.dto;

import com.example.rest.domain.article.entity.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleDTO {
    private final Long id;
    private final String subject;
    private final String content;
    private final String author;
    private final LocalDateTime createDate;
    private final LocalDateTime modifiedDate;

    public ArticleDTO(Article article) {
        this.id = getId();
        this.subject = getSubject();
        this.content = getContent();
        this.author = article.getMember().getUsername();
        this.createDate = getCreateDate();
        this.modifiedDate = getModifiedDate();
    }
}

