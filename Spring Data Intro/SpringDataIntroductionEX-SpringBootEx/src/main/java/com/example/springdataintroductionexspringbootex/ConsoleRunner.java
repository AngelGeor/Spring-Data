package com.example.springdataintroductionexspringbootex;

import com.example.springdataintroductionexspringbootex.Entities.Author;
import com.example.springdataintroductionexspringbootex.Entities.Book;
import com.example.springdataintroductionexspringbootex.Repositories.AuthorRepository;
import com.example.springdataintroductionexspringbootex.Repositories.BookRepository;
import com.example.springdataintroductionexspringbootex.Services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        //  this.seedService.seedAuthors();
        //   this.seedService.seedCategories();
        //   this.seedService.seedAll();

        //   this.booksAfter2000();               // (1.Get all books after the year 2000. Print only their titles.)

        // this.allAuthorsWithBookBefore1990();  //(2. Get all authors with at least one book with release date before 1990.
                                                         // Print their first name and last name.)


        //this.allAuthorsOrderedByBookCount(); // (3. Get all authors, ordered by the number of their books (descending).
                                                // Print their first name, last name and book count.)

        this.printAllBooksByAuthorNameOrderByReleaseDate(); // 4.Get all books from author George Powell,
                                            // ordered by their release date (descending), then by book title (ascending).
                                            // Print the book's title, release date and copies.
    }


    private void booksAfter2000() {
        LocalDate year2000 = LocalDate.of(2000, 12, 31);

        List<Book> books = this.bookRepository.findByReleaseDateAfter(year2000);

        books.forEach(book -> System.out.println(book.getTitle()));
    }

    private void allAuthorsWithBookBefore1990() {
        LocalDate year1990 = LocalDate.of(1990, 1, 1);

        List<Author> authors = this.authorRepository.findDistinctByBooksReleaseDateBefore(year1990);
        authors.forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
    }

    private void allAuthorsOrderedByBookCount() {
        List<Author> authors = this.authorRepository.findAll();

        authors.stream().
                sorted((l, r) -> r.getBooks().size() - l.getBooks().size()).
                forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName() + " "
                        + "-> " + author.getBooks().size()));

    }

    private void printAllBooksByAuthorNameOrderByReleaseDate() {
        Author author = authorRepository.findByFirstNameAndLastName("George", "Powell");

        List<Book> books = bookRepository.findByAuthorOrderByReleaseDateDescTitleAsc(author);

        books.forEach(b -> System.out.printf("Title: %s, release date: %s, Number of copies: %d.%n",
                b.getTitle(), b.getReleaseDate(), b.getCopies()));

    }


}
