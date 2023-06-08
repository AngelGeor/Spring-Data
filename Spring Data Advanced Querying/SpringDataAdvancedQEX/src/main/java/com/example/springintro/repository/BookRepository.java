package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.BookSummary;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType type, Integer numberOfCopies);


    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal lowerBound, BigDecimal upperBound);


    List<Book> releaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> findByReleaseDateBefore(LocalDate before);

    List<Book> findByTitleContaining(String search);

    List<Book> findByAuthorLastNameStartingWith(String search);

    @Query("Select count(b) from Book b where length(b.title) > :length")
    int countBooksWithTitleLongerThan(int length);

    @Query("SELECT b.title AS title, b.editionType AS editionType, b.ageRestriction AS ageRestriction," +
            " b.price AS price FROM Book AS b" +
            " Where b.title = :title")
    BookSummary findSummaryForTitle(String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book AS b Set b.copies = b.copies + :amount WHERE b.releaseDate > :date")
    int addCopiesToBooksAfter(LocalDate date, int amount);

    @Transactional
    int deleteByCopiesLessThan(int amount);
}
