//package ui;
//
//import model.Market;
//import persistence.Reader;
//import persistence.Writer;
//import ui.tabs.HomeTab;
//import ui.tabs.InvestmentTab;
//import ui.tabs.PortfolioTab;
//
//import javax.swing.*;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//public class BuildPortfolioUI extends JFrame {
//    public static final int HOME_TAB_INDEX = 0;
//    public static final int INVEST_TAB_INDEX = 1;
//    public static final int PORT_TAB_INDEX = 2;
//    public static final int SETTINGS_TAB_INDEX = 3;
//
//    private JButton removeButton;
//    private JButton addButton;
//    private JButton loadButton;
//    private JButton saveButton;
//
//
//
//    public static final int WIDTH = 600;
//    public static final int HEIGHT = 400;
//    protected JTabbedPane sidebar;
//    protected Market myMarket;
//
//    protected static final String JSON_STORAGE = "./data/market.json";
//    protected Writer jsonWriter;
//    protected Reader jsonReader;
//
//    public static void main(String[] args) {
//        new BuildPortfolioUI();
//    }
//
//    //MODIFIES: this
//    //EFFECTS: creates Market, loads portfolios and investments, displays sidebar and tabs
//    private BuildPortfolioUI() {
//        super("BuildPortfolio Console");
//        setSize(WIDTH, HEIGHT);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        myMarket = new Market();
//        initInvestments();
//        initPortfolios();
//        jsonWriter = new Writer(JSON_STORAGE);
//        jsonReader = new Reader(JSON_STORAGE);
//        sidebar = new JTabbedPane();
//        sidebar.setTabPlacement(JTabbedPane.LEFT);
//
//        loadTabs();
//        add(sidebar);
//
//        setVisible(true);
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: initializes investments
//    private void initInvestments() {
//        myMarket.addInitInvestments();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes portfolios
//    private void initPortfolios() {
//        myMarket.addInitPortfolios();
//    }
//
//    //EFFECTS: returns Market object controlled by this UI
//    public Market getMarket() {
//        return myMarket;
//    }
//
//    //MODIFIES: this
//    //EFFECTS: adds home tab, settings tab and report tab to this UI
//    private void loadTabs() {
//        JPanel homeTab = new HomeTab(this);
//        JPanel investmentTab = new InvestmentTab(this);
//        JPanel portfolioTab = new PortfolioTab(this);
//
//        sidebar.add(homeTab, HOME_TAB_INDEX);
//        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");
//        sidebar.add(investmentTab, INVEST_TAB_INDEX);
//        sidebar.setTitleAt(INVEST_TAB_INDEX, "View Investments");
//        sidebar.add(portfolioTab, PORT_TAB_INDEX);
//        sidebar.setTitleAt(PORT_TAB_INDEX, "View Portfolios");
//    }
//
//    //EFFECTS: returns sidebar of this UI
//    public JTabbedPane getTabbedPane() {
//        return sidebar;
//    }
//
//
//
//
//
//}
