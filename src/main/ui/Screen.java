package ui;

import model.Market;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Screen extends JFrame {
    private final MarketManager marketManager;
    private final InvestmentsUI investmentsUI;
    private final PortfoliosUI portfoliosUI;
    private final JTabbedPane pane;
    private final MainMenu mainMenu;

    // Initialises the program with title and automatically reloads data from Backup file.
    public Screen(String title) {
        super(title);
        marketManager = new MarketManager();
//        try {
//            marketManager.load();           //Reload method to get data
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Back-up not Found!");
//            marketManager.myMarket = new Market();
//        }

        // set layout manager
        setLayout(new GridLayout());

        // Create Swing Component
        pane = new JTabbedPane();
        pane.setTabPlacement(SwingConstants.LEFT);

        mainMenu = new MainMenu(pane, marketManager);
        investmentsUI = new InvestmentsUI(marketManager);
        portfoliosUI = new PortfoliosUI(marketManager);

        pane.addTab("Main Menu", mainMenu);
        pane.addTab("Investments", investmentsUI);
        pane.addTab("Portfolios", portfoliosUI);


        Container cont = getContentPane();
        cont.add(pane);
    }

    //Returns investments pane
    public InvestmentsUI getInvestmentsUI() {
        return this.investmentsUI;
    }

    //Returns investments pane
    public PortfoliosUI getPortfoliosUI() {
        return this.portfoliosUI;
    }

}
