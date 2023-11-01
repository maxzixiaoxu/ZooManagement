package ui.menu;

import model.Zoo;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents application's name zoo menu window
public class NameMenu extends Menu {

    // labels
    private JLabel curName;
    private JLabel titleLabel;

    private JButton enter;
    private JTextField input;


    public NameMenu(Zoo zoo) {
        super(zoo);
        initializeApp();
        initializeLabels();
        initializeButtons();
        initializeListeners();
        addToPanel();
        panel.revalidate();
        panel.repaint();

    }

    // MODIFIES: this
    // EFFECTS: Initializes and adds text to all the present labels in this menu
    @Override
    public void initializeLabels() {
        curName = new JLabel("The current name of the zoo is: " + zoo.getName());
        titleLabel = new JLabel("Enter/Change Zoo Name");
    }

    // MODIFIES: this
    // EFFECTS: Initializes all action listeners
    @Override
    public void initializeListeners() {
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoo.setName(input.getText());
                zoo.logNameEvent();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds buttons that control the actions naming the zoo
    private void initializeButtons() {
        input = new JTextField(20);
        enter = new JButton("Enter");

    }

    // MODIFIES: Menu (super)
    // EFFECTS: adds all necessary elements to the panel
    @Override
    public void addToPanel() {
        panel.add(curName);
        panel.add(titleLabel);
        panel.add(input);
        panel.add(enter);
    }

    // MODIFIES: Menu (super)
    // EFFECTS: initializes the JFrame components
    private void initializeApp() {
        frame.setTitle("The Zoo: Name Menu");
        panel.setLayout(new GridLayout(8, 2, 20, 10));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }

}
