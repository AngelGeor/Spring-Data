package com.softuni.modelmapperexgamestore.services.user.game;

public interface GameService {
    String addGame(String[] args);

    String editGame(String[] args);

    String deleteGame(Long id);

}
