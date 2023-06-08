package com.softuni.modelmapperexgamestore.repositories;

import com.softuni.modelmapperexgamestore.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
    Game findFirstByTitle(String title);
}
