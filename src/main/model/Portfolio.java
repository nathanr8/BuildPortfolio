package model;

import java.util.ArrayList;

public class Portfolio {
    private String portfolioname;
    private ArrayList<Investment> investments;
    private String preferredSector;
    private int capital;
    private int availablecapital;


    // Constructs
    public Portfolio(String portfolioname, String preferredSector, int capital) {
        this.portfolioname = portfolioname;
        this.preferredSector = preferredSector;
        this.capital = capital;
        this.availablecapital = capital;
        this.investments = new ArrayList<>();
    }

    // getters

    public String getPortfolioname() {
        return portfolioname;
    }

    public String getPreferredSector() {
        return preferredSector;
    }

    public int getCapital() {
        return capital;
    }

    public ArrayList<Investment> getInvestments() {
        return investments;
    }

    public int getAvailablecapital() {
        return availablecapital;
    }

    // setters

    //MODIFIES: this
    //EFFECTS: sets portfolio's name
    public void setPortfolioname(String portfolioname) {
        this.portfolioname = portfolioname;
    }

    //MODIFIES: this
    //EFFECTS: sets portfolio's preferred sector
    public void setPreferredSector(String preferredSector) {
        this.preferredSector = preferredSector;
    }

    //REQUIRES: capital >= 1000
    //MODIFIES: this
    //EFFECTS: sets portfolio's starting capital
    public void setCapital(int capital) {
        this.capital = capital;
    }

    //REQUIRES: i.getInvestmentname() in future, price*quantity <= avail. capital
    // quantity >= 1
    //MODIFIES: this
    //EFFECTS: adds an investment to portfolio's investment list
    public void addInvestments(Investment i, int quantity) {
        if ((i.getPrice() * quantity) <= this.availablecapital) {
            for (int n = 0; n < quantity; n++) {
                this.investments.add(i);
            }
            this.availablecapital = availablecapital - (i.getPrice() * quantity);
        } else {
            errorMessage();
        }
    }

    public void calculateReturn(){
        // stub
    }

    public void errorMessage(){
        // stub
    }

    //REQUIRES: quantity >= 1
    //MODIFIES: this
    //EFFECTS: removes investment from portfolio, adds back capital to available capital
    public void removeInvestment(Investment i, int quantity) {
        for (int n = 0; n < quantity; n++) {
            this.investments.remove(i);
        }
        availablecapital = availablecapital + (i.getPrice() * quantity);
    }

    //EFFECTS: prints out a list of all investments in the portfolio
    public void printInvestments() {
        System.out.printf("| %-10s | %-6s %2s |%n", this.getPortfolioname(), "Sector:", this.getPreferredSector());
        System.out.println("Investments:");
        System.out.printf("| %-10s | %-6s | %-20s | %-12s |%n", "Name", "Price", "Expected Return (%)", "Industry");
        System.out.printf("| %-54s |%n", "---------------------------------------------------------");

        for (Investment i : investments) {
            System.out.printf("| %-10s | %-6s | %-20s | %-12s |%n", i.getInvestmentname(), i.getPrice(),
                    i.getReturnPercentage(), i.getSector());
        }
    }




}
