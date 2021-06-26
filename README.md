# - CMS -

**A basic CMS (Content management system) replica - practicing getting more familiar with Spring MVC, MongoDB ODM and Java.**

## Technologies
* Java
* Maven
* Spring Web
* Spring Data MongoDB

## EndPoints
* POST - [`/authors/author`](#create-author)
* GET - [`/authors/get/{email}`](#get-author)
* DELETE - [`/authors/delete/{email}`](#delete-author)
* GET - [`/authors`](#get-all-authors)
* GET - [`/articles/author/{email}`](#get-all-of-authors-articles)
* POST - [`/articles/article`](#create-article)
* GET - [`/articles/author/get/{id}`](#get-article)
* DELETE - [`/articles/author/delete/{id}`](#delete-article)

### Create Author
```json
{
  "url": "/authors/author",
  "method": "POST",
  "description": "Create a new author",
  "requestBody": {
    "firstName": "John",
    "lastName": "Doe",
    "email": "doe@gmail.com"
  },
  "responseBody": {
    "success": {
      "link": {
        "DELETE": "/authors/delete/doe@gmail.com",
        "GET": "/authors/get/doe@gmail.com"
      },
      "message": "author created successfully",
      "statusCode": 201
    },
    "errors":[
      {
        "error": "author with email already exists",
        "statusCode": 400
      },
      {
        "error": "request body missing one or more keys [ firstName, lastName, email ]",
        "statusCode": 400
      }
    ]
  }
}
```
### Get Author
```json
{
  "url": "/authors/get/{email}",
  "method": "GET",
  "description": "Get author by email if exists",
  "pathVariable": "email",
  "responseBody": {
    "success": {
      "author": {
        "firstName": "John",
        "lastName": "Doe",
        "email": "doe@gmail.com"
      },
      "message": "success",
      "statusCode": 200
    },
    "error": {
      "error": "can not find author with specified email",
      "statusCode": 404
    }
  }
}
```
### Delete Author
```json
{
  "url": "/authors/delete/{email}",
  "method": "DELETE",
  "description": "Delete author using email if exists",
  "pathVariable": "email",
  "responseBody": {
    "success": {
      "message": "success",
      "statusCode": 202
    },
    "error": {
      "error": "can not find author with specified email",
      "statusCode": 404
    }
  }
}
```
### Get all Authors
```json
{
  "url": "/authors",
  "method": "GET",
  "description": "Get all existing authors",
  "responseBody": {
    "success": {
      "amount": 3,
      "message": "success",
      "authors": [
        {
          "firstName": "John",
          "lastName": "Doe",
          "email": "doe@gmail.com"
        }
      ],
      "statusCode": 200
    },
    "error": {
      "_comment": "Currently not checking for any errors"
    }
  }
}
```
### Get all of Author's Articles
```json
{
  "url": "/articles/author/{email}",
  "method": "GET",
  "description": "Get all of author's existing article",
  "pathVariable": "email",
  "responseBody": {
    "success": {
      "amount": 2,
      "message": "success",
      "articles": [
        {
          "id": "60d6cc97b63c6d2aa4d6de8c",
          "title": "the second article",
          "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
          "ownerEmail": "doe@gmail.com",
          "dateCreated": "2021-06-26T06:43:35.408+00:00"
        }
      ],
      "statusCode": 200
    },
    "error": {
      "error": "can not find author with specified email",
      "statusCode": 404
    }
  }
}
```
### Create Article
```json
{
  "url": "/articles/article",
  "method": "POST",
  "description": "Create a new article",
  "requestBody": {
    "title": "the second article",
    "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
    "ownerEmail": "doe@gmail.com"
  },
  "responseBody": {
    "success": {
      "message": "success",
      "article": {
        "id": "60d751038d96243a292a551d",
        "title": "the second article",
        "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
        "ownerEmail": "doe@gmail.com",
        "dateCreated": "2021-06-26T16:08:35.228+00:00"
      },
      "statusCode": 201
    },
    "errors": [
      {
        "error": "can not find author with specified email",
        "statusCode": 404
      },
      {
        "error": "request body missing one or more keys [ title, content, ownerEmail ]",
        "statusCode": 400
      }
    ]
  }
}
```
### Get Article
```json
{
  "url": "/articles/author/get/{id}",
  "method": "GET",
  "description": "Get article by using id",
  "pathVariable": "id",
  "responseBody": {
    "success": {
      "message": "success",
      "article": {
        "id": "60d6cc97b63c6d2aa4d6de8c",
        "title": "the second article",
        "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras eu placerat quam. Pellentesque porta lectus tortor, non pretium mauris viverra eget. Vivamus eget risus consequat, tempor quam eget, iaculis quam. Nullam tincidunt gravida placerat. Nunc massa metus, vulputate nec lacus sed, ullamcorper pellentesque arcu. Ut mollis egestas dui, vitae imperdiet felis viverra sed. Praesent interdum aliquam nibh, id vestibulum ante ultrices eu. Phasellus tincidunt diam hendrerit odio dictum tincidunt. Pellentesque laoreet tortor eget turpis elementum egestas. Praesent blandit odio nibh, eget sagittis nulla consectetur ac. Donec pellentesque hendrerit mi, sit amet tristique augue.",
        "ownerEmail": "boss@gmail.com",
        "dateCreated": "2021-06-26T06:43:35.408+00:00"
      },
      "statusCode": 200
    },
    "error": {
      "error": "can not find author with specified email",
      "statusCode": 404
    }
  }
}
```
### Delete Article
```json
{
  "url": "/articles/author/delete/{id}",
  "method": "DELETE",
  "description": "Delete article by using id",
  "pathVariable": "id",
  "responseBody": {
    "success": {
      "message": "success",
      "statusCode": 202
    },
    "error": {
      "error": "can not find article with specified id",
      "statusCode": 404
    }
  }
}
```

&#10240;<br>
<hr>
&#10240;<br>
&#10240; &#10240; &#10240; Author: Marcelo Martins<br>
&#10240; &#10240; &#10240; GitHub: @matxa<br>
&#10240; &#10240; &#10240; Email: matxa21@gmail.com<br>
&#10240;
<hr>
