package ui;

import model.Zoo;
import ui.menu.StartMenu;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        new StartMenu(new Zoo("default"));
    }
}
