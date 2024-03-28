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


    //EFFECTS: creates a JScrollPane with the list of books in library
    private void placeBookList() {
        panel = new JPanel();
        panel.setBackground(Color.pink);
        panel.setLayout(new FlowLayout());
        panel.setBounds(50, 100, 300, 400);
        for (Book bk : controller.getLibrary().getBooks()) {
            JButton bookButton = new JButton(bk.getTitle() + "By: " + bk.getAuthor());
            panel.add(bookButton);

            bookButton.addActionListener(e -> {
                if (!controller.getLibrary().getBooks().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Title: " + bk.getTitle()
                            + "\nAuthor: " + bk.getAuthor() + "\nGenre: " + bk.getGenre().toString().toLowerCase()
                            + "\nNumber of pages: " + bk.getLength());
                } else {
                    JOptionPane.showMessageDialog(this, "No books in your library.");
                }
            });
        }
        this.add(panel);
        this.revalidate();
        this.repaint();
    }

    //EFFECTS: creates a refresh button
    private void placeRefreshButton() {
        JButton refreshButton = new JButton("Refresh");

        refreshButton.setBounds(400, 400, 70, 70);
        this.add(refreshButton);
        refreshButton.addActionListener(e -> {
            if (!panel.equals(null)) {
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
