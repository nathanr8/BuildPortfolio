package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InvestmentsUI extends JPanel {
    private final InvestmentList il;
    private final DisplayPanelInvestment dp;
    private final MarketManager market;

    //Instantiates Panel and creates components
    public InvestmentsUI(MarketManager market) {
        Dimension size = getPreferredSize();
        size.width = 220;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder("Investments"));
        this.market = market;

        //creates the two main panels in the record panel
        dp = new DisplayPanelInvestment();
        il = new InvestmentList(market, dp);

        Dimension minSize = new Dimension(400, 400);
        dp.setMinimumSize(minSize);
        il.setMinimumSize(minSize);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, il, dp);
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerLocation(400);

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

    // Creates the components
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


    private void addReload(JButton reload) {
        reload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    market.save();
                    il.reload();
                } catch (IOException x) {
                    JOptionPane.showMessageDialog(null,
                            "Error saving Data", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Adds Button 1 to panel
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
                try {
                    market.save();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving Data", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Adds Button 2 to panel
    private void delInvButton(JButton bt1) {
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = il.getJList().getSelectedIndex();
                market.getMarket().getInvestmentList().remove(num);
                il.reload();
                try {
                    market.save();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving Data", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }




    //Returns InvestmentList panel for other panels to use.
    public InvestmentList getInvestmentList() {
        return il;
    }

}
