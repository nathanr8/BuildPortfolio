package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// CITATION: Code inspired by the following sources:
// https://www.youtube.com/watch?v=Kmgo00avvEw&list=LL&index=5&t=16276s
// https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/JOptionPane.html
// https://www.youtube.com/watch?v=sDJnUiBwIcM
// https://alvinalexander.com/java/joptionpane-showmessagedialog-examples-1/

// Home screen of the GUI, displays buttons and pane for options of different UIs
public class MainMenu extends JPanel {
    protected JButton save;
    protected JButton load;
    protected JButton openInvestments;
    protected JButton openPortfolios;
    private final GridBagConstraints gc;

    //EFFECTS: Initialises MainMenu Pane, buttons
    public MainMenu(JTabbedPane pane, MarketManager marketManager) {
        Dimension size = getPreferredSize();
        size.width = 200;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder(" "));
        setLayout(new GridBagLayout());
        save = new JButton("Save Market");
        load = new JButton("Load Market");
        openInvestments = new JButton("View Investments");
        openPortfolios = new JButton("View Portfolios");
        gc = new GridBagConstraints();

        addopenInvestments(pane);
        addopenPortfolios(pane);
        addSave(marketManager);
        addLoad(marketManager);
    }

    //EFFECTS: Adds the save button to the pane, creates action listener which
    //         tells user when data has been saved, throws IO exception if cannot
    //         save data
    private void addSave(MarketManager marketManager) {
        gc.fill = 0;
        gc.gridx = 2;
        add(save, gc);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    marketManager.save();
                    JOptionPane.showMessageDialog(null,
                            "Data Saved", "Saved!", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error saving Data", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    //EFFECTS: Adds the load button to the pane, creates action listener which
    //         tells user when data has been loaded, throws IO exception if cannot
    //         load data
    private void addLoad(MarketManager marketManager) {
        gc.fill = 0;
        gc.gridx = 3;
        add(load, gc);

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    marketManager.load();
                    JOptionPane.showMessageDialog(null,
                            "Data Loaded", "Loaded!", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error loading Data", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    //EFFECTS: Adds the open investments button to the pane, creates action listener which
    //         opens the InvestmentsUI when activated
    private void addopenInvestments(JTabbedPane pane) {
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(25, 5, 300, 5);
        gc.gridwidth = 1;
        gc.gridy = 1;
        gc.gridx = 0;
        add(openInvestments, gc);

        openInvestments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pane.setSelectedIndex(1);
            }
        });
    }

    //EFFECTS: Adds the open portfolios button to the pane, creates action listener which
    //         opens the PortfolioUI when activated
    private void addopenPortfolios(JTabbedPane pane) {
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(25, 5, 300, 5);
        gc.gridwidth = 1;
        gc.gridy = 1;
        gc.gridx = 1;
        add(openPortfolios, gc);

        openPortfolios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pane.setSelectedIndex(2);
            }
        });
    }





}
