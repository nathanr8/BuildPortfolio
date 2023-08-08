package ui;


import model.Portfolio;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

// CITATION: Code inspired by the following sources:
// https://www.youtube.com/watch?v=Kmgo00avvEw&list=LL&index=5&t=16276s
// https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/JList.html
// https://stackoverflow.com/questions/1097366/java-swing-revalidate-vs-repaint
// https://www.geeksforgeeks.org/arraylist-removeall-method-in-java-with-examples/
// https://www.youtube.com/watch?v=OJSAnlzXRDk


// Creates display for the names of portfolios to be shown inside the portfolio panel
public class PortfolioList extends JPanel {
    private DefaultListModel<String> listModel;
    private JList<Portfolio> list2;
    private final DisplayPanelPortfolio displayPanelPortfolio;
    private final MarketManager marketManager;

    //CONSTRUCTOR
    //EFFECTS: Instantiates PortfolioList Panel using MarketManager and DisplayPanelPortfolio
    //         calls reload to refresh PortfolioList display
    public PortfolioList(MarketManager marketManager, DisplayPanelPortfolio displayPanelPortfolio) {
        this.displayPanelPortfolio = displayPanelPortfolio;
        this.marketManager = marketManager;
        reload();
    }

    //EFFECTS: "reloads" list so all changes are shown
    public void reload() {
        this.removeAll();
        this.revalidate();
        this.repaint();
        listModel = new DefaultListModel<>();
        for (Portfolio p: marketManager.myMarket.getPortfolioList()) {
            listModel.addElement(p.getPortfolioName());
        }
        initializeJList();
    }

    //EFFECTS: Initialises List with ListModel, controls to navigate through the list,
    //         calls display info to show information for portfolio selected from display panel list
    private void initializeJList() {
        list2 = new JList(listModel);
        list2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list2.setLayoutOrientation(JList.VERTICAL);
        list2.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(list2);
        listScroller.setPreferredSize(new Dimension(500, 500));
        add(listScroller);

        list2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selected = list2.getSelectedIndex();
                displayPanelPortfolio.removeAll();
                displayPanelPortfolio.revalidate();
                displayPanelPortfolio.repaint();
                displayPanelPortfolio.displayInfo(marketManager.myMarket.getPortfolioList().get(selected));
            }
        });
    }

    //EFFECTS: Returns JList object so other classes can operate on it.
    public JList getJList() {
        return this.list2;
    }
}
