package com.example.jsonex.repositories;

import com.example.jsonex.entities.Category;
import com.example.jsonex.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "Select * from `jsonexprodcutshop`.users order by RAND() LIMIT 1" ,nativeQuery = true)
    Optional<User> getRandomEntity();
}
