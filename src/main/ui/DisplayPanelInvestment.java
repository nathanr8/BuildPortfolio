package ui;

import model.Investment;

public class DisplayPanelInvestment extends InvestmentPanel {

    private Investment investment;

    public DisplayPanelInvestment() {
        super("Investment Information", 400, 400);
    }

    //Displays the info of the given investment on the DisplayPanel panel
    public void displayInfo(Investment investment) {
        this.investment = investment;

        intializeLabels();
        initalizeFields(investment);
        setFieldQualities();

        gc.weightx = 0.5;
        gc.weighty = 0.5;
        addLabels();
        addFields();
    }

}
