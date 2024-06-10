package dev.danvega.graphqlbooks;

import dev.danvega.graphqlbooks.model.Book;
import dev.danvega.graphqlbooks.model.Review;
import dev.danvega.graphqlbooks.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /*@Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository) {
        return args -> {

            Book book = new Book("Reactive Spring", 484L, "Josh Long");
            Review review = new Review("Great book", "I really enjoyed this book");
            // bidirectional relationship
            review.setBook(book);
            book.setReviews(List.of(review));
            bookRepository.save(book);

            bookRepository.save(new Book("Spring Boot Up & Running", 328L, "Mark Heckler"));
            bookRepository.save(new Book("Hacking with Spring Boot 2.3", 392L, "Greg Turnquist"));
        };
    }*/

}