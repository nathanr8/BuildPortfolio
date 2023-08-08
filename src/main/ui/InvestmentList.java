package ui;

import model.Investment;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

// Displays the name of investments inside the investments panel
public class InvestmentList extends JPanel {
    private DefaultListModel<String> listModel;
    private JList<Investment> list;
    private final DisplayPanelInvestment displayPanelInvestment;
    private final MarketManager market;

    //EFFECTS: Instantiates InvestmentList Panel using MarketManager and DisplayPanelInvestment
    public InvestmentList(MarketManager marketManager, DisplayPanelInvestment displayPanelInvestment) {
        this.displayPanelInvestment = displayPanelInvestment;
        this.market = marketManager;
        reload();
    }


    //EFFECTS: "reloads" list so all changes are shown
    public void reload() {
        this.removeAll();
        this.revalidate();
        this.repaint();
        listModel = new DefaultListModel<>();
        for (Investment i: market.myMarket.getInvestmentList()) {
            listModel.addElement(i.getInvestmentname());
        }
        initializeJList();
    }


    //EFFECTS: Initialises List with ListModel, controls to navigate through the list
    private void initializeJList() {
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(500, 500));
        add(listScroller);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selected = list.getSelectedIndex();
                displayPanelInvestment.removeAll();
                displayPanelInvestment.revalidate();
                displayPanelInvestment.repaint();
                displayPanelInvestment.displayInfo(market.myMarket.getInvestmentList().get(selected));
            }
        });
    }

    //EFFECTS: Returns JList object so other classes can operate on it.
    public JList getJList() {
        return this.list;
    }

}
