package ui.tabs;

import model.Book;
import ui.GUI;

import javax.swing.*;
import java.awt.*;

// Represents a GUI tab with Library displayed
public class LibraryTab extends Tab {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final String TITLE = "Your Library:";
    private JLabel title;
    private final GUI controller;
    private String lib;
    private JTextArea bookList;
    private JPanel panel;


    //EFFECTS: creates a GUI tab with library displayed
    public LibraryTab(GUI controller) {
        super(controller);
        this.controller = controller;

        setLayout(null);

        placeRefreshButton();
        placeTitle();
        placeBookList();
    }

    //EFFECTS: creates title at top of console
    private void placeTitle() {
        title = new JLabel(TITLE, JLabel.CENTER);
        this.add(title);
        title.setBounds(200, 20, 100, 100);
    }

    //EFFECTS: creates a list of books in the library as buttons
    private void placeBookList() {
        panel = new JPanel();
        panel.setBackground(Color.pink);
        panel.setLayout(new FlowLayout());
        panel.setBounds(50, 100, 300, 400);

        for (Book bk : controller.getLibrary().getBooks()) {
            JButton bookButton = new JButton(bk.getTitle() + " By: " + bk.getAuthor());
            panel.add(bookButton);
            initializeBookButton(bookButton, bk);
        }
        this.add(panel);
        this.revalidate();
        this.repaint();
    }

    //EFFECTS: creates the pop-up pane with book details, remove button, and ok button
    private void initializeBookButton(JButton bookButton, Book bk) {
        bookButton.addActionListener(e -> {
            JDialog bookInfo = new JDialog(controller, "Book Details", true);
            bookInfo.setLayout(new BorderLayout());

            JLabel bookDetailsLabel = new JLabel("Title: " + bk.getTitle() + "\nAuthor: " + bk.getAuthor()
                    + "\nGenre: " + bk.getGenre().toString().toLowerCase() + "\nNumber of Pages: " + bk.getLength());
            bookInfo.add(bookDetailsLabel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());

            JButton removeButton = new JButton("Remove Book");
            removeButton.addActionListener(e1 -> {
                removeBook(bookButton, panel, bk);
                bookInfo.dispose();
            });
            buttonPanel.add(removeButton);

            JButton okButton = new JButton("Ok");
            okButton.addActionListener(e1 -> {
                bookInfo.dispose();
            });
            buttonPanel.add(okButton);
            bookInfo.add(buttonPanel, BorderLayout.SOUTH);

            bookInfo.pack();
            bookInfo.setVisible(true);
        });
    }

    // MODIFIES: this
    // EFFECTS: removes book from library and removes the bookButton from panel
    private void removeBook(JButton bookButton, JPanel panel, Book bk) {
        controller.getLibrary().removeBook(bk);
        panel.remove(bookButton);
        panel.revalidate();
        panel.repaint();
    }

    // EFFECTS: creates a refresh button
    private void placeRefreshButton() {
        JButton refreshButton = new JButton("Refresh");

        refreshButton.setBounds(400, 400, 70, 70);
        this.add(refreshButton);
        refreshButton.addActionListener(e -> {
            if (panel != null) {
                this.remove(panel);
                revalidate();
                repaint();
            }
            placeBookList();
            revalidate();
            repaint();
        });
    }


}
