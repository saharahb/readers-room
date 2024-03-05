package persistence;

import model.Book;
import model.Genre;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkBook(String title, String author, Genre genre, Integer pages, Book book) {
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(genre, book.getGenre());
        assertEquals(pages, book.getLength());
    }
}
