package com.cms.cmsapi.dao;

import com.cms.cmsapi.model.Author;

import java.util.List;

public interface AuthorDAO {
    public List<Author> getAllAuthors();
    public Author getAuthorByEmail(String email);
    public Boolean deleteAuthorByEmail(String email);
    public Author insertAuthor(Author author);
}

