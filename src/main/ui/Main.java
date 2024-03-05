package ui;

import java.io.FileNotFoundException;

// Runs Reader's Room as a console based interface.
public class Main {
    public static void main(String[] args) {
        try {
            new ReadersRoom();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
