//package ui.tabs;
//
//import ui.BuildPortfolioUI;
//
//import javax.swing.*;
//import java.awt.*;
//
//public abstract class Tab extends JPanel {
//
//    protected final BuildPortfolioUI controller;
//
//    //REQUIRES: Market controller that holds this tab
//    public Tab(BuildPortfolioUI controller) {
//        this.controller = controller;
//    }
//
//    //EFFECTS: creates and returns row with button included
//    public JPanel formatButtonRow(JButton b) {
//        JPanel p = new JPanel();
//        p.setLayout(new FlowLayout());
//        p.add(b);
//
//        return p;
//    }
//
//    //EFFECTS: returns the SmartHomeUI controller for this tab
//    public BuildPortfolioUI getController() {
//        return controller;
//    }
//
//
//}
