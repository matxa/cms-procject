package com.cms.cmsapi.controller;

import com.cms.cmsapi.model.Article;
import com.cms.cmsapi.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/articles", method = RequestMethod.GET)
public class ArticlesController {
    private final ArticleService articleService;

    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/article")
    public ResponseEntity createArticle(@RequestBody Article article) {
        HashMap response = this.articleService.insertArticle(article);
        return ResponseEntity.status((int) response.get("statusCode")).body(response);
    }

    @GetMapping("/article/get/{id}")
    public ResponseEntity getArticleById(@PathVariable String id) {
        HashMap response = this.articleService.getArticleById(id);
        return ResponseEntity.status((int) response.get("statusCode")).body(response);
    }

    @DeleteMapping("/article/delete/{id}")
    public ResponseEntity deleteArticleById(@PathVariable String id) {
        HashMap response = this.articleService.deleteArticle(id);
        return ResponseEntity.status((int) response.get("statusCode")).body(response);
    }

    @GetMapping("/author/{email}")
    public ResponseEntity getArticlesByEmail(@PathVariable String email) {
        HashMap response = this.articleService.getArticlesByEmail(email);
        return ResponseEntity.status((int) response.get("statusCode")).body(response);
    }
}
