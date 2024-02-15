package model;

import java.util.ArrayList;
import java.util.List;

public class Library {

    public ArrayList<Book> books;
//    private String ownerName;

//    private String ownerName;

    // EFFECTS: constructs a library with a list of books read.
    public Library() {

        this.books = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

//    public String getName() { return ownerName;}

    // MODIFIES: this
    // EFFECTS: adds given book to list of books read in library.
    public void addBook(Book bk) {
        books.add(bk);
    }

    // MODIFIES: this
    // EFFECTS: removes given book from list of books read in library.
    public void removeBook(Book bk) {
        books.remove(bk);
    }
}
