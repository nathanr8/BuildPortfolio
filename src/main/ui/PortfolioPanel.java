package ui;

import model.Investment;
import model.Portfolio;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class PortfolioPanel extends JPanel {
    protected GridBagConstraints gc;
    //creates Labels, and Fields
    protected JLabel nameLabel;
    protected JLabel sectorLabel;
    protected JLabel initCapLabel;
    protected JLabel currCapLabel;
    protected JLabel investmentsLabel;
    protected JLabel returnLabel;

    protected JTextField nameField;
    protected JTextField sectorField;
    protected JTextField initCapField;
    protected JTextField currCapField;
    protected JTable investmentsTable;
    protected JScrollPane investmentsScrollPane;
    protected JTextField returnField;

    //Initialises the Panel
    public PortfolioPanel(String str, int num, int num2) {
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
        initCapLabel = new JLabel("Initial Capital: ");
        currCapLabel = new JLabel("Current Capital: ");
        sectorLabel = new JLabel("Sector: ");
        investmentsLabel = new JLabel("Investments: ");
        returnLabel = new JLabel("Return %: ");

    }

    //Initialises the Fields
    protected void initalizeFields(Portfolio portfolio) {
        nameField = new JTextField(portfolio.getPortfolioName(), 15);
        initCapField = new JTextField(Integer.toString(portfolio.getInitialCapital()), 15);
        currCapField = new JTextField(Integer.toString(portfolio.getAvailableCapital()), 15);
        sectorField = new JTextField(portfolio.getPreferredSector(), 15);
        returnField = new JTextField(portfolio.calculateReturnAmountPercent(), 15);

        String[] columnNames = {"Name", "Price", "Sector", "Return %"};
        int numberOfInvestments = portfolio.getInvestments().size();
        String[][] tableData = new String[numberOfInvestments][columnNames.length];

        for (int i = 0; i < numberOfInvestments; i++) {
            Investment investment = portfolio.getInvestments().get(i);
            tableData[i][0] = investment.getInvestmentname();
            tableData[i][1] = Float.toString(investment.getPrice());
            tableData[i][2] = investment.getSector();
            tableData[i][3] = Double.toString(investment.getReturnPercentage());
        }

        investmentsTable = new JTable(tableData, columnNames);
        investmentsScrollPane = new JScrollPane(investmentsTable);
        investmentsTable.setPreferredScrollableViewportSize(new Dimension(200, 200));


    }

    //Adjusts the Fields for their specific use
    protected void setFieldQualities() {
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        nameField.setEditable(false);
        initCapField.setEditable(false);
        currCapField.setEditable(false);
        returnField.setEditable(false);
        sectorField.setEditable(false);
        investmentsTable.setEnabled(false);
    }

    //Adds Labels onto the Panel
    protected void addLabels() {
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 1;
        add(nameLabel, gc);
        gc.gridy = 2;
        add(initCapLabel, gc);
        gc.gridy = 3;
        add(currCapLabel, gc);
        gc.gridy = 4;
        add(returnLabel, gc);
        gc.gridy = 5;
        add(investmentsLabel, gc);
        gc.gridy = 6;
        add(sectorLabel, gc);
    }

    //Adds Fields Onto the Panel
    protected void addFields() {
        gc.anchor = GridBagConstraints.LINE_START;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1.0;

        gc.gridx = 1;
        gc.gridy = 1;
        add(nameField, gc);
        gc.gridy = 2;
        add(initCapField, gc);
        gc.gridy = 3;
        add(currCapField, gc);
        gc.gridy = 4;
        add(returnField, gc);

        gc.gridy = 5;
        gc.weighty = 1.0;
        gc.gridwidth = 2;
        add(investmentsScrollPane, gc);

        gc.gridy = 6;
        gc.weighty = 0;
        gc.gridwidth = 1;
        add(sectorField, gc);
    }

}

