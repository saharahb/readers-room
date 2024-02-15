package ui;

import model.Book;
import model.Genre;
import model.Library;
import model.exceptions.invalidRatingException;

import java.util.ArrayList;
import java.util.Scanner;


public class ReadersRoom {


    private Library library;
    private Scanner input;

    public ReadersRoom() {
        this.library = new Library();
        run();
    }

    private void run() {
        boolean keepRunning = true;
        String command;

        init();

        while (keepRunning) {
            mainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\n Thank you for using Reader's Room, goodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("b")) {
            addBook();
        } else if (command.equals("l")) {
            viewLibrary();
        } else {
            System.out.println("Invalid option, please select b, l, or q.");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes account
    private void init() {
        library = new Library();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays main menu of options to user
    private void mainMenu() {
        System.out.println("\nWELCOME TO READER'S ROOM");
        System.out.println("\nSelect from:");
        System.out.println("\tb -> add a book to your library");
        System.out.println("\tl -> view your personal library");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a book to library, using information inputted by user
    private void addBook() {
        System.out.print("Enter the title of the book you want to add: ");
        String title = input.next();
        System.out.print("Who is the author of this book?");
        String author = input.next();
        genreMenu();
        String genreInput = input.next().toLowerCase();
        Genre genre = processGenre(genreInput);
        System.out.println("How many pages are in the book?");
        Integer pages = input.nextInt();

        Book bk = new Book(title, author, genre, pages);
        library.addBook(bk);
        System.out.println("The book " + "'" + title + "'" + " was added to your library!");
    }


    // EFFECTS: displays the list of genre options
    private void genreMenu() {
        System.out.println("\nSelect the book's genre from the following list");
        System.out.println("\tEnter n for Non-Fiction");
        System.out.println("\tEnter c for Children's");
        System.out.println("\tEnter m for Mystery");
        System.out.println("\tEnter r for Romance");
        System.out.println("\tEnter t for Textbook");
        System.out.println("\tEnter h for Historical Fiction");

    }

    // EFFECTS: returns the selected genre, null if genre does not exist
    private Genre processGenre(String genreInput) {
        switch (genreInput) {
            case "n":
                return Genre.NONFICTION;
            case "c":
                return Genre.CHILDREN;
            case "m":
                return Genre.MYSTERY;
            case "r":
                return Genre.ROMANCE;
            case "t":
                return Genre.TEXTBOOK;
            case "h":
                return Genre.HISTORICAL_FICTION;
            default:
                System.out.println("Invalid input, please enter one of the options above.");
                String newGenreInput = input.next().toLowerCase();
                return processGenre(newGenreInput);
        }
    }

    private void viewLibrary() {
        if (!library.getBooks().isEmpty()) {
            System.out.println("Your Library titles:");
            Integer count = 1;
            for (Book bk : library.getBooks()) {
                System.out.println(count + ". " + bk.getTitle());
                count++;
            }
            System.out.println("Want more details on a title? \nOr want to leave a rating or review? \n" +
                    "Enter the number of the book you would like to view.");
            Integer bookInput = input.nextInt();
            selectBook(bookInput);
        } else {
            System.out.println("Looks like your library is empty :( Enter b to add books!");
        }
    }

    private void selectBook(Integer bookInput) {
        if (bookInput > 0 && bookInput <= library.getBooks().size()) {
            Book selectedBook = library.getBooks().get(bookInput - 1);
            System.out.println( "Title: " + selectedBook.getTitle());
            System.out.println("By: " + selectedBook.getAuthor());
            System.out.println("Genre: " + selectedBook.getGenre());
            System.out.println("Number of Pages: " + selectedBook.getLength());
            try {
                rateOrReview(selectedBook);
            } catch (invalidRatingException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Invalid selection. Please enter a number between 1 and " + library.getBooks().size());
            Integer newBookInput = input.nextInt();
            selectBook(newBookInput);
        }
    }

    private void rateOrReview(Book bk) throws invalidRatingException {
        System.out.println("Enter 1 to rate the book out of 5 stars, or enter 2 to leave a review.");
        Integer choice = input.nextInt();
        if (choice == 1) {
            rateBook(bk);
        } else if (choice == 2) {
            reviewBook(bk);
        }
    }

    private void rateBook(Book bk) {
        System.out.println("How many stars do you give this book out of 5?");
        Integer stars = input.nextInt();
        try {
            bk.addRating(stars);
        } catch (invalidRatingException e) {
            System.out.println("Invalid rating. Please enter a number from 0 to 5.");
        }
    }

    private void reviewBook(Book bk) {
        System.out.println("Enter review here: ");
        String review = input.next();
        bk.addReview(review);
    }

}







