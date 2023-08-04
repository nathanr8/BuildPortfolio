package ui;

import javax.swing.*;
import model.Investment;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class InvestmentPanel extends JPanel {
    protected GridBagConstraints gc;
    //creates Labels, and Fields
    protected JLabel nameLabel;
    protected JLabel priceLabel;
    protected JLabel returnLabel;
    protected JLabel sectorLabel;

    protected JTextField nameField;
    protected JTextField priceField;
    protected JTextField returnField;
    protected JTextField sectorField;

    //Initialises the Panel
    public InvestmentPanel(String str, int num, int num2) {
        Dimension size = getPreferredSize();
        size.width = num;
        size.height = num2;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder(str));
        setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
    }

    //Initialises the Labels
    protected void intializeLabels() {
        nameLabel = new JLabel("Name: ");
        priceLabel = new JLabel("Price: ");
        returnLabel = new JLabel("Return %: ");
        sectorLabel = new JLabel("Sector: ");

    }

    //Initialises the Fields
    protected void initalizeFields(Investment investment) {
        nameField = new JTextField(investment.getInvestmentname(), 20);
        priceField = new JTextField(Float.toString(investment.getPrice()), 20);
        returnField = new JTextField(Double.toString(investment.getReturnPercentage()), 20);
        sectorField = new JTextField(investment.getSector(), 20);
    }

    //Adjusts the Fields for their specific use
    protected void setFieldQualities() {
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        nameField.setEditable(false);
        priceField.setEditable(false);
        returnField.setEditable(false);
        sectorField.setEditable(false);
    }

    //Adds Labels onto the Panel
    protected void addLabels() {
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 1;
        add(nameLabel, gc);
        gc.gridy = 2;
        add(priceLabel, gc);
        gc.gridy = 3;
        add(returnLabel, gc);
        gc.gridy = 4;
        add(sectorLabel, gc);
    }

    //Adds Fields Onto the Panel
    protected void addFields() {
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 1;
        add(nameField, gc);
        gc.gridy = 2;
        add(priceField, gc);
        gc.gridy = 3;
        add(returnField, gc);
        gc.gridy = 4;
        add(sectorField, gc);
    }

}
