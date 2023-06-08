package com.softuni.modelmapperexgamestore.entities;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

@ManyToOne(optional = false)
private User user;

@ManyToMany(fetch = FetchType.EAGER, cascade = {
        CascadeType.DETACH,CascadeType.MERGE
})
private Set<Game> games;

    public Order() {
        this.games = new HashSet<>();
    }

    public Order(User user, Set<Game> games) {
        this.user = user;
        this.games = games;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
