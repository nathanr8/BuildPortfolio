package ui;

import javax.swing.*;

// Runs the main MarketManager GUI
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Screen("Investment Portfolio Manager");
                frame.setSize(1200, 800);
                frame.setVisible(true);
            }
        });

    }
}
