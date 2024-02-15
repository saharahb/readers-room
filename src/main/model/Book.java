package model;

import model.exceptions.invalidRatingException;

import java.util.ArrayList;

// Represents a Book with a title, author, genre, and length in pages. Includes rating/reviews.
public class Book {

    private String title;
    private String author;
    private Genre genre;
    private int pages;

    private Integer rating;
    private ArrayList<String> reviews;

    // EFFECTS: constructs a book with a title, author, genre, and length in pages.
    public Book(String title, String author, Genre genre, int pages) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;

        this.rating = null;
        this.reviews = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getLength() {
        return pages;
    }

    public ArrayList getReviews() {
        return reviews;
    }

    public Integer getRating() {
        return rating;
    }

    // REQUIRES: rating is an integer between 1-5
    // MODIFIES: this
    // EFFECTS: adds a 1-5 star rating to book
    public void addRating(Integer rating) throws invalidRatingException {
        if (rating <= 5 && rating >= 1) {
            this.rating = rating;
        } else {
            throw new invalidRatingException();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a review to the book's list of reviews
    public void addReview(String review) {
        reviews.add(review);
    }

}
