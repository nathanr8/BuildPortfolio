package ui;


import model.Portfolio;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class PortfolioList extends JPanel {
    private DefaultListModel<String> listModel;
    private JList<Portfolio> list2;
    private final DisplayPanelPortfolio displayPanelPortfolio;
    private final MarketManager marketManager;

    //Instantiates PortfolioList Panel
    public PortfolioList(MarketManager marketManager, DisplayPanelPortfolio displayPanelPortfolio) {
        this.displayPanelPortfolio = displayPanelPortfolio;
        this.marketManager = marketManager;
        reload();
    }

    //"Refreshes" List so all changes are shown so there is no discrepancy between that is in the list and
    // what is shown.
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

    //Initialises List with ListModel and sets basic controls to navigate through the list
    private void initializeJList() {
        list2 = new JList(listModel);
        list2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list2.setLayoutOrientation(JList.VERTICAL);
        list2.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(list2);
        listScroller.setPreferredSize(new Dimension(500, 500));
        add(listScroller);

        //Listener detects selection of investment and displays the appropriate Data
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

    //Returns JLIST object so that other classes can operate on it.
    public JList getJList() {
        return this.list2;
    }





}
