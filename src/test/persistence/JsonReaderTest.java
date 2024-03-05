package persistence;

import model.Book;
import model.Genre;
import model.Library;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/BadFileName.json");
        try {
            Library lib = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            System.out.println("Good.");
        }
    }

    @Test
    void testReaderEmptyLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLibrary.json");
        try {
            Library lib = reader.read();
            assertEquals("Person", lib.getName());
            assertEquals(0, lib.getBooks().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderLibrary.json");
        try {
            Library lib = reader.read();
            assertEquals("Person", lib.getName());
            ArrayList<Book> books = lib.getBooks();
            assertEquals(2, books.size());
            checkBook("Hunger Games", "Suzanne Collins", Genre.YOUNG_ADULT, 364, books.get(0));
            assertEquals(5, books.get(0).getRating());
            assertEquals("PERFECT.", books.get(0).getReview());
            checkBook("Pride and Prejudice", "Jane Austen", Genre.ROMANCE, 266, books.get(1));
            assertNull(books.get(1).getRating());
            assertNull(books.get(1).getReview());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
