//package ui.tabs;
//
//import ui.BuildPortfolioUI;
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
//
//public class PortfolioTab extends Tab {
//
//    protected BuildPortfolioUI buildPortfolioUI;
//
//    public PortfolioTab(BuildPortfolioUI controller) {
//        super(controller);
//
//        setLayout(new GridLayout(3, 1));
//
////        placeViewPortfolioList();
////        placeLoadButton();
////        placeSaveButton();
//    }
//
////    //MODIFIES: this
////    //EFFECTS: loads market and updates myMarket status when clicked
////    private void placeViewPortfolioList() {
////        JButton b1 = new JButton(ButtonNames.VI.getValue());
////        JPanel buttonRow = formatButtonRow(b1);
////        buttonRow.setSize(WIDTH, HEIGHT / 6);
////
////        b1.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                String buttonPressed = e.getActionCommand();
////                if (buttonPressed.equals(ButtonNames.LOAD.getValue())) {
////                    //
////                }
////            }
////        });
////
////        this.add(buttonRow);
////    }
//
//}
