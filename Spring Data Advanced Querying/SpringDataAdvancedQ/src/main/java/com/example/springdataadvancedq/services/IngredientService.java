package com.example.springdataadvancedq.services;

import com.example.springdataadvancedq.entities.Ingredient;

import java.util.List;

public interface IngredientService {

   List<Ingredient> selectByName(String name);

    List<Ingredient> findAllByNameInOrderByPrice(List<String> names);

    void deleteByName(String ingredientName);

    void updatePriceAll();
}
