package com.example.springintro;

import com.example.springintro.model.entity.BookSummary;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;


    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        //1.Books' Titles by Age Restriction
//    String ageRestrictionType = scanner.nextLine();
//
//        AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRestrictionType.toUpperCase());
//        final List<Book> allBooksByAgeRestriction = this.bookService.findAllByAgeRestriction(ageRestriction);
//        allBooksByAgeRestriction.stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);
//

//        //2.Golden Books
//        this.bookService.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD,5000)
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);


        //3.	Books by Price
//        this.bookService.findAllWithPriceLessThanOrPriceGreaterThan(5,40)
//                .forEach(book -> System.out.println(book.getTitle() + " - " + book.getPrice()));


//        //4.	Not Released Books
//        int releaseYear = Integer.parseInt(scanner.nextLine());
//        this.bookService.findNotReleasedIn(releaseYear).
//                forEach(book -> System.out.println(book.getTitle()));
//

//        //5.	Books Released Before Date
//        String date = scanner.nextLine();
//        this.bookService.findBooksReleasedBefore(date)
//                .forEach(book -> System.out.printf("%s %s %.2f%n",
//                  book.getTitle(), book.getEditionType(), book.getPrice()));


//        //6.  Authors Search
//        String endsWith = scanner.nextLine();
//        authorService.findByFirstNameEndingWith(endsWith)
//                .stream()
//                .map(author -> author.getFirstName() + " " + author.getLastName())
//                .forEach(System.out::println);


//        //7. Books Search
//        String search = scanner.nextLine();
//        this.bookService.findAllTitlesContaining(search)
//                .forEach(System.out::println);


//        //8. Book Titles Search
//        String search = scanner.nextLine();
//        this.bookService.findByAuthorLastNameStartsWith(search)
//                .forEach(book -> System.out.printf("%s (%s %s)%n",
//                        book.getTitle(),book.getAuthor().getFirstName(),
//                        book.getAuthor().getLastName()));
//

//        //9. Count Books
//        int length = Integer.parseInt(scanner.nextLine());
//      int numberOfBooks =  this.bookService.countBooksWithTitleLongerThan(length);
//
//        System.out.printf("There are %d books with longer title than %d symbols%n",
//                numberOfBooks,length);


//        //10. Total Book Copies
//        this.authorService.getWithTotalCopies()
//                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()
//                + " - " + a.getTotalCopies()));


        //11. Reduced Book
//        String title = scanner.nextLine();
//        BookSummary summary = this.bookService.getInformationForTitle(title);
//
//        System.out.println(summary.getTitle() + " " + summary.getEditionType() + " " + summary.getAgeRestriction()
//        + " " + summary.getPrice());


        // 12. Increase Book Copies*
//        String date = scanner.nextLine();
//        int amount = Integer.parseInt(scanner.nextLine());
//        int updatedBooks = this.bookService.addCopiesToBooksAfter(date,amount);
//        System.out.printf("%d books are released after %s, " +
//                "so total of %d book copies were added", updatedBooks, date, amount * updatedBooks);

        //13. Remove Books*
        int amount = Integer.parseInt(scanner.nextLine());
       int CountOfDeletedBooks = this.bookService.deleteWithCopiesLessThan(amount);
        System.out.println(CountOfDeletedBooks + " books were deleted.");

    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}