package com.cms.cmsapi.service;

import com.cms.cmsapi.dao.AuthorDAOImpl;
import com.cms.cmsapi.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorDAOImpl authorDAO;

    @Autowired
    public AuthorService(final AuthorDAOImpl authorDAO) {
        this.authorDAO = authorDAO;
    }

    public HashMap insertAuthor(Author author) {

        HashMap response = new HashMap();

        // Validate request body
        if (author.getEmail() != null
                && author.getFirstName() != null
                && author.getLastName()  != null
        ) {
            Author newAuthor = this.authorDAO.insertAuthor(author);
            if (newAuthor != null) {
                HashMap link = new HashMap();
                link.put("GET", "http://localhost:8080/authors/get/" + newAuthor.getEmail());
                link.put("DELETE", "http://localhost:8080/authors/delete/" + newAuthor.getEmail());
                // response map
                response.put("message", "author created successfully");
                response.put("statusCode", 201);
                response.put("link", link);
            } else {
                response.put("error", "author with email already exists");
                response.put("statusCode", 400);
            }
        } else {
            response.put("error", "request body missing one or more keys [ firstName, lastName, email ]");
            response.put("statusCode", 400);
        }

        return response;
    }

    public HashMap getAuthorByEmail(String email) {
        Author author = this.authorDAO.getAuthorByEmail(email);
        HashMap response = new HashMap();
        if (author != null) {
            response.put("author", author);
            response.put("message", "success");
            response.put("statusCode", 200);
        } else {
            response.put("error", "can not find author with specified email");
            response.put("statusCode", 404);
        }
        return response;
    }

    public HashMap deleteAuthorByEmail(String email) {
        Boolean existsAndDeleted = this.authorDAO.deleteAuthorByEmail(email);
        HashMap response = new HashMap();
        if (existsAndDeleted) {
            response.put("message", "success");
            response.put("statusCode", 202);
        } else {
            response.put("error", "can not find author with specified email");
            response.put("statusCode", 404);
        }
        return response;
    }

    public HashMap allAuthors() {
        List<Author> authors = this.authorDAO.getAllAuthors();
        HashMap authorsMap = new HashMap();
        authorsMap.put("authors", authors);
        authorsMap.put("amount", authors.size());
        authorsMap.put("message", "success");
        authorsMap.put("statusCode", 200);
        return authorsMap;
    }
}
