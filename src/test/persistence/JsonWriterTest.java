package persistence;

import model.Book;
import model.Genre;
import model.Library;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile () {
        try {
            Library lib = new Library("Person");
            JsonWriter writer = new JsonWriter(".data/myBadFileName.json");
            writer.open();
            fail("Expected an exception");
        } catch (IOException e) {
            System.out.println("Good.");
        }
    }

    @Test
    void testWriterEmptyLibrary() {
        try {
            Library lib = new Library("Person");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLibrary.json");
            writer.open();
            writer.write(lib);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLibrary.json");
            lib = reader.read();
            assertEquals("Person", lib.getName());
            assertEquals(0, lib.getBooks().size());
        } catch (IOException e) {
            fail("Should not throw exception here.");
        }
    }

    @Test
    void testWriterLibrary() {
        try {
            Library lib = new Library("Person");
            lib.addBook(new Book("Hunger Games", "Suzanne Collins", Genre.YOUNG_ADULT, 364 ));
            lib.addBook(new Book("Pride and Prejudice", "Jane Austen", Genre.ROMANCE, 266));
            JsonWriter writer = new JsonWriter("./data/testWriterLibrary.json");
            writer.open();
            writer.write(lib);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterLibrary.json");
            lib = reader.read();
            assertEquals("Person", lib.getName());
            ArrayList<Book> books = lib.getBooks();
            assertEquals(2, books.size());
            checkBook("Hunger Games", "Suzanne Collins", Genre.YOUNG_ADULT, 364, books.get(0));
            checkBook("Pride and Prejudice", "Jane Austen", Genre.ROMANCE, 266, books.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
