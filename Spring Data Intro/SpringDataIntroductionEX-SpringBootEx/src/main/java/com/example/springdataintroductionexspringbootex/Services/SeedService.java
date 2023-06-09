package com.example.springdataintroductionexspringbootex.Services;

import java.io.IOException;

public interface SeedService {

    void seedAuthors() throws IOException;

    void seedCategories() throws IOException;

    void seedBooks() throws IOException;

    default void seedAll() throws IOException {
        seedAuthors();
        seedCategories();
        seedBooks();
    }
}
