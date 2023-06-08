package com.example.springdataadvancedq.repositories;

import com.example.springdataadvancedq.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    List<Ingredient> findByNameStartingWith(String name);

    List<Ingredient> findByNameInOrderByPrice(List<String> names);

    void deleteByName(String ingredientName);

    @Query("UPDATE Ingredient AS i SET i.price = i.price * 1.10")

    @Modifying                  //zaduljitelno pri UPDATE zaqvki kogato pishem query
    void updateAllPrice();
}
