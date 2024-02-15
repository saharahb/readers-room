package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Library lib;
    private Book b1;
    private Book b2;

    @BeforeEach
    public void runBefore() {
        lib = new Library();
        b1 = new Book("Test Book", "Saharah Bains", Genre.ROMANCE, 200);
        b2 = new Book("Hunger Games", "Suzanne Collins", Genre.CHILDREN, 384);
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

}
