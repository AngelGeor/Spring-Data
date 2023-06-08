package com.softuni.modelmapperexgamestore.repositories;

import com.softuni.modelmapperexgamestore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findFirstByEmail(String email);


    boolean existsUserByEmail(String email);
}
