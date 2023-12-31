package ui;

import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;

// CITATION: Code inspired by the following sources:
// https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
// https://www.youtube.com/watch?v=qP2tXh4Pd_8
// https://www.youtube.com/watch?v=Kmgo00avvEw&list=LL&index=5&t=16276s

// Creates the main screen object for which main calls to start GUI
public class Screen extends JFrame {
    private final MarketManager marketManager;
    private final InvestmentsUI investmentsUI;
    private final PortfoliosUI portfoliosUI;
    private final JTabbedPane pane;
    private final MainMenu mainMenu;

    //EFFECTS: Main GUI, initialises the program with title, main menu, investment and portfolio UIs
    public Screen(String title) {
        super(title);
        marketManager = new MarketManager();
        setLayout(new GridLayout());
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                for (Iterator<model.Event> it = marketManager.getMarket().getEventLog().iterator(); it.hasNext(); ) {
                    Event event = it.next();
                    System.out.println(event.getDescription() + " at " + event.getDate());
                }
            }
        });


    }

}
