package model;


import java.util.ArrayList;
import java.math.RoundingMode;
import java.text.DecimalFormat;




public class Portfolio {
    private String portfolioName;
    private ArrayList<Investment> investments;
    private String preferredSector;
    private int initialCapital;
    private int availableCapital;
    private int portfolioCapital;
    private static final DecimalFormat df = new DecimalFormat("0.00");


    //EFFECTS: Constructs a new portfolio with name, preferred sector, and starting capital
    public Portfolio(String portfolioname, String preferredSector, int initialCapital) {
        this.portfolioName = portfolioname;
        this.preferredSector = preferredSector;
        this.initialCapital = initialCapital;
        this.availableCapital = initialCapital;
        this.investments = new ArrayList<>();
        this.portfolioCapital = 0;
    }

    // getters
    public String getPortfolioName() {
        return portfolioName;
    }

    public String getPreferredSector() {
        return preferredSector;
    }

    public int getInitialCapital() {
        return initialCapital;
    }

    public ArrayList<Investment> getInvestments() {
        return investments;
    }

    public int getAvailableCapital() {
        return availableCapital;
    }

    public int getPortfolioCapital() {
        int i = this.getInitialCapital();
        int j = this.getAvailableCapital();
        return (i - j);
    }

    // setters

    //MODIFIES: this
    //EFFECTS: sets portfolio's name
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    //MODIFIES: this
    //EFFECTS: sets portfolio's preferred sector
    public void setPreferredSector(String preferredSector) {
        this.preferredSector = preferredSector;
    }

    //REQUIRES: capital >= 1000
    //MODIFIES: this
    //EFFECTS: sets portfolio's starting capital
    public void setInitialCapital(int initialCapital) {
        this.initialCapital = initialCapital;
    }

    //REQUIRES: capital >= 1000
    //MODIFIES: this
    //EFFECTS: sets portfolio's starting available capital
    public void setAvailableCapital(int capital) {
        this.availableCapital = capital;
    }

    //REQUIRES: i.getInvestmentname() in future, price*quantity <= avail. capital, quantity >= 0
    // quantity >= 1
    //MODIFIES: this
    //EFFECTS: adds an investment to portfolio's investment list
    public String addInvestments(Investment i, int quantity) {
        if ((i.getPrice() * quantity) <= this.availableCapital) {
            for (int n = 0; n < quantity; n++) {
                this.investments.add(i);
            }
            this.availableCapital = availableCapital - (i.getPrice() * quantity);
            return "Investment added successfully!";
        } else {
            return "Sorry, this portfolio does not have enough available capital.";
        }
    }

    //EFFECTS: calculates return % for portfolio
    @SuppressWarnings("checkstyle:WhitespaceAround")
    public double calculateReturnAmountDollar(){
        double i = 0.0F;
        for (Investment inv: this.investments) {
            double percent = inv.getReturnPercentage() / 100;
            i += (inv.getPrice() * percent);
        }
        //return df.format(parseFloat(i));
        return (double) Math.round(i);
    }

    @SuppressWarnings("checkstyle:WhitespaceAround")
    public String calculateReturnAmountPercent(){
        double i = this.calculateReturnAmountDollar();
        double j = this.getPortfolioCapital();
        return df.format((i / j) * 100);
    }

    //REQUIRES: quantity >= 1, portfolio has # of selected investments >= quantity,
    // cannot remove more than portfolio has
    //MODIFIES: this
    //EFFECTS: removes investment from portfolio, adds back capital to available capital
    public void removeInvestment(Investment i, int quantity) {
        for (int n = 0; n < quantity; n++) {
            this.investments.remove(i);
        }
        availableCapital = availableCapital + (i.getPrice() * quantity);
    }

    //EFFECTS: prints out a list of all investments in the portfolio
    public void printInvestments() {
        System.out.printf("| %-10s | %-6s %2s |%n", this.getPortfolioName(), "Sector:", this.getPreferredSector());
        System.out.println("Investments:");
        System.out.printf("| %-10s | %-6s | %-20s | %-12s |%n", "Name", "Price", "Expected Return (%)", "Industry");
        System.out.printf("| %-54s |%n", "---------------------------------------------------------");

        for (Investment i : investments) {
            System.out.printf("| %-10s | %-6s | %-20s | %-12s |%n", i.getInvestmentname(), i.getPrice(),
                    i.getReturnPercentage(), i.getSector());
        }
    }




}
