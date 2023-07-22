package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Market {
    private Investment google;
    private Investment blackrock;
    private Investment tesla;
    private Investment nextEra;
    private Investment pfizer;
    private Portfolio portfolioA;
    private Portfolio portfolioB;
    private ArrayList<Investment> investmentList;
    private ArrayList<Portfolio> portfolioList;

    // Constructor
    // EFFECTS: Constructs a market with initial investments and portfolios
    public Market() {
        investmentList = new ArrayList<>();
        portfolioList = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: initializes investments, adds them to market
    public void addInitInvestments() {
        google = new Investment("Google", 8.1F, "IT", 200);
        blackrock = new Investment("Blackrock", 6.2F, "Financial", 150);
        tesla = new Investment("Tesla", 12.2F, "Energy", 300);
        nextEra = new Investment("NextEra", 7.2F, "Utilities", 50);
        pfizer = new Investment("Pfizer", 5.7F, "Healthcare", 100);
        investmentList.add(google);
        investmentList.add(blackrock);
        investmentList.add(tesla);
        investmentList.add(nextEra);
        investmentList.add(pfizer);

    }

    // MODIFIES: this
    // EFFECTS: initializes portfolios, adds them to market
    public void addInitPortfolios() {
        portfolioA = new Portfolio("PortfolioA", "IT", 10000);
        portfolioA.addInvestments(google, 2);
        portfolioA.addInvestments(blackrock, 2);
        portfolioA.addInvestments(tesla, 2);
        portfolioA.addInvestments(nextEra, 2);
        portfolioA.addInvestments(pfizer, 2);

        portfolioB = new Portfolio("PortfolioB", "Energy", 10000);
        portfolioB.addInvestments(google, 2);
        portfolioB.addInvestments(blackrock, 2);
        portfolioB.addInvestments(tesla, 2);
        portfolioB.addInvestments(nextEra, 2);
        portfolioB.addInvestments(pfizer, 2);
        portfolioList.add(portfolioA);
        portfolioList.add(portfolioB);

    }

    //REQUIRES: portfolio is already constructed
    //MODIFIES: this
    //EFFECTS: removes portfolio from portfolioList
    private void deletePortfolio() {
        System.out.println("Name of portfolio you would like to delete?");
        String portfolioName = input.next();
        Portfolio port = lookupPortfolioByName(portfolioName);
        this.portfolioList.remove(port);
        viewPortfolios();
    }


}
