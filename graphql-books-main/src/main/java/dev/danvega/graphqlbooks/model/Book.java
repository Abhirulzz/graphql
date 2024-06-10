package dev.danvega.graphqlbooks.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Long pages;
    private String author;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    //@JoinColumn(name = "book_id")
    private List<Review> reviews;

   
    public Book() {
    }

    public Book(String title, Long pages, String author) {
        this.title = title;
        this.pages = pages;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pageCount) {
        this.pages = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                ", author='" + author + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}
