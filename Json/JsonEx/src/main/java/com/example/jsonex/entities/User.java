package com.example.jsonex.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "first_name", nullable = true)
    private String firstName;

    @Size(min = 3)
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column
    private int age;

    @OneToMany(targetEntity = Product.class, mappedBy = "seller")
    @Fetch(FetchMode.JOIN)
    private Set<Product> sellingProducts;

    @OneToMany(targetEntity = Product.class, mappedBy = "buyer")
    @Fetch(FetchMode.JOIN)
    private Set<Product> boughtProducts;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    private Set<User> friends;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;

        return
                Objects.equals(firstName, user.firstName)
                && Objects.equals(getId(), user.getId())
                && Objects.equals(lastName, user.lastName);

    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, getId());
    }
}
