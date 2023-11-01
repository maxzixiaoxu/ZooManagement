package ui.menu;

import model.Zoo;

import javax.swing.*;
import java.awt.*;

public abstract class Menu extends JFrame {

    protected Zoo zoo;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    protected JFrame frame;
    protected JPanel panel;

    // creates a generic menu
    public Menu(Zoo zoo) {
        this.zoo = zoo;
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle("My Zoo");
        frame.setVisible(true);
        frame.add(panel, BorderLayout.CENTER);
        panel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(new GridLayout(5, 2, 20, 10));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }

    // EFFECTS: generates empty label to act as an empty space
    protected JLabel createSpaceLabel() {
        JLabel space = new JLabel("   ");
        return space;
    }

    // MODIFIES: this
    // EFFECTS: initializes all present labels to be added to the panel that are not empty or need to
    // added multiple times
    public abstract void initializeLabels();

    // MODIFIES: this
    // EFFECTS: initializes all listeners for every button present in the menu
    public abstract void initializeListeners();

    // MODIFIES: this
    // EFFECTS: adds all the necessary components to the panel
    public abstract void addToPanel();

}
