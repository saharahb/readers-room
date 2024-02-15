package model;

import model.exceptions.invalidRatingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Book book;

    @BeforeEach
    private void runBefore() {
        book = new Book("Test Book", "Saharah Bains", Genre.ROMANCE, 200);
    }

    @Test
    public void testAddRatingOver5() {
        try {
            book.addRating(6);
            fail("Should not allow rating of 6!");
        } catch (invalidRatingException e) {
            System.out.println("Exception works!");
        }
    }

    @Test
    public void testAddRatingValid() {
        try {
            book.addRating(4);
            System.out.println("good!");
        } catch (invalidRatingException e) {
            fail("Should not throw exception here!");
        }
    }
}