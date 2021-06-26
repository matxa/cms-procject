package com.cms.cmsapi.dao;

import com.cms.cmsapi.model.Article;
import com.cms.cmsapi.model.Author;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleDAOImp implements ArticleDAO {
    private final MongoOperations operations;

    public ArticleDAOImp(MongoOperations operations) {
        this.operations = operations;
    }


    @Override
    public List<Article> getArticlesByEmail(String email) {
        Author author = this.getAuthorByEmail(email);
        if (author != null) {
            Query query = new Query().addCriteria(Criteria.where("ownerEmail").is(email));
            return this.operations.find(query, Article.class);
        }
        return null;
    }

    @Override
    public Article getArticleById(String id) {
        Query query = new Query().addCriteria(Criteria.where("id").is(id));
        return this.operations.findOne(query, Article.class);
    }

    @Override
    public Article insertArticle(Article article) {
        Author author = this.getAuthorByEmail(article.getOwnerEmail());
        if (author != null) {
            Article newArticle = this.operations.insert(article);
            return newArticle;
        }
        return null;
    }

    @Override
    public Boolean deleteArticle(String id) {
        Article article = this.getArticleById(id);
        if (article != null) {
            this.operations.remove(article);
            return true;
        }
        return false;
    }

    @Override
    public Author getAuthorByEmail(String email) {
        Query query = new Query().addCriteria(Criteria.where("email").is(email));
        return this.operations.findOne(query, Author.class);
    }
}
