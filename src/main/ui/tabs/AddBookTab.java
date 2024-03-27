package ui.tabs;

import model.Book;
import model.Genre;
import ui.GUI;

import javax.swing.*;
import java.awt.*;

//Represents a GUI tab where user adds book info

public class AddBookTab extends Tab {

    private final GUI controller;
    private String title;
    private String author;
    private Genre genre;
    private int length;
    private LibraryTab libraryTab;

    private JTextField titleField;
    private JTextField authorField;
    private JComboBox genreBox;
    private JTextField lengthField;
    private String lengthVal;


    private final String[] genres = {"Non-fiction", "Children", "Young Adult", "Mystery", "Romance", "Fantasy",
            "Historical Fiction", "Textbook"};

    //EFFECTS: creates a GUI tab where user adds book info
    public AddBookTab(GUI controller) {
        super(controller);
        this.controller = controller;

        setLayout(new GridLayout(5, 1));

        placeAddBookTitle();
        placeAddBookAuthor();
        placeAddBookGenre();
        placeAddBookLength();

        placeAddBookButton();


    }

    //EFFECTS: creates text fields and button for adding book title
    private void placeAddBookTitle() {
        JLabel titleLabel = new JLabel("Enter book title:");
        titleField = new JTextField(6);
        this.add(titleLabel);
        this.add(titleField);
    }

    //EFFECTS: creates text fields and button for adding book author
    private void placeAddBookAuthor() {
        JLabel authorLabel = new JLabel("Enter the author's name:");
        authorField = new JTextField(6);
        this.add(authorLabel);
        this.add(authorField);
//        this.author = authorField.getText();
    }

    //EFFECTS: creates text field for adding book genre
    private void placeAddBookGenre() {
        JLabel genreLabel = new JLabel("Select the genre:");
        genreBox = new JComboBox<>(genres);
        this.add(genreLabel);
        this.add(genreBox);
    }

    //EFFECTS: returns the corresponding GENRE of the given string
    private static Genre mapStringToGenre(String genreString) {
        switch (genreString) {
            case "Non-fiction":
                return Genre.NONFICTION;
            case "Children":
                return Genre.CHILDREN;
            case "Young Adult":
                return Genre.YOUNG_ADULT;
            case "Mystery":
                return Genre.MYSTERY;
            case "Romance":
                return Genre.ROMANCE;
            case "Fantasy":
                return Genre.FANTASY;
            case "Historical Fiction":
                return Genre.HISTORICAL_FICTION;
            case "Textbook":
                return Genre.TEXTBOOK;
            default:
                return null;
        }
    }

    //EFFECTS: creates text field for adding book length
    private void placeAddBookLength() {
        JLabel lengthLabel = new JLabel("Enter the number of pages in this book:");
        lengthField = new JTextField(6);
        this.add(lengthLabel);
        this.add(lengthField);
    }

    //EFFECTS: checks if length inputted is an integer > 0
    private boolean isValidLength(String length) {
        try {
            int lengthValue = Integer.parseInt(length);
            return lengthValue > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //EFFECTS: creates an 'Add Book' button that saves book to library
    private void placeAddBookButton() {
        JButton addButton = new JButton("Add Book");
        addButton.setAlignmentX(CENTER_ALIGNMENT);
        addButton.setBackground(Color.green);
        this.add(addButton);
        addButton.addActionListener(e -> {
            this.title = titleField.getText();
            this.author = authorField.getText();
            String genreVal = String.valueOf(genreBox.getSelectedItem());
            this.genre = mapStringToGenre(genreVal);
            lengthVal = lengthField.getText();
            if (isValidLength(lengthVal)) {
                length = Integer.parseInt(lengthVal);
                Book bk = new Book(title, author, genre, length);
                controller.getLibrary().addBook(bk);
                JOptionPane.showMessageDialog(this, title + " was added to your library!");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a number of pages greater than zero.");
            }
        });

    }

}
