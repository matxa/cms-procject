package com.cms.cmsapi.controller;

import com.cms.cmsapi.model.Author;
import com.cms.cmsapi.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/authors", method = RequestMethod.GET)
public class AuthorsController {

    private final AuthorService authorService;

    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity authors() {
        HashMap response = this.authorService.allAuthors();
        return ResponseEntity.status((int) response.get("statusCode")).body(response);
    }

    @GetMapping("/get/{email}")
    public ResponseEntity getAuthor(@PathVariable("email") String email) {
        HashMap response = this.authorService.getAuthorByEmail(email);
        return ResponseEntity.status((int) response.get("statusCode")).body(response);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity deleteAuthor(@PathVariable("email") String email) {
        HashMap response = this.authorService.deleteAuthorByEmail(email);
        return ResponseEntity.status((int) response.get("statusCode")).body(response);
    }

    @PostMapping("/author")
    public ResponseEntity createNewAuthor(@RequestBody Author author) {
        HashMap response = this.authorService.insertAuthor(author);
        return ResponseEntity.status((int) response.get("statusCode")).body(response);
    }
}

