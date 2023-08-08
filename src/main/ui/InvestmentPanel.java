package ui;

import javax.swing.*;
import model.Investment;
import javax.swing.border.Border;
import java.awt.*;
import java.text.DecimalFormat;

// Used to display investments, controls that operate on investments, creates buttons to achieve this
public abstract class InvestmentPanel extends JPanel {
    protected GridBagConstraints gc;
    protected JLabel nameLabel;
    protected JLabel priceLabel;
    protected JLabel returnLabel;
    protected JLabel sectorLabel;
    protected JTextField nameField;
    protected JTextField priceField;
    protected JTextField returnField;
    protected JTextField sectorField;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    //EFFECTS: Initialises the Panel using name and size integers
    public InvestmentPanel(String str, int num, int num2) {
        Dimension size = getPreferredSize();
        size.width = num;
        size.height = num2;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder(str));
        setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
    }

    //EFFECTS: Initialises the Labels
    protected void intializeLabels() {
        nameLabel = new JLabel("Name: ");
        priceLabel = new JLabel("Price: ");
        returnLabel = new JLabel("Return %: ");
        sectorLabel = new JLabel("Sector: ");

    }

    //EFFECTS: Initialises the Fields that are displayed
    protected void initalizeFields(Investment investment) {
        nameField = new JTextField(investment.getInvestmentname(), 20);
        priceField = new JTextField(Float.toString(investment.getPrice()), 20);
        returnField = new JTextField(df.format(investment.getReturnPercentage()), 20);
        sectorField = new JTextField(investment.getSector(), 20);
    }

    //EFFECTS: Makes so fields value cannot be changed
    protected void setFieldQualities() {
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        nameField.setEditable(false);
        priceField.setEditable(false);
        returnField.setEditable(false);
        sectorField.setEditable(false);
    }

    //EFFECTS: Adds Labels onto the Panel
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

    //EFFECTS: Adds Fields Onto the Panel
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
