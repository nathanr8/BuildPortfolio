package ui;

import model.Investment;
import model.Portfolio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// CITATION: Code inspired by the following sources:
// https://www.youtube.com/watch?v=Kmgo00avvEw&list=LL&index=5&t=16276s
// https://docs.oracle.com/javase/8/docs/api/javax/swing/JButton.html
// https://www.youtube.com/watch?v=QvFQFmzRqoo
// https://www.tabnine.com/code/java/methods/javax.swing.JPanel/getPreferredSize
// https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/BorderFactory.html
// https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/JSplitPane.html
// https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
// https://stackoverflow.com/questions/7435622/what-is-the-difference-between-addactionlistenerthis-and-addactionlistenernew
// https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html

// Creates the main Portfolio Panel, displays PortfolioList and DisplayPanelPortfolio beside one another
public class PortfoliosUI extends JPanel {
    private final PortfolioList pl;
    private final DisplayPanelPortfolio dp;
    private final MarketManager market;
    private DrawGraph drawer;
    protected JButton newPor = new JButton("New Portfolio");
    protected JButton delPor = new JButton("Delete Portfolio");
    protected JButton addInv = new JButton("Add Investment");
    protected JButton delInv = new JButton("Delete Investment");
    protected JButton refresh = new JButton("  Refresh  ");
    protected JButton chart = new JButton("View Chart");


    //CONSTRUCTOR
    //EFFECTS: Instantiates Panel and creates components in a split pane, adds buttons
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
        createComponents(splitPane, newPor, delPor, addInv, delInv, refresh, chart);
        newPortfolioBtn(newPor);
        delPortfolioBtn(delPor);
        addInvestment(addInv);
        delInvButton(delInv);
        addReload(refresh);
        addChart(chart);
    }

    // EFFECTS: Creates size of the buttons to be displayed
    private void loadButtons() {
        newPor.setMinimumSize(new Dimension(150, 20));
        delPor.setMinimumSize(new Dimension(150, 20));
        addInv.setMinimumSize(new Dimension(150, 20));
        delInv.setMinimumSize(new Dimension(150, 20));
        refresh.setMinimumSize(new Dimension(150, 20));
        chart.setMinimumSize(new Dimension(150, 20));

    }

    // EFFECTS: Creates the components, buttons, sets the gridlayout
    private void createComponents(JSplitPane pane, JButton bt1, JButton bt2, JButton bt3, JButton bt4,
                                  JButton bt5, JButton reload) {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.weighty = 0;
        gc.weightx = 0.1;
        gc.gridwidth = 5;
        gc.gridx = 3;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        add(pane, gc);
        gc.insets = new Insets(10, 50, 0, 0);
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.WEST;
        add(reload, gc);
        gc.gridy = 1;
        add(bt1, gc);
        gc.gridy = 2;
        add(bt2, gc);
        gc.gridy = 3;
        add(bt3, gc);
        gc.gridy = 4;
        add(bt4, gc);
        gc.gridy = 6;
        add(bt5, gc);
    }

    //EFFECTS: Reloads market display so portfolio list and UI are in sync
    private void addReload(JButton reload) {
        reload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pl.reload();
            }
        });
    }

    // EFFECTS: Adds new portfolio button to panel with action listener,
    //          upon activation prompts user to fill out portfolio info,
    //          adds portfolio to market and reloads portfolio list
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
            }
        });
    }

    // EFFECTS: Adds delete portfolio button to panel with action listener
    //          upon activation, deletes selected portfolio from market and reloads portfolio list
    private void delPortfolioBtn(JButton bt2) {
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = pl.getJList().getSelectedIndex();
                market.getMarket().getPortfolioList().remove(num);
                pl.reload();
            }
        });
    }

    // EFFECTS: Adds add investment button to panel with action listener,
    //          upon activation prompts user to fill out investment info, if portfolio has enough capital
    //          adds investment to portfolio and reloads portfolio list
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
            }
        });
    }

    // EFFECTS: Adds delete investment button to panel with action listener,
    //          upon activation prompts user to fill out investment info,
    //          deletes investment from selected portfolio and returns capital
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
            }
        });
    }

    // EFFECTS: Adds the chart button to panel with action listener, upon activation it
    //          creates the corresponding portfolio capital growth graph to user
    private void addChart(JButton bt5) {
        bt5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = pl.getJList().getSelectedIndex();
                Portfolio p = market.getMarket().getPortfolioList().get(num);
                ArrayList<Double> i = new ArrayList<>();
                double j = (double) p.getPortfolioCapital();
                i.add(j);
                i.add(j);
                Double returnDollar = p.calculateReturnAmountDollar();
                Double returnPercent = returnDollar / i.get(1);

                drawer = new DrawGraph(i, returnPercent);
                drawer.createAndShowGui();
                pl.reload();
            }
        });

    }
}
