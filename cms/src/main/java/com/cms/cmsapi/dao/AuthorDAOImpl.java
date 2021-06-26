package com.cms.cmsapi.dao;

import com.cms.cmsapi.model.Author;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorDAOImpl implements AuthorDAO {
    private final MongoOperations operations;

    public AuthorDAOImpl(MongoOperations operations) {
        this.operations = operations;
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = this.operations.findAll(Author.class);
        return authors;
    }

    @Override
    public Author getAuthorByEmail(String email) {
        Query query = new Query().addCriteria(Criteria.where("email").is(email));
        return this.operations.findOne(query, Author.class);
    }

    @Override
    public Boolean deleteAuthorByEmail(String email) {
        Author author = this.getAuthorByEmail(email);
        if (author != null) {
            this.operations.remove(author);
            return true;
        }
        return false;
    }

    @Override
    public Author insertAuthor(Author author) {
        if (this.getAuthorByEmail(author.getEmail()) == null) return this.operations.insert(author);
        return null;
    }

}

