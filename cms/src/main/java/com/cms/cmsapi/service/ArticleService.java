package com.cms.cmsapi.service;

import com.cms.cmsapi.dao.ArticleDAOImp;
import com.cms.cmsapi.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleDAOImp articleDAO;

    @Autowired
    public ArticleService(ArticleDAOImp articleDAO) {
        this.articleDAO = articleDAO;
    }

    public HashMap getArticlesByEmail(String email) {
        HashMap response = new HashMap();
        List<Article> articles = this.articleDAO.getArticlesByEmail(email);

        if (articles != null) {
            response.put("amount", articles.size());
            response.put("articles", articles);
            response.put("message", "success");
            response.put("statusCode", 200);
        } else {
            response.put("error", "can not find author with specified email");
            response.put("statusCode", 404);
        }
        return response;
    }

    public HashMap getArticleById(String id) {
        HashMap response = new HashMap();
        Article article = this.articleDAO.getArticleById(id);

        if (article != null) {
            response.put("article", article);
            response.put("message", "success");
            response.put("statusCode", 200);
        } else {
            response.put("error", "can not find article with specified id");
            response.put("statusCode", 404);
        }

        return response;
    }

    public HashMap insertArticle(Article article) {
        HashMap response = new HashMap();

        // Validate request body
        if (article.getOwnerEmail() == null
            || article.getTitle() == null || article.getContent() == null
        ) {
            response.put("error", "request body missing one or more keys [ title, content, ownerEmail ]");
            response.put("statusCode", 400);
            return response;
        }

        Article newArticle = this.articleDAO.insertArticle(article);

        if (newArticle != null) {
            response.put("message", "success");
            response.put("statusCode", 201);
            response.put("article", newArticle);
        } else {
            response.put("error", "can not find author with specified email");
            response.put("statusCode", 404);
        }
        return response;
    }

    public HashMap deleteArticle(String id) {
        HashMap response = new HashMap();

        Boolean isDeleted = this.articleDAO.deleteArticle(id);
        if (isDeleted) {
            response.put("message", "success");
            response.put("statusCode", 202);
        } else {
            response.put("error", "can not find article with specified id");
            response.put("statusCode", 404);
        }

        return response;
    }
}
