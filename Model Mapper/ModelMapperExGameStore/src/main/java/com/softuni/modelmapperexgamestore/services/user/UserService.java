package com.softuni.modelmapperexgamestore.services.user;

import com.softuni.modelmapperexgamestore.entities.User;

public interface UserService {
    String registerUser(String [] args);

    String loginUser(String[] args);

    String logoutUser();

    User getLoggedInUser();
}
