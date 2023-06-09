package com.example.jsonex.repositories;

import com.example.jsonex.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "Select * from `jsonexprodcutshop`.categories order by RAND() LIMIT 1" ,nativeQuery = true)
    Optional<Category> getRandomEntity();

}
