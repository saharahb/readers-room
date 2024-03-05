package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a library of books.
public class Library implements Writable {

    private String name;
    private ArrayList<Book> books;

    // EFFECTS: constructs a library with a list of books read.
    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    // getters
    public ArrayList<Book> getBooks() {
        return books;
    }

    public String getName() {
        return this.name;
    }


    // MODIFIES: this
    // EFFECTS: adds given book to list of books read in library.
    public void addBook(Book bk) {
        books.add(bk);
    }

    // MODIFIES: this
    // EFFECTS: removes book from list of books and returns true, or false if book is not in library.
    public boolean removeBook(Book bk) {
        return books.remove(bk);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("books", booksToJson());
        return json;
    }

    // EFFECTS: returns books in library as a JSON array
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : books) {
            jsonArray.put(b.toJson());
        }
        return jsonArray;
    }
}
