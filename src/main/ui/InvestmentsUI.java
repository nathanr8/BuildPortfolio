package ui;

import model.Investment;
import model.Portfolio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Creates the main Investment Panel, displays InvestmentList and DisplayPanelInvestment
public class InvestmentsUI extends JPanel {
    private final InvestmentList il;
    private final DisplayPanelInvestment dp;
    private final MarketManager market;

    //EFFECTS: Instantiates Panel and creates components
    public InvestmentsUI(MarketManager market) {
        Dimension size = getPreferredSize();
        size.width = 220;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder("Investments"));
        this.market = market;

        dp = new DisplayPanelInvestment();
        il = new InvestmentList(market, dp);

        Dimension minSize = new Dimension(800, 800);
        dp.setMinimumSize(minSize);
        il.setMinimumSize(minSize);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, il, dp);
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerLocation(500);

        JButton newInv = new JButton("New Investment");
        JButton delInv = new JButton("Delete Investment");
        JButton refresh = new JButton("  Refresh  ");
        newInv.setMinimumSize(new Dimension(100, 20));
        delInv.setMinimumSize(new Dimension(100, 20));
        refresh.setMinimumSize(new Dimension(100, 20));
        createComponents(splitPane, newInv, delInv, refresh);
        newInvestmentBtn(newInv);
        delInvButton(delInv);
        addReload(refresh);
    }

    // EFFECTS: Creates the components, buttons
    private void createComponents(JSplitPane pane, JButton bt1, JButton bt2, JButton reload) {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.weighty = 0;
        gc.weightx = 0.1;

        gc.gridwidth = 5;
        gc.gridx = 3;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        add(pane, gc);

        gc.insets = new Insets(10, 100, 0, 0);
        gc.gridx = 3;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.WEST;
        add(reload, gc);

        gc.gridx = 3;
        gc.gridy = 2;
        add(bt1, gc);

        gc.gridx = 3;
        gc.gridy = 3;
        add(bt2, gc);

    }


    //EFFECTS: Reloads market display so list and UI are in sync
    private void addReload(JButton reload) {
        reload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                il.reload();
            }
        });
    }

    // EFFECTS: Adds new inventory button to panel with action listener
    //          upon activation, prompts user to fill out investment info,
    //          adds investment to market and reloads investment list
    private void newInvestmentBtn(JButton bt1) {
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = JOptionPane.showInputDialog(null,
                        "Investment Name?", "Add Patient", JOptionPane.QUESTION_MESSAGE);
                Integer response2 = Integer.valueOf(JOptionPane.showInputDialog(null,
                        "Initial Price for investment?", "Add Patient", JOptionPane.QUESTION_MESSAGE));

                Float response3 = Float.valueOf(JOptionPane.showInputDialog(null,
                        "The expected return in % for this investment?", "Add Patient", JOptionPane.QUESTION_MESSAGE));

                String response4 = JOptionPane.showInputDialog(null,
                        "What is it's economic sector?", "Add Patient", JOptionPane.QUESTION_MESSAGE);
                market.myMarket.newInvestment(response, response3, response4, response2);

                il.reload();
            }
        });
    }

    // EFFECTS: Adds delete inventory button to panel with action listener
    //          upon activation, deletes selected investment from market and reloads investment list
    private void delInvButton(JButton bt1) {
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = il.getJList().getSelectedIndex();
                Investment inv = market.getMarket().getInvestmentList().remove(num);
                for (Portfolio p: market.getMarket().getPortfolioList()) {
                    if (p.getInvestments().contains(inv)) {
                        while (p.getInvestments().contains(inv)) {
                            p.removeInvestment(inv, 1);
                        }
                    }
                }
                il.reload();
            }
        });
    }

    //EFFECTS: Returns InvestmentList panel for other panels to use.
    public InvestmentList getInvestmentList() {
        return il;
    }

}
