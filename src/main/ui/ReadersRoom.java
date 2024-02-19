package ui;

import model.Book;
import model.Genre;
import model.Library;
import model.exceptions.InvalidRatingException;

import java.util.Scanner;

// Runs Reader's Room as a console based interface.
public class ReadersRoom {

    private Library library;
    private Scanner input;

    public ReadersRoom() {
        run();
    }

    private void run() {
        boolean keepRunning = true;
        String command;

        init();

        System.out.println("\nWELCOME TO READER'S ROOM");

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
    // EFFECTS: initializes new library and Scanner
    private void init() {
        library = new Library();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    // EFFECTS: displays main menu of options to user
    private void mainMenu() {
        System.out.println("\nREADER'S ROOM");
        System.out.println("\nSelect from:");
        System.out.println("\tb -> add a book to your library");
        System.out.println("\tl -> view your personal library");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a book to library, using information inputted by user.
    private void addBook() {
        while (true) {
            System.out.print("Enter the title of the book you want to add: ");
            String title = input.next();
            System.out.print("\nWho is the author of this book?");
            String author = input.next();
            genreMenu();
            String genreInput = input.next().toLowerCase();
            Genre genre = processGenre(genreInput);
            System.out.println("How many pages are in the book?");
            Integer pages = input.nextInt();
            Book bk = new Book(title, author, genre, pages);
            library.addBook(bk);
            System.out.println("The book " + "'" + title + "'" + " was added to your library!");
            System.out.println("\nPress any key to add another book.");
            System.out.println("\nEnter m to return to main menu");
            String choice = input.next();
            if (choice.equals("m")) {
                break;
            }
        }
    }


    // EFFECTS: displays the list of genre options
    private void genreMenu() {
        System.out.println("\nSelect the book's genre from the following list");
        System.out.println("\tEnter n for Non-Fiction");
        System.out.println("\tEnter c for Children's");
        System.out.println("\tEnter y for Young Adult");
        System.out.println("\tEnter m for Mystery");
        System.out.println("\tEnter r for Romance");
        System.out.println("\tEnter f for Fantasy");
        System.out.println("\tEnter h for Historical Fiction");
        System.out.println("\tEnter t for Textbook");
    }

    // EFFECTS: returns the selected genre, null if genre does not exist
    private Genre processGenre(String genreInput) {
        switch (genreInput) {
            case "n":
                return Genre.NONFICTION;
            case "c":
                return Genre.CHILDREN;
            case "y":
                return Genre.YOUNG_ADULT;
            case "m":
                return Genre.MYSTERY;
            case "f":
                return Genre.FANTASY;
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

    // EFFECTS: displays the books in library as a numbered list.
    private void viewLibrary() {
        while (true) {
            if (!library.getBooks().isEmpty()) {
                System.out.println("Your Library titles:");
                Integer count = 1;
                for (Book bk : library.getBooks()) {
                    System.out.println(count + ". " + bk.getTitle());
                    count++;
                }
                System.out.println("Want more details on a title? " + "\nWant to leave a rating or review? \n"
                        + "\nEnter the number of the book you would like to view."
                        + "\nEnter 0 to return to main menu.");
                input.nextLine();
                Integer bookInput = input.nextInt();
                if (bookInput == 0) {
                    break;
                } else {
                    selectBook(bookInput);
                }
            } else {
                System.out.println("Looks like your library is empty :(");
                break;
            }
        }
    }

    // EFFECTS: processes number input and displays book info of corresponding book in list.
    private void selectBook(Integer bookInput) {
        if (bookInput > 0 && bookInput <= library.getBooks().size()) {
            Book selectedBook = library.getBooks().get(bookInput - 1);
            displayBookDetails(selectedBook);
        } else {
            System.out.println("Invalid selection. Please enter a number between 1 and " + library.getBooks().size());
            Integer newBookInput = input.nextInt();
            selectBook(newBookInput);
        }
    }

    // EFFECTS: displays information related to selected book.
    private void displayBookDetails(Book bk) {
        while (true) {
            System.out.println("Title: " + bk.getTitle());
            System.out.println("By: " + bk.getAuthor());
            System.out.println("Genre: " + bk.getGenre());
            System.out.println("Number of Pages: " + bk.getLength());
            if (bk.getRating() != null) {
                System.out.println("Your Rating:" + bk.getRating());
            }
            if (bk.getReview() != null) {
                System.out.println("Your Review:" + bk.getReview());
            }
            System.out.println("Enter b to go back, or r to leave a rating or review.");
            String choice = input.next();
            if (choice.equals("b")) {
                break;
            } else if (choice.equals("r")) {
                rateOrReview(bk);
            }
        }
    }

    // EFFECTS: processes choice input and calls either rateBook() or reviewBook()
    private void rateOrReview(Book bk) {
        System.out.println("\nEnter a to rate the book out of 5 stars, or enter b to leave a review.");
        String choice = input.next();
        if (choice.equals("a")) {
            rateBook(bk);
        } else if (choice.equals("b")) {
            reviewBook(bk);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // REQUIRES: input of integer between 0-5
    // MODIFIES: book
    // EFFECTS: adds rating to selected book.
    private void rateBook(Book bk) {
        System.out.println("How many stars do you give this book out of 5?");
        Integer stars = input.nextInt();
        try {
            bk.addRating(stars);
        } catch (InvalidRatingException e) {
            System.out.println("Invalid rating. Please enter a number from 0 to 5.");
            rateBook(bk);
        }
    }

    // MODIFIES: book
    // EFFECTS: adds review to selected book's list of reviews.
    private void reviewBook(Book bk) {
        System.out.println("Enter review here: ");
        String review = input.next();
        bk.addReview(review);
    }

}
