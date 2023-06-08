package com.example.springdataadvancedq.services;

import com.example.springdataadvancedq.entities.Shampoo;
import com.example.springdataadvancedq.entities.Size;
import com.example.springdataadvancedq.repositories.ShampooRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }


    @Override
    public List<Shampoo> findByBrand(String brand) {
        return this.shampooRepository.findByBrand(brand);
    }

    @Override
    public List<Shampoo> findByBrandAndSize(String brand, String size) {
        Size inputSize = Size.valueOf(size);

        return this.shampooRepository.findByBrandAndSize(brand, inputSize);
    }

    @Override
    public List<Shampoo> findBySizeOrderByIdDesc(String size) {
        Size inputSize = Size.valueOf(size);

        return this.shampooRepository.findBySizeOrderByIdDesc(inputSize);
    }

    @Override
    public List<Shampoo> findByIngredient(String ingredient) {
        return this.shampooRepository.findByIngredient(ingredient);
    }

    @Override
    public List<Shampoo> findByIngredient(List<String> ingredients) {
        return this.shampooRepository.findByIngredients(ingredients);
    }

    @Override
    public List<Shampoo> findBySizeOrLabelId(String size, long labelId) {
        Size inputSize = Size.valueOf(size);

        return this.shampooRepository.findBySizeOrLabelId(inputSize, labelId);
    }

    @Override
    public List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(String price) {
        BigDecimal parsed = new BigDecimal(price);
        return this.shampooRepository.findByPriceGreaterThanOrderByPriceDesc(parsed);
    }

    @Override
    public long countByPriceLessThan(String price) {
        BigDecimal parsedNumber = new BigDecimal(price);
        return this.shampooRepository.countByPriceLessThan(parsedNumber);
    }

    @Override
    public List<Shampoo> findWithIngredientCountLessThan(int count) {
     return this.shampooRepository.findWithIngredientCountLessThan(count);
    }
}