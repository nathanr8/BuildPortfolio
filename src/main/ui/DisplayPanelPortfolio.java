package ui;

import model.Portfolio;

// Displays information for chosen portfolio in Portfolio tab
public class DisplayPanelPortfolio extends PortfolioPanel {
    private Portfolio portfolio;

    //CONSTRUCTOR
    //EFFECTS: constructs a PortfolioPanel with name and size
    public DisplayPanelPortfolio() {
        super("Portfolio Information", 600, 600);
    }

    //EFFECTS: Displays the info of the given portfolio on the DisplayPanel panel
    public void displayInfo(Portfolio portfolio) {
        this.portfolio = portfolio;

        intializeLabels();
        initalizeFields(portfolio);
        setFieldQualities();

        gc.weightx = 0.5;
        gc.weighty = 0.5;
        addLabels();
        addFields();
    }
}
