package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Library lib;
    private Book b1;
    private Book b2;

    @BeforeEach
    public void runBefore() {
        lib = new Library("Saharah");
        b1 = new Book("Test Book", "Saharah Bains", Genre.ROMANCE, 200);
        b2 = new Book("Hunger Games", "Suzanne Collins", Genre.YOUNG_ADULT, 384);
    }

    @Test
    public void testAddBook() {
        lib.addBook(b1);
        assertEquals(1, lib.getBooks().size());
        assertEquals(b1, lib.getBooks().get(0));

        lib.addBook(b2);
        assertEquals(2, lib.getBooks().size());
        assertEquals(b2, lib.getBooks().get(1));
    }

    @Test
    public void testRemoveBook() {
        lib.addBook(b1);
        assertFalse(lib.removeBook(b2));

        lib.addBook(b2);
        assertEquals(2, lib.getBooks().size());

        lib.removeBook(b2);
        assertEquals(1, lib.getBooks().size());
        assertFalse(lib.getBooks().contains(b2));
        assertTrue(lib.removeBook(b1));
    }

    @Test
    public void testToJson() {
        JSONObject expectedJson = new JSONObject();
        expectedJson.put("name", "Saharah");
        JSONArray books = new JSONArray();
        expectedJson.put("books", books);
        JSONObject actualJson = lib.toJson();
        assertEquals(expectedJson.toString(), actualJson.toString());

        lib.addBook(b1);
        lib.addBook(b2);
        actualJson = lib.toJson();

        books.put(b1.toJson());
        books.put(b2.toJson());

        assertEquals(expectedJson.toString(), actualJson.toString());
    }

    @Test
    public void testBooksToJson() {
        JSONArray expectedArray = new JSONArray();
        JSONArray actualArray = lib.booksToJson();
        assertEquals(expectedArray.toString(), actualArray.toString());

        lib.addBook(b1);
        lib.addBook(b2);
        actualArray = lib.booksToJson();

        expectedArray.put(b1.toJson());
        expectedArray.put(b2.toJson());

        assertEquals(expectedArray.toString(), actualArray.toString());
    }


}
