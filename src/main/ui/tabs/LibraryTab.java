package ui.tabs;

import ui.GUI;

import javax.swing.*;
import java.awt.*;

public class LibraryTab extends Tab {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final String TITLE = "Your Library:";
    private JLabel title;
    private final GUI controller;
    private String lib;
    private JTextArea bookList;

    public LibraryTab(GUI controller) {
        super(controller);
        this.controller = controller;

        setLayout(null);

        placeRefreshButton();
        placeTitle();
        placeBookList();
//        placeBooks();

    }

    //EFFECTS: creates title at top of console
    private void placeTitle() {
        title = new JLabel(TITLE, JLabel.CENTER);
        this.add(title);
        title.setBounds(300, 20, 100, 100);
    }

    // EFFECTS: creates buttons for every book in library
//    public void placeBooks() {
//        for (Book bk : controller.getLibrary().getBooks()) {
//            JButton bookButton = new JButton(bk.getTitle() + " By: " + bk.getAuthor());
//            this.add(bookButton);
//            bookButton.addActionListener(e -> {
//                JOptionPane.showMessageDialog(this, bk.getTitle() +
//                        bk.getAuthor() + bk.getGenre() + bk.getLength());
//            });
//        }
//    }

    private void placeBookList() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBounds(50, 100, 300, 400);

        bookList = new JTextArea();
        bookList.setEditable(false);

        panel.add(new JScrollPane(bookList), BorderLayout.CENTER);
        lib = controller.viewLibrary();
        bookList.setText(lib);

        this.add(panel);
        this.revalidate();
        this.repaint();
    }

    //EFFECTS: refresh button
    private void placeRefreshButton() {
        JButton refreshButton = new JButton("Refresh");

        refreshButton.setBounds(0, 0, 70, 70);
        this.add(refreshButton);
        refreshButton.addActionListener(e -> {
            lib = controller.viewLibrary();
            bookList.setText(lib);
            revalidate();
            repaint();
        });


    }


}
