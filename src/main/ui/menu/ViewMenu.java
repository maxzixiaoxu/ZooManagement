package ui.menu;

import model.Habitat;
import model.Zoo;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// represents application's view habitat menu window
public class ViewMenu extends Menu {
    
    private ArrayList<JLabel> habitatList;

    // labels
    private JLabel titleLabel;
    private JLabel chooseLabel;
    private JLabel viewLabel;
    private JLabel nameLabel;
    private JLabel tempLabel;
    private JLabel capLabel;

    private JButton enter1;
    private JTextField input;

    public ViewMenu(Zoo zoo) {
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
        titleLabel = new JLabel("All Habitats:");
        habitatList = new ArrayList<>();
        for (Habitat h : zoo.getHabitats()) {
            String habitat = h.getName();
            habitatList.add(new JLabel(habitat));
        }
        viewLabel = new JLabel("Choose habitat to view: ");
    }

    // MODIFIES: this
    // EFFECTS: Initializes all action listeners
    @Override
    public void initializeListeners() {
        enter1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Habitat habitat = null;
                for (Habitat h : zoo.getHabitats()) {
                    if (input.getText().equals(h.getName())) {
                        habitat = h;
                    }
                }
                if (habitat == null) {
                    panel.add(new JLabel("Invalid habitat!"));
                    panel.revalidate();
                    panel.repaint();
                } else {
                    panel.add(new JLabel("Name: " + habitat.getName()));
                    panel.add(new JLabel("Average Temperature: " + habitat.getAverageTemperature()));
                    panel.add(new JLabel("Capacity: " + habitat.getCapacity()));
                    habitat.logViewEvent();
                    panel.revalidate();
                    panel.repaint();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds buttons that control the actions to viewing the habitat
    private void initializeButtons() {
        input = new JTextField(20);
        enter1 = new JButton("Enter");

    }

    // MODIFIES: Menu (super)
    // EFFECTS: adds all the necessary components to the panel
    @Override
    public void addToPanel() {
        panel.add(titleLabel);
        for (JLabel l : habitatList) {
            panel.add(l);
        }
        panel.add(createSpaceLabel());
        panel.add(viewLabel);
        panel.add(input);
        panel.add(enter1);
    }

    // MODIFIES: Menu (super)
    // EFFECTS: initializes the JFrame components
    private void initializeApp() {
        frame.setTitle("The Zoo: View Habitat Menu");
        panel.setLayout(new GridLayout(8, 2, 20, 10));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }

}
