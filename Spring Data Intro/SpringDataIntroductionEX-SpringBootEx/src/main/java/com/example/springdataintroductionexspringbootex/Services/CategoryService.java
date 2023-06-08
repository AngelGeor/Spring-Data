package com.example.springdataintroductionexspringbootex.Services;

import com.example.springdataintroductionexspringbootex.Entities.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();
}
