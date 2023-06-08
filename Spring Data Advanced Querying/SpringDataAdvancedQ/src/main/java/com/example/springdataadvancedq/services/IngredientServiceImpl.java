package com.example.springdataadvancedq.services;

import com.example.springdataadvancedq.entities.Ingredient;
import com.example.springdataadvancedq.repositories.IngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> selectByName(String name) {
        return this.ingredientRepository.findByNameStartingWith(name);
    }



    @Override
    public List<Ingredient> findAllByNameInOrderByPrice(List<String> names) {
        return this.ingredientRepository.findByNameInOrderByPrice(names);
    }

    @Transactional        //Zaduljitelno trasactional anotaciq se postavq pri Delete operacii
    @Override
    public void deleteByName(String ingredientName) {
        this.ingredientRepository.deleteByName(ingredientName);
    }

    @Override
    @Transactional        ////Zaduljitelno trasactional anotaciq se postavq pri Update operacii
    public void updatePriceAll(){
        this.ingredientRepository.updateAllPrice();
    }
}
