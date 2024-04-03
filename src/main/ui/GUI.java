package ui;

import model.Event;
import model.EventLog;
import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.AddBookTab;
import ui.tabs.HomeTab;
import ui.tabs.LibraryTab;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents a GUI of Reader's Room app
public class GUI extends JFrame implements WindowListener {

    private final JTabbedPane sidebar;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    private static final String JSON_STORE = "./data/readersroom.json";
    private Library library;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    //EFFECTS: constructs GUI of Reader's Room app
    public GUI() {
        super("Reader's Room");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(this);

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


    //EFFECTS: creates 3 tabs in the sidebar
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

    @Override
    public void windowOpened(WindowEvent e) {
        // nothing
    }

    // EFFECTS: closes window
    @Override
    public void windowClosing(WindowEvent e) {
        dispose();
    }

    // EFFECTS: prints log to console after closing
    @Override
    public void windowClosed(WindowEvent e) {
        printLog(EventLog.getInstance());
    }

    // EFFECTS: prints log to console
    public void printLog(EventLog el) {
        for (Event event : el) {
            System.out.println(event.toString() + "\n\n");
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
