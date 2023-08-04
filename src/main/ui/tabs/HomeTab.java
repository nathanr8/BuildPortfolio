//package ui.tabs;
//
//import model.Investment;
//import model.Market;
//import persistence.Reader;
//import persistence.Writer;
//import ui.BuildPortfolioUI;
//import ui.ButtonNames;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//public class HomeTab extends Tab {
//
//    private static final String INIT_GREETING = "Hello";
//    private JLabel greeting;
//    protected Market myMarket2;
//    protected BuildPortfolioUI buildPortfolioUI;
//    protected static final String JSON_STORAGE = "./data/market.json";
//    protected Writer jsonWriter;
//    protected Reader jsonReader;
//
//    //EFFECTS: constructs a home tab for console with buttons and a greeting
//    public HomeTab(BuildPortfolioUI controller) {
//        super(controller);
//
//
//        setLayout(new GridLayout(3, 1));
//
//        placeGreeting();
//        placeLoadButton();
//        placeSaveButton();
//        placeviewInvestments();
//    }
//
//    //EFFECTS: creates greeting at top of console
//    private void placeGreeting() {
//        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
//        greeting.setSize(WIDTH, HEIGHT / 3);
//        this.add(greeting);
//    }
//
//    //MODIFIES: this
//    //EFFECTS: loads market and updates myMarket status when clicked
//    private void placeLoadButton() {
//        JButton b1 = new JButton(ButtonNames.LOAD.getValue());
//        JPanel buttonRow = formatButtonRow(b1);
//        buttonRow.setSize(WIDTH, HEIGHT / 6);
//
//        b1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String buttonPressed = e.getActionCommand();
//                if (buttonPressed.equals(ButtonNames.LOAD.getValue())) {
//                    loadMarket();
//                }
//            }
//        });
//
//        this.add(buttonRow);
//    }
//
//    //MODIFIES: this
//    //EFFECTS: loads market and updates myMarket status when clicked
//    private void placeSaveButton() {
//        JButton b2 = new JButton(ButtonNames.SAVE.getValue());
//        JPanel buttonRow = formatButtonRow(b2);
//        buttonRow.setSize(WIDTH, HEIGHT / 6);
//
//        b2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String buttonPressed = e.getActionCommand();
//                if (buttonPressed.equals(ButtonNames.SAVE.getValue())) {
//                    saveMarket();
//                }
//            }
//        });
//
//        this.add(buttonRow);
//    }
//
//    //MODIFIES: this
//    //EFFECTS: displays investments in market when clicked
//    private void placeviewInvestments() {
//        JButton b3 = new JButton(ButtonNames.VIEW_INVESTMENTS.getValue());
//        JPanel buttonRow = formatButtonRow(b3);
//        buttonRow.setSize(WIDTH, HEIGHT / 6);
//
//        b3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String buttonPressed = e.getActionCommand();
//                if (buttonPressed.equals(ButtonNames.VIEW_INVESTMENTS.getValue())) {
//                    viewInvestments();
//                }
//            }
//        });
//
//        this.add(buttonRow);
//    }
//
//    // EFFECTS: displays all investments available in the market
//    private void viewInvestments() {
////        System.out.println("Currently available investments in the market: ");
////        System.out.printf("| %-10s | %-6s | %-20s | %-12s |%n", "Name", "Price", "Expected Return (%)", "Industry");
////        System.out.printf("| %-54s |%n", "---------------------------------------------------------");
////        // EFFECTS: displays all investments available in the market
////        for (Investment i : myMarket.getInvestmentList()) {
////            System.out.printf("| %-10s | %-6s | %-20s | %-12s |%n", i.getInvestmentname(), i.getPrice(),
////                    i.getReturnPercentage(), i.getSector());
////        }
//    }
//
//
//
//
//
//
//
//
//
//
//
//    // MODIFIES: this
//    // EFFECTS: loads market from file
//    protected void loadMarket() {
//        try {
//            myMarket2 = jsonReader.read();
//            System.out.println("Loaded " + "market" + " from " + JSON_STORAGE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORAGE);
//        }
//    }
//
//    // EFFECTS: saves the market to file
//    protected void saveMarket() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(myMarket2);
//            jsonWriter.close();
//            System.out.println("Saved " + "market" + " to " + JSON_STORAGE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORAGE);
//        }
//    }
//
//
//
//}
