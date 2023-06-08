package com.example.springdataintroductionexspringbootex.Repositories;

import com.example.springdataintroductionexspringbootex.Entities.Author;
import com.example.springdataintroductionexspringbootex.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByReleaseDateAfter(LocalDate releaseDate);

    List<Book> findByAuthorOrderByReleaseDateDescTitleAsc(Author author);
}
