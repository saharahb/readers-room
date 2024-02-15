package model;

import model.exceptions.InvalidRatingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BookTest {

    private Book book;

    @BeforeEach
    private void runBefore() {
        book = new Book("Test Book", "Saharah Bains", Genre.ROMANCE, 200);
    }

    @Test
    public void testBook() {
        assertEquals("Test Book", book.getTitle());
        assertEquals("Saharah Bains", book.getAuthor());
        assertEquals(Genre.ROMANCE, book.getGenre());
        assertEquals(200, book.getLength());
    }
    @Test
    public void testAddRatingOver5() {
        try {
            book.addRating(6);
            fail("Should not allow rating of 6!");
        } catch (InvalidRatingException e) {
            System.out.println("Exception works!");
        }
    }

    @Test
    public void testAddRatingUnder0() {
        try {
            book.addRating(-1);
            fail("Should not allow rating of -1!");
        } catch (InvalidRatingException e) {
            System.out.println("Exception works!");
        }
    }

    @Test
    public void testAddRatingValid() {
        try {
            book.addRating(4);
            assertEquals(4, book.getRating());
        } catch (InvalidRatingException e) {
            fail("Should not throw exception here!");
        }
    }

    @Test
    public void testAddRatingZero() {
        try {
            book.addRating(0);
            System.out.println("good!");
        } catch (InvalidRatingException e) {
            fail("Should not throw exception here!");
        }
    }

    @Test
    public void testAddRating5() {
        try {
            book.addRating(5);
            System.out.println("good!");
        } catch (InvalidRatingException e) {
            fail("Should not throw exception here!");
        }
    }

    @Test
    public void testAddReview() {
        book.addReview("I like this book");
        assertEquals(1, book.getReviews().size());

        book.addReview("I hate this book");
        assertEquals(2, book.getReviews().size());
        assertEquals("I like this book", book.getReviews().get(0));
    }
}
