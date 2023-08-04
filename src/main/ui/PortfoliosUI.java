package ui;

import model.Investment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PortfoliosUI extends JPanel {
    private final PortfolioList pl;
    private final DisplayPanelPortfolio dp;
    private final MarketManager market;
    protected JButton newPor = new JButton("New Portfolio");
    protected JButton delPor = new JButton("Delete Portfolio");
    protected JButton addInv = new JButton("Add Investment");
    protected JButton delInv = new JButton("Delete Investment");
    protected JButton refresh = new JButton("  Refresh  ");

    //Instantiates Panel and creates components
    public PortfoliosUI(MarketManager market) {
        Dimension size = getPreferredSize();
        size.width = 220;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder("Portfolios"));
        this.market = market;
        dp = new DisplayPanelPortfolio();
        pl = new PortfolioList(market, dp);
        Dimension minSize = new Dimension(500, 500);
        dp.setMinimumSize(minSize);
        pl.setMinimumSize(minSize);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pl, dp);
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerLocation(500);
        loadButtons();
        createComponents(splitPane, newPor, delPor, addInv, delInv, refresh);
        newPortfolioBtn(newPor);
        delPortfolioBtn(delPor);
        addInvestment(addInv);
        delInvButton(delInv);
        addReload(refresh);
    }

    private void loadButtons() {
        newPor.setMinimumSize(new Dimension(100, 20));
        delPor.setMinimumSize(new Dimension(100, 20));
        addInv.setMinimumSize(new Dimension(100, 20));
        delInv.setMinimumSize(new Dimension(100, 20));
        refresh.setMinimumSize(new Dimension(100, 20));

    }

    // Creates the components
    private void createComponents(JSplitPane pane, JButton bt1, JButton bt2, JButton bt3, JButton bt4, JButton reload) {
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

        gc.gridx = 3;
        gc.gridy = 4;
        add(bt3, gc);

        gc.gridx = 3;
        gc.gridy = 5;
        add(bt4, gc);

    }

    private void addReload(JButton reload) {
        reload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    market.save();
                    pl.reload();
                } catch (IOException x) {
                    JOptionPane.showMessageDialog(null,
                            "Error saving Data", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Adds Button 1 to panel
    private void newPortfolioBtn(JButton bt1) {
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = JOptionPane.showInputDialog(null,
                        "Portfolio Name?", "Add Portfolio", JOptionPane.QUESTION_MESSAGE);
                Integer response2 = Integer.valueOf(JOptionPane.showInputDialog(null,
                        "Initial Capital?", "Add Portfolio", JOptionPane.QUESTION_MESSAGE));

                String response4 = JOptionPane.showInputDialog(null,
                        "What is it's economic sector?", "Add Portfolio", JOptionPane.QUESTION_MESSAGE);
                market.myMarket.newPortfolio(response, response4, response2);

                pl.reload();
                try {
                    market.save();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error saving Data", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Adds Button 2 to panel
    private void delPortfolioBtn(JButton bt2) {
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = pl.getJList().getSelectedIndex();
                market.getMarket().getPortfolioList().remove(num);
                pl.reload();
                try {
                    market.save();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving Data", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Adds Button 3 to panel
    private void addInvestment(JButton bt3) {
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = pl.getJList().getSelectedIndex();

                String response = JOptionPane.showInputDialog(null,
                        "Investment Name?", "Add Investment", JOptionPane.QUESTION_MESSAGE);

                Investment inv = market.myMarket.lookupInvestmentByName(response);

                Integer response2 = Integer.valueOf(JOptionPane.showInputDialog(null,
                        "Quantity", "Add Investment", JOptionPane.QUESTION_MESSAGE));

                market.myMarket.getPortfolioList().get(num).addInvestments(inv, response2);
                pl.reload();
                try {
                    market.save();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving Data", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Adds Button 4 to panel
    private void delInvButton(JButton bt4) {
        bt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = pl.getJList().getSelectedIndex();

                String response = JOptionPane.showInputDialog(null,
                        "Investment Name?", "Delete Investment", JOptionPane.QUESTION_MESSAGE);

                Investment inv = market.myMarket.lookupInvestmentByName(response);

                Integer response2 = Integer.valueOf(JOptionPane.showInputDialog(null,
                        "Quantity", "Delete Investment", JOptionPane.QUESTION_MESSAGE));

                market.myMarket.getPortfolioList().get(num).removeInvestment(inv, response2);
                pl.reload();
                try {
                    market.save();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving Data", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }



}
