package ui;

import model.Book;
import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.AddBookTab;
import ui.tabs.HomeTab;
import ui.tabs.LibraryTab;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GUI extends JFrame {
    private int count;

    private final JTabbedPane sidebar;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    private static final String JSON_STORE = "./data/readersroom.json";
    private Library library;
    private Scanner input;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;


    public GUI() {
        super("Reader's Room");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        library = new Library("Saharah");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        pack();
        setVisible(true);
    }


    public void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        JPanel addBookTab = new AddBookTab(this);
        JPanel libraryTab = new LibraryTab(this);

        sidebar.add(homeTab, 0);
        sidebar.setTitleAt(0, "Home");
        sidebar.add(addBookTab, 1);
        sidebar.setTitleAt(1, "Add books");
        sidebar.add(libraryTab, 2);
        sidebar.setTitleAt(2, "Your Library");
    }

    //EFFECTS: returns sidebar of this UI
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }

    // EFFECTS: returns the library
    public Library getLibrary() {
        return library;
    }

    //EFFECTS: saves the library to file
    public void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(library);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Saved " + library.getName() + "'s Library to "
                    + JSON_STORE);
        } catch (FileNotFoundException j) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: loads library from file
    public void load() {
        try {
            library = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Loaded " + library.getName()
                    + "'s Library from " + JSON_STORE);
        } catch (IOException j) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public String viewLibrary() {
        StringBuilder lib = new StringBuilder();
        if (!library.getBooks().isEmpty()) {
            for (Book b : library.getBooks()) {
                lib.append("\n").append(b.getTitle()).append(b.getAuthor()).append(b.getGenre()).append(b.getLength());
            }
            return lib.toString();
        } else {
            return "No books in your library.";
        }
    }
}
