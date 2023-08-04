package ui;

import model.Market;
import model.Portfolio;

public class DisplayPanelPortfolio extends PortfolioPanel {
    private Portfolio portfolio;

    public DisplayPanelPortfolio() {
        super("Portfolio Information", 600, 600);
    }

    //Displays the info of the given portfolio on the DisplayPanel panel
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
