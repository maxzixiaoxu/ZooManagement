package ui.menu;

import model.EventLog;
import model.Zoo;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// represents application's start menu window
public class StartMenu extends Menu {

    private static final String JSON_STORE = "./data/myFile.json";

    // labels
    private JLabel titleLabel;

    //buttons
    private JButton name;
    private JButton create;
    private JButton view;
    private JButton save;
    private JButton load;

    // json
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public StartMenu(Zoo zoo) {
        super(zoo);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeApp();
        initializeLabels();
        initializeButtons();
        initializeListeners();
        JFrame image = new JFrame();
        JPanel imagePanel = new JPanel();
        imagePanel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        imagePanel.setLayout(new GridLayout(5, 0, 0, 0));
        imagePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        image.add(imagePanel, BorderLayout.SOUTH);
        image.setSize(1500, 1500);
        JLabel welcome = new JLabel("WELCOME TO THE ZOO!");
        welcome.setFont(new Font("Serif", Font.PLAIN, 110));
        imagePanel.add(welcome);
        image.add(new JLabel(new ImageIcon("./data/tobs.jpg")));
        image.setVisible(true);
        addToPanel();
        panel.revalidate();
        panel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Initializes and adds text to all the present labels in this menu
    @Override
    public void initializeLabels() {
        titleLabel = new JLabel("Welcome to the Zoo");
    }

    // MODIFIES: this
    // EFFECTS: Initializes all action listeners with helper functions
    @Override
    public void initializeListeners() {
        initializeNameListener();
        initializeCreateEditListeners();
        initializeSaveLoadListeners();
    }

    // MODIFIES: this
    // EFFECTS: Initializes button listener for name
    private void initializeNameListener() {
        name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NameMenu nameMenu = new NameMenu(zoo);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Initializes button listeners for create and edit
    private void initializeCreateEditListeners() {
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateMenu createMenu = new CreateMenu(zoo);
            }
        });

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewMenu editMenu = new ViewMenu(zoo);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Initializes button listeners for save and load
    private void initializeSaveLoadListeners() {

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFunction();
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFunction();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: If save function is successful, it saves the zoo onto the JSON file
    // else, provides error message
    private void saveFunction() {
        try {
            jsonWriter.open();
            jsonWriter.write(zoo);
            jsonWriter.close();
            panel.add(new JLabel("Saved " + zoo.getName() + " to " + JSON_STORE));
            zoo.logSaveEvent();
            panel.revalidate();
            panel.repaint();
        } catch (FileNotFoundException e) {
            panel.add(new JLabel("Unable to write to file: " + JSON_STORE));
            panel.revalidate();
            panel.repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS: if load function is successful, it loads the zoo from the json file to be viewed
    // else, provides error message
    private void loadFunction() {
        try {
            zoo = jsonReader.read();
            panel.add(new JLabel("Loaded " + zoo.getName() + " from " + JSON_STORE));
            zoo.logLoadEvent();
            panel.revalidate();
            panel.repaint();
        } catch (IOException e) {
            panel.add(new JLabel("Unable to read from file: " + JSON_STORE));
            panel.revalidate();
            panel.repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds buttons that control the actions to handle naming zoo, creating and viewing habitat,
    // loading and saving
    private void initializeButtons() {
        name = new JButton("Enter/Change Zoo Name");
        create = new JButton("Create Habitat");
        view = new JButton("View Habitat");
        load = new JButton("Load Zoo");
        save = new JButton("Save Zoo");
    }

    // MODIFIES: Menu (super)
    // EFFECTS: adds all the necessary components to the panel
    @Override
    public void addToPanel() {
        panel.add(titleLabel);
        panel.add(name);
        panel.add(create);
        panel.add(view);
        panel.add(save);
        panel.add(load);

    }

    // MODIFIES: Menu (super)
    // EFFECTS: initializes the JFrame components present in the menu
    private void initializeApp() {
        frame.setTitle("The Zoo");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                zoo.printLog(EventLog.getInstance());
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(8, 2, 20, 10));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }

}
