package com.example.rest.domain.article.controller;

import com.example.rest.domain.article.dto.ArticleDTO;
import com.example.rest.domain.article.entity.Article;
import com.example.rest.domain.article.request.ArticleCreateRequest;
import com.example.rest.domain.article.request.ArticleModifyRequest;
import com.example.rest.domain.article.response.ArticleCreateResponse;
import com.example.rest.domain.article.response.ArticleModifyResponse;
import com.example.rest.domain.article.response.ArticleResponse;
import com.example.rest.domain.article.response.ArticlesResponse;
import com.example.rest.domain.article.service.ArticleService;
import com.example.rest.global.RsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
@Tag(name = "ApiV1ArticleController", description = "게시글 CRUD API")
public class ApiV1ArticleController {

    private final ArticleService articleService;

    @GetMapping("")
    @Operation(summary = "게시글 다건 조회")
    public RsData<ArticlesResponse> list() {
        List<ArticleDTO> articleList = this.articleService.getList();
        if (articleList == null) {
            return RsData.of("404", "게시글을 찾을 수 없습니다.", null);
        }
        return RsData.of("200", "게시글 다건 조회 성공", new ArticlesResponse(articleList));
    }

    @GetMapping("/{id}")
    @Operation(summary = "게시글 단건 조회")
    public RsData<ArticleResponse> getArticle(@PathVariable("id") Long id) {
        Article article = this.articleService.getArticle(id);
        if (article == null) {
            return RsData.of("404", "게시글을 찾을 수 없습니다.", null);
        }
        ArticleDTO articleDTO = new ArticleDTO(article);
        return RsData.of("200", "게시글 단건 조회 성공", new ArticleResponse(articleDTO));
    }

    @PostMapping("")
    @Operation(summary = "게시글 등록")
    public RsData<ArticleCreateResponse> create(@Valid @RequestBody ArticleCreateRequest articleCreateRequest) {
        Article article = this.articleService.write(articleCreateRequest.getSubject(), articleCreateRequest.getContent());
        return RsData.of("200", "등록 성공", new ArticleCreateResponse(article));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "게시글 수정")
    public RsData<ArticleModifyResponse> modify(@PathVariable("id") Long id, @Valid @RequestBody ArticleModifyRequest articleModifyRequest) {
        Article article = this.articleService.getArticle(id);
        if (article == null) {
            return RsData.of("404", "게시글을 찾을 수 없습니다.", null);
        }
        article = this.articleService.update(article, articleModifyRequest.getSubject(), articleModifyRequest.getContent());
        return RsData.of("200", "수정 성공", new ArticleModifyResponse(article));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "게시글 삭제")
    public RsData<String> delete(@PathVariable("id") Long id) {
        boolean isDeleted = this.articleService.delete(id);
        if (!isDeleted) {
            return RsData.of("404", "게시글을 찾을 수 없습니다.", null);
        }
        return RsData.of("200", "삭제 성공", null);
    }

}
