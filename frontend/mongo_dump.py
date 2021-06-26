#!/usr/bin/env python3
"""Dump Dummy data into DB for testing FRONTEND"""
import requests


# Important please change if cms-api is running on diffent host or port
API_URL = "http://localhost:8080"

authors = [
    {
        "firstName": "John",
        "lastName": "Doe",
        "email": "doe@gmail.com"
    },
    {
        "firstName": "Brian",
        "lastName": "Boss",
        "email": "boss@gmail.com"
    },
    {
        "firstName": "Devindra",
        "lastName": "Genius",
        "email": "genius@gmail.com"
    },
]

articles = [
    {
        "title": "the first article",
        "content": """Lorem ipsum dolor sit amet, consectetur adipiscing elit.
Quisque non nisl eu nisl varius cursus. Vivamus tempor tellus
condimentum urna eleifend ullamcorper. Sed molestie blandit leo
vel luctus. Aliquam quis consectetur urna. Aenean eu odio augue.
Sed tempor sit amet tortor eget dapibus. Integer ultrices lectus
mauris, nec accumsan nunc sagittis in. Nullam id bibendum dolor,
sodales mollis lacus. Suspendisse mollis, ante ut pellentesque
sagittis, ex purus tincidunt dui, vel mattis arcu orci quis diam.
Praesent fringilla magna magna, quis blandit metus vehicula eu.
In consequat porta odio, eu efficitur ipsum varius sed. Etiam
fringilla, dui mattis finibus viverra, orci nisl eleifend massa,
et faucibus ex urna a magna.""",
        "ownerEmail": "boss@gmail.com"
    },
    {
        "title": "the second article",
        "content": """Lorem ipsum dolor sit amet, consectetur adipiscing elit.
Quisque non nisl eu nisl varius cursus. Vivamus tempor tellus
condimentum urna eleifend ullamcorper. Sed molestie blandit leo
vel luctus. Aliquam quis consectetur urna. Aenean eu odio augue.
Sed tempor sit amet tortor eget dapibus. Integer ultrices lectus
mauris, nec accumsan nunc sagittis in. Nullam id bibendum dolor,
sodales mollis lacus. Suspendisse mollis, ante ut pellentesque
sagittis, ex purus tincidunt dui, vel mattis arcu orci quis diam.
Praesent fringilla magna magna, quis blandit metus vehicula eu.
In consequat porta odio, eu efficitur ipsum varius sed. Etiam
fringilla, dui mattis finibus viverra, orci nisl eleifend massa,
et faucibus ex urna a magna.""",
        "ownerEmail": "boss@gmail.com"
    },
    {
        "title": "click on the red X to delete article",
        "content": """Lorem ipsum dolor sit amet, consectetur adipiscing elit.
Quisque non nisl eu nisl varius cursus. Vivamus tempor tellus
condimentum urna eleifend ullamcorper. Sed molestie blandit leo
vel luctus. Aliquam quis consectetur urna. Aenean eu odio augue...""",
        "ownerEmail": "boss@gmail.com"
    },
]

# Create Authors
for author in authors:
    req = requests.post(f"{API_URL}/authors/author", json=author)

# Create Articles
for article in articles:
    req = requests.post(f"{API_URL}/articles/article", json=article)
