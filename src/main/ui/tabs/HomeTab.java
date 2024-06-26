package ui.tabs;

import ui.GUI;

import javax.swing.*;
import java.awt.*;

// Represents a GUI tab displaying home page
public class HomeTab extends Tab {
    private static final String INIT_GREETING = "Welcome to Reader's Room";
    private JLabel greeting;
    private final GUI controller;
    private Font font;


    // EFFECTS constructs a home tab for console with greetings and save/load buttons
    public HomeTab(GUI controller) {
        super(controller);
        this.controller = controller;

        setLayout(new GridLayout(3, 1));

        placeGreeting();

        placeLogo();

        placeSaveLoadButtons();

    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setBackground(Color.pink);
        greeting.setSize(WIDTH, HEIGHT / 4);
        font = new Font("Georgia", Font.PLAIN, 24);
        greeting.setFont(font);
        this.add(greeting);
    }

    //EFFECTS: creates a logo in the middle of the console
    private void placeLogo() {
        ImageIcon logo = new ImageIcon("images/logo_rr.jpg");
        Image scaledImage = logo.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledLogoIcon);

        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(logoLabel);
    }

    //EFFECTS: creates a save and load button
    private void placeSaveLoadButtons() {
        JButton saveButton = new JButton("Save Data");
        JButton loadButton = new JButton("Load Data");

        JPanel buttonRow = formatButtonRow(saveButton);
        buttonRow.add(loadButton);
        buttonRow.setSize(WIDTH, HEIGHT / 10);

        saveButton.addActionListener(e -> {
            controller.save();
        });

        loadButton.addActionListener(e -> {
            controller.load();
        });

        this.add(buttonRow);
    }
}
