package com.cms.cmsapi.dao;

import com.cms.cmsapi.model.Article;
import com.cms.cmsapi.model.Author;

import java.util.List;

public interface ArticleDAO {
    public List<Article> getArticlesByEmail(String email);
    public Article getArticleById(String id);
    public Article insertArticle(Article article);
    public Boolean deleteArticle(String id);
    public Author getAuthorByEmail(String email);
}
