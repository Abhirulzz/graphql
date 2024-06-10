package dev.danvega.graphqlbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.danvega.graphqlbooks.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
