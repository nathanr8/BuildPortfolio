package ui;

import model.Investment;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class InvestmentList extends JPanel {
    private DefaultListModel<String> listModel;
    private JList<Investment> list;
    private final DisplayPanelInvestment displayPanelInvestment;
    private final MarketManager market;

    //Instantiates InvestmentList Panel
    public InvestmentList(MarketManager marketManager, DisplayPanelInvestment displayPanelInvestment) {
        this.displayPanelInvestment = displayPanelInvestment;
        this.market = marketManager;
        reload();
    }


    //"Refreshes" List so all changes are shown so there is no discrepancy between that is in the list and
    // what is shown.
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


    //Initialises List with ListModel and sets basic controls to navigate through the list
    private void initializeJList() {
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(400, 400));
        add(listScroller);

        //Listener detects selection of investment and displays the appropriate Data
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

    //Returns JLIST object so that other classes can operate on it.
    public JList getJList() {
        return this.list;
    }



}
