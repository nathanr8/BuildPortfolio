package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenu extends JPanel {
    protected JButton save;
    protected JButton load;
    protected JButton openInvestments;
    protected JButton openPortfolios;
    private final GridBagConstraints gc;

    //Initialises MainMenu Pane
    public MainMenu(JTabbedPane pane, MarketManager marketManager) {
        //Set Default Settings for Pane
        Dimension size = getPreferredSize();
        size.width = 200;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder(" "));
        setLayout(new GridBagLayout());

        //Instantiate Components
        save = new JButton("Save Market");
        load = new JButton("Load Market");
        openInvestments = new JButton("View Investments");
        openPortfolios = new JButton("View Portfolios");
        gc = new GridBagConstraints();

        //Add Components to Pane
        addopenInvestments(pane);
        addopenPortfolios(pane);
        addSave(marketManager);
        addLoad(marketManager);
    }

    //Adds the save button to the pane
    private void addSave(MarketManager marketManager) {
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.CENTER;
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

    // Adds the load
    private void addLoad(MarketManager marketManager) {
        gc.fill = 0;
        gc.gridx = 3;
        add(load, gc);

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    marketManager.load();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error loading Data", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    //Adds the Open investment button to the pane with the Listener aswell
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

    //Adds the Open investment button to the pane with the Listener aswell
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
