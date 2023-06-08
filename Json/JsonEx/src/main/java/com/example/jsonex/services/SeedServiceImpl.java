package com.example.jsonex.services;


import com.example.jsonex.dtos.CategoryImportDTO;
import com.example.jsonex.dtos.ProductImportDTO;
import com.example.jsonex.dtos.UserImportDTO;
import com.example.jsonex.entities.Category;
import com.example.jsonex.entities.Product;
import com.example.jsonex.entities.User;
import com.example.jsonex.repositories.CategoryRepository;
import com.example.jsonex.repositories.ProductRepository;
import com.example.jsonex.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.jsonex.constants.Paths.*;
import static com.example.jsonex.constants.Utils.GSON;
import static com.example.jsonex.constants.Utils.MODEL_MAPPER;

@Service
public class SeedServiceImpl implements SeedService{
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void seedUsers() throws IOException {

       if(this.userRepository.count() == 0){
         final FileReader fileReader = new FileReader(USER_JSON_PATH.toFile());

         List<User> users = Arrays.stream(GSON.fromJson(fileReader, UserImportDTO[].class))
                 .map(userImportDTO -> MODEL_MAPPER.map(userImportDTO, User.class))
                 .collect(Collectors.toList());

         this.userRepository.saveAllAndFlush(users);
           fileReader.close();
       }
    }

    @Override
    public void seedCategories() throws IOException {
        if(this.categoryRepository.count() == 0) {
            final FileReader fileReader = new FileReader(CATEGORIES_JSON_PATH.toFile());

            final List<Category> categoryCollection = Arrays.stream(GSON.fromJson(fileReader, CategoryImportDTO[].class))
                    .map(categoryImportDTO -> MODEL_MAPPER.map(categoryImportDTO, Category.class))
                    .collect(Collectors.toList());

            this.categoryRepository.saveAllAndFlush(categoryCollection);
            fileReader.close();
        }

    }

    @Override
    public void seedProducts() throws IOException {
        if(this.productRepository.count() == 0) {
           final FileReader fileReader = new FileReader(PRODUCTS_JSON_PATH.toFile());

           final List<Product> products = Arrays.stream(GSON.fromJson(fileReader, ProductImportDTO[].class)).
                    map(productImportDTO -> MODEL_MAPPER.map(productImportDTO, Product.class))
                    .map(this::setRandomSeller)
                    .map(this::setRandomBuyer)
                    .map(this::setRandomCategories)
                            .collect(Collectors.toList());

            this.productRepository.saveAllAndFlush(products);

            fileReader.close();

        }
    }

    private Product setRandomCategories(Product product) {
        Random random = new Random();
       int numberOfCategories = random.nextInt( (int) this.categoryRepository.count());

        Set<Category> categories = new HashSet<>();

        IntStream.range(0, numberOfCategories)
                .forEach(number ->{
                   Category category = this.categoryRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

                    categories.add(category);
                });

        product.setCategories(categories);

        return product;
    }



    private Product setRandomBuyer(Product product) {
        if(product.getPrice().compareTo(BigDecimal.valueOf(700L)) > 0) {
            User buyer = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

            while(buyer.equals(product.getSeller())) {
                buyer = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
            }
            product.setBuyer(buyer);
        }
        return product;
    }

    private Product setRandomSeller(Product product) {
       final User seller = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

       product.setSeller(seller);

       return product;
    }
}
