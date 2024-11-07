package com.example.rest.domain.article.response;

import com.example.rest.domain.article.dto.ArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ArticlesResponse {
    private final List<ArticleDTO> articleList;
}
