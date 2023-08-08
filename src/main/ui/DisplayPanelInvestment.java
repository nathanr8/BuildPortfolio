package ui;

import model.Investment;

// Displays information for chosen investment in Investment tab
public class DisplayPanelInvestment extends InvestmentPanel {
    private Investment investment;

    //CONSTRUCTOR
    //EFFECTS: constructs an InvestmentPanel with name and size
    public DisplayPanelInvestment() {
        super("Investment Information", 500, 500);
    }

    //EFFECTS: Initialized fields and labels to display the info of
    //         the given investment on the DisplayPanel panel
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
