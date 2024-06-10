package dev.danvega.graphqlbooks.model;

import java.util.List;

public record BookInput(String title, Long pages, String author,List<Review> reviews) {
}
