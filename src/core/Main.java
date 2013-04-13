package core;

import gui.Window;

/**
 * Main entry point of the application.
 * Creates the window and sets it to visible.
 */
public class Main {
    public static void main(String[] args) {
        Window w = new Window();

        w.setVisible(true);
    }
}
