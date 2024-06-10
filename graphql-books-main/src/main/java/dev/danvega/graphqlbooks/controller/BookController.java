package dev.danvega.graphqlbooks.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import dev.danvega.graphqlbooks.model.Book;
import dev.danvega.graphqlbooks.model.BookInput;
import dev.danvega.graphqlbooks.model.Review;
import dev.danvega.graphqlbooks.model.ReviewInput;
import dev.danvega.graphqlbooks.repository.BookRepository;
import dev.danvega.graphqlbooks.repository.ReviewRepository;


@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    public BookController(BookRepository bookRepository,ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
    }

    @QueryMapping
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @QueryMapping
    public Book findBook(@Argument Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Book createBook(@Argument String title, @Argument Long pages, @Argument String author, @Argument Integer review) {
        Book book = new Book(title, pages, author);
       book.setReviews(List.of(reviewRepository.findById(review).orElseThrow()));
        return bookRepository.save(book);
    }
    
    
   /* @MutationMapping
    public Review createReview(@Argument String title, @Argument String comment, @Argument Integer bookId) {
    	// Book bookInput = new Book(book.title(),book.pages(),book.author());
         Review review = new Review(title, comment);
         // bidirectional relationship
         //review.setBook(bookInput);
         review.setBook(new Book(bookId));
         //bookInput.setReviews(List.of(review));
        return reviewRepository.save(review);
        
    }*/
    
    @MutationMapping
    public Review createReview(@Argument ReviewInput reviewInput) {
        Book book = bookRepository.findById(reviewInput.bookId())
            .orElseThrow(() -> new RuntimeException("Author not found"));

        Review review = new Review();
        review.setTitle(reviewInput.title());
        review.setBook(book);

        return reviewRepository.save(review);
    }
    
    @MutationMapping
    public Book addBook(@Argument BookInput bookInput) {
        //return bookRepository.save(new Book(book.title(),book.pages(),book.author()));
    	 Book book = new Book();
         book.setTitle(bookInput.title());
         book.setAuthor(bookInput.author());
         book.setPages(bookInput.pages());
         List<Review> reviews = bookInput.reviews().stream().map(r -> {
             Review review = new Review();
             review.setTitle(r.getTitle());
             review.setComment(r.getComment());
             review.setBook(book);
             return review;
         }).collect(Collectors.toList()); 

         book.setReviews(reviews);
         return bookRepository.save(book);
     
    }
    
    @MutationMapping
    
    public Book createBookWithReview(@Argument BookInput bookInput) {
    	 Book book = new Book();
         book.setTitle(bookInput.title());
         book.setPages(bookInput.pages());
         List<Review> reviews = bookInput.reviews().stream().map(r -> {
             Review review = new Review();
             review.setTitle(r.getTitle());
             review.setComment(r.getComment());
             return review;
         }).collect(Collectors.toList()); 

         book.setReviews(reviews);
         return bookRepository.save(book);
    }

    @MutationMapping
    public Book updateBook(@Argument Integer id, @Argument BookInput book) {
        Book bookToUpdate = bookRepository.findById(id).orElse(null);
        if(bookToUpdate == null) {
            throw new RuntimeException("Book not found");
        }
        bookToUpdate.setTitle(book.title());
        bookToUpdate.setPages(book.pages());
        bookToUpdate.setAuthor(book.author());
        bookRepository.save(bookToUpdate);
        return bookToUpdate;
    }

    @MutationMapping
    public void deleteBook(@Argument Integer id) {
        bookRepository.deleteById(id);
    }

}
