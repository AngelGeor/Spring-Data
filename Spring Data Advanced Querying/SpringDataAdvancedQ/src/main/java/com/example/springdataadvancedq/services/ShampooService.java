package com.example.springdataadvancedq.services;

import com.example.springdataadvancedq.entities.Shampoo;

import java.util.List;

public interface ShampooService {

    List<Shampoo> findByBrand(String brand);
    List<Shampoo> findByBrandAndSize(String brand, String size);


    List<Shampoo> findBySizeOrderByIdDesc(String size);

    List<Shampoo> findByIngredient(String ingredient);

    List<Shampoo> findByIngredient(List<String> ingredients);

    List<Shampoo> findBySizeOrLabelId(String size, long labelId);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(String price);

    long countByPriceLessThan(String price);

    List<Shampoo> findWithIngredientCountLessThan(int count);
}
