package com.example.springdataadvancedq.repositories;

import com.example.springdataadvancedq.entities.Shampoo;
import com.example.springdataadvancedq.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository <Shampoo,Long> {

    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findByBrandAndSize(String brand, Size size);

    List<Shampoo> findBySizeOrderByIdDesc(Size inputSize);

    @Query(value = "SELECT s FROM Shampoo AS s JOIN s.ingredients as i WHERE i.name = :ingredient")
    List<Shampoo> findByIngredient(String ingredient);

    @Query(value = "SELECT s FROM Shampoo AS s JOIN s.ingredients as i WHERE i.name IN :ingredients")
    List<Shampoo> findByIngredients(List<String> ingredients);

    List<Shampoo> findBySizeOrLabelId(Size inputSize, long labelId);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    long countByPriceLessThan(BigDecimal price);
    @Query(value = "SELECT s FROM Shampoo AS s WHERE s.ingredients.size < :count")
    List<Shampoo> findWithIngredientCountLessThan(int count);

}
