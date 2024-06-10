package dev.danvega.graphqlbooks.repository;

import dev.danvega.graphqlbooks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
