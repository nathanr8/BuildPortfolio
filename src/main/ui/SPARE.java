//////package ui;
//////
//////import model.Portfolio;
//////
//////import javax.swing.*;
//////import javax.swing.border.Border;
//////import java.awt.*;
//////
//////public class SPARE {
//////
//////
//////}
//////
//////package ui;
//////
//////        import model.Investment;
//////        import model.Portfolio;
//////
//////        import javax.swing.*;
//////        import javax.swing.border.Border;
//////        import java.awt.*;
//////
//////public abstract class PortfolioPanel extends JPanel {
//////    protected GridBagConstraints gc;
//////    //creates Labels, and Fields
//////    protected JLabel nameLabel;
//////    protected JLabel sectorLabel;
//////    protected JLabel initCapLabel;
//////    protected JLabel currCapLabel;
//////    protected JLabel investmentsLabel;
//////    protected JLabel returnLabel;
//////
//////    protected JTextField nameField;
//////    protected JTextField sectorField;
//////    protected JTextField initCapField;
//////    protected JTextField currCapField;
//////    protected JTextField investmentsField;
//////    protected JTextField returnField;
//////
//////    //Initialises the Panel
//////    public PortfolioPanel(String str, int num, int num2) {
//////        Dimension size = getPreferredSize();
//////        size.width = num;
//////        size.height = num2;
//////        setPreferredSize(size);
//////        setBorder(BorderFactory.createTitledBorder(str));
//////        setLayout(new GridBagLayout());
//////        gc = new GridBagConstraints();
//////    }
//////
//////    //Initialises the Labels
//////    protected void intializeLabels() {
//////        nameLabel = new JLabel("Name: ");
//////        initCapLabel = new JLabel("Initial Capital: ");
//////        currCapLabel = new JLabel("Current Capital: ");
//////        sectorLabel = new JLabel("Sector: ");
//////        investmentsLabel = new JLabel("Investments: ");
//////        returnLabel = new JLabel("Return %: ");
//////
//////    }
//////
//////    //Initialises the Fields
//////    protected void initalizeFields(Portfolio portfolio) {
//////        nameField = new JTextField(portfolio.getPortfolioName(), 20);
//////        initCapField = new JTextField(Integer.toString(portfolio.getInitialCapital()), 20);
//////        currCapField = new JTextField(Integer.toString(portfolio.getAvailableCapital()), 20);
//////        sectorField = new JTextField(portfolio.getPreferredSector(), 20);
//////        // investmentsField = new JTextField(portfolio.getPreferredSector(), 20);
//////        returnField = new JTextField(portfolio.calculateReturnAmountPercent(), 20);
//////
//////    }
//////
//////    //Adjusts the Fields for their specific use
//////    protected void setFieldQualities() {
//////        Border border = BorderFactory.createLineBorder(Color.BLACK);
//////        nameField.setEditable(false);
//////        initCapField.setEditable(false);
//////        currCapField.setEditable(false);
//////        returnField.setEditable(false);
//////        sectorField.setEditable(false);
//////        investmentsField.setEditable(false);
//////    }
//////
//////    //Adds Labels onto the Panel
//////    protected void addLabels() {
//////        gc.anchor = GridBagConstraints.LINE_END;
//////        gc.gridx = 0;
//////        gc.gridy = 1;
//////        add(nameLabel, gc);
//////        gc.gridy = 2;
//////        add(initCapLabel, gc);
//////        gc.gridy = 3;
//////        add(currCapLabel, gc);
//////        gc.gridy = 4;
//////        add(returnLabel, gc);
//////        gc.gridy = 5;
//////        add(investmentsLabel, gc);
//////        gc.gridy = 6;
//////        add(sectorLabel, gc);
//////    }
//////
//////    //Adds Fields Onto the Panel
//////    protected void addFields() {
//////        gc.anchor = GridBagConstraints.LINE_START;
//////        gc.gridx = 1;
//////        gc.gridy = 1;
//////        add(nameField, gc);
//////        gc.gridy = 2;
//////        add(initCapField, gc);
//////        gc.gridy = 3;
//////        add(currCapField, gc);
//////        gc.gridy = 4;
//////        add(returnField, gc);
//////        gc.gridy = 5;
//////        add(investmentsField, gc);
//////        gc.gridy = 6;
//////        add(sectorField, gc);
//////    }
//////
//////
//////
//////
//////
//////}
//////
////
////
////
////
////
////        Dimension size = getPreferredSize();
////                size.width = num;
////                size.height = num2;
////                setPreferredSize(size);
////                setBorder(BorderFactory.createTitledBorder(str));
////                setLayout(new GridBagLayout());
////                gc = new GridBagConstraints();
////                }
////
//////Initialises the Labels
////protected void intializeLabels() {
////        nameLabel = new JLabel("Name: ");
////        initCapLabel = new JLabel("Initial Capital: ");
////        currCapLabel = new JLabel("Current Capital: ");
////        sectorLabel = new JLabel("Sector: ");
////        investmentsLabel = new JLabel("Investments: ");
////        returnLabel = new JLabel("Return %: ");
////
////        }
////
//////Initialises the Fields
////protected void initalizeFields(Portfolio portfolio) {
////        nameField = new JTextField(portfolio.getPortfolioName(), 20);
////        initCapField = new JTextField(Integer.toString(portfolio.getInitialCapital()), 20);
////        currCapField = new JTextField(Integer.toString(portfolio.getAvailableCapital()), 20);
////        sectorField = new JTextField(portfolio.getPreferredSector(), 20);
////        // investmentsField = new JTextField(portfolio.getPreferredSector(), 20);
////        returnField = new JTextField(portfolio.calculateReturnAmountPercent(), 20);
////
////        }
////
//////Adjusts the Fields for their specific use
////protected void setFieldQualities() {
////        Border border = BorderFactory.createLineBorder(Color.BLACK);
////        nameField.setEditable(false);
////        initCapField.setEditable(false);
////        currCapField.setEditable(false);
////        returnField.setEditable(false);
////        sectorField.setEditable(false);
////        investmentsField.setEditable(false);
////        }
////
//////Adds Labels onto the Panel
////protected void addLabels() {
////        gc.anchor = GridBagConstraints.LINE_END;
////        gc.gridx = 0;
////        gc.gridy = 1;
////        add(nameLabel, gc);
////        gc.gridy = 2;
////        add(initCapLabel, gc);
////        gc.gridy = 3;
////        add(currCapLabel, gc);
////        gc.gridy = 4;
////        add(returnLabel, gc);
////        gc.gridy = 5;
////        add(investmentsLabel, gc);
////        gc.gridy = 6;
////        add(sectorLabel, gc);
////        }
////
//////Adds Fields Onto the Panel
////protected void addFields() {
////        gc.anchor = GridBagConstraints.LINE_START;
////        gc.gridx = 1;
////        gc.gridy = 1;
////        add(nameField, gc);
////        gc.gridy = 2;
////        add(initCapField, gc);
////        gc.gridy = 3;
////        add(currCapField, gc);
////        gc.gridy = 4;
////        add(returnField, gc);
////        gc.gridy = 5;
////        add(investmentsField, gc);
////        gc.gridy = 6;
////        add(sectorField, gc);
////        }
//
//
//
//package ui;
//
//import model.Investment;
//import model.Portfolio;
//import javax.swing.*;
//import javax.swing.border.Border;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import java.awt.*;
//
//public abstract class PortfolioPanel extends JPanel {
//    protected GridBagConstraints gc;
//    private DefaultListModel<String> listModel;
//    private JList<Investment> list;
//    private DisplayPanelPortfolio displayPanelPortfolio;
//    private MarketManager market;
//    protected JTextField nameField;
//    //creates Labels, and Fields
//
//    //Initialises the Panel
//    public PortfolioPanel(MarketManager market, String str, int num, int num2) {
//        Dimension size = getPreferredSize();
//        size.width = num;
//        size.height = num2;
//        setPreferredSize(size);
//        setBorder(BorderFactory.createTitledBorder(str));
//        setLayout(new GridBagLayout());
//
//        this.market = market;
//        listModel = new DefaultListModel<>();
//        initializeJList();
//        gc = new GridBagConstraints();
//        displayPanelPortfolio = new DisplayPanelPortfolio(market);
//
//    }
//
//    public void displayInvestments(Portfolio portfolio) {
//        listModel.clear();
//        for (Investment i : portfolio.getInvestments()) {
//            listModel.addElement(i.getInvestmentname());
//        }
//    }
//
//
//    //Initialises List with ListModel and sets basic controls to navigate through the list
//    private void initializeJList() {
//        list = new JList(listModel);
//        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//        list.setLayoutOrientation(JList.VERTICAL);
//        list.setVisibleRowCount(-1);
//
//        JScrollPane listScroller = new JScrollPane(list);
//        listScroller.setPreferredSize(new Dimension(300, 300));
//        add(listScroller, BorderLayout.CENTER);
//
//        //Listener detects selection of investment and displays the appropriate Data
//        list.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                int selected = list.getSelectedIndex();
//                displayPanelPortfolio.removeAll();
//                displayPanelPortfolio.revalidate();
//                displayPanelPortfolio.repaint();
//                displayPanelPortfolio.displayInfo(market.myMarket.getPortfolioList().get(selected));
//            }
//        });
//    }
//
//
//
//
//
//}