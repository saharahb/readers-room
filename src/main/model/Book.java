package model;

import model.exceptions.InvalidRatingException;
import org.json.JSONObject;
import persistence.Writable;

// Represents a Book with a title, author, genre, and length in pages. Includes rating/reviews.
public class Book implements Writable {

    private String title;
    private String author;
    private Genre genre;
    private int pages;

    private Integer rating;
    private String review;

    // EFFECTS: constructs a book with a title, author, genre, and length in pages.
    public Book(String title, String author, Genre genre, int pages) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;

    }

    // getters and setters
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

    public String getReview() {
        return review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    // REQUIRES: rating is an integer between 0-5
    // MODIFIES: this
    // EFFECTS: adds a 0-5 star rating to book
    public void addRating(Integer rating) throws InvalidRatingException {
        if (rating <= 5 && rating >= 0) {
            this.setRating(rating);
        } else {
            throw new InvalidRatingException();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a review to the book's list of reviews
    public void addReview(String review) {
        this.setReview(review);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("author", author);
        json.put("genre", genre);
        json.put("pages", pages);
        json.put("rating", rating);
        json.put("review", review);
        return json;
    }
}
