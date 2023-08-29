package model;

import persistence.Writable;

import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;

// Represents a market with investments and portfolios.
public class Market implements Writable {
    private Investment google;
    private Investment blackrock;
    private Investment tesla;
    private Investment nextEra;
    private Investment pfizer;
    private Portfolio portfolioA;
    private Portfolio portfolioB;
    private final ArrayList<Investment> investmentList;
    private final ArrayList<Portfolio> portfolioList;
    private EventLog eventLog;

    // Constructor
    // EFFECTS: Constructs a market with initial investments and portfolios empty
    public Market() {
        investmentList = new ArrayList<>();
        portfolioList = new ArrayList<>();
        eventLog = EventLog.getInstance();
        EventLog.getInstance().logEvent(new Event("New Market."));

    }

    //GETTERS
    public ArrayList<Investment> getInvestmentList() {
        return this.investmentList;
    }

    public ArrayList<Investment> getInvestmentListDelete() {
        EventLog.getInstance().logEvent(new Event("Removed Investment from Market"));
        return this.investmentList;
    }

    public ArrayList<Portfolio> getPortfolioList() {
        return this.portfolioList;
    }

    public ArrayList<Portfolio> getPortfolioListDelete() {
        EventLog.getInstance().logEvent(new Event("Removed Portfolio from Market"));
        return this.portfolioList;
    }


    //REQUIRES: portfolio exits
    //MODIFIES: this
    //EFFECTS: adds a portfolio to the market's portfolio list
    public void addPortfolio(Portfolio portfolio) {
        portfolioList.add(portfolio);
        EventLog.getInstance().logEvent(new Event("Add portfolio to market"));
    }

    //REQUIRES: investment exits
    //MODIFIES: this
    //EFFECTS: adds an investment to the market's investment list
    public void addInvestment(Investment investment) {
        investmentList.add(investment);
        EventLog.getInstance().logEvent(new Event("Add investment to market"));
    }


    // MODIFIES: this
    // EFFECTS: initializes investments, adds them to market to have starting investments to use
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
    // EFFECTS: initializes portfolios, adds them to market to have starting portfolios to use
    public void addInitPortfolios() {
        portfolioA = new Portfolio("PortfolioA", "IT", 10000);
        portfolioB = new Portfolio("PortfolioB", "Energy", 10000);
        portfolioList.add(portfolioA);
        portfolioList.add(portfolioB);
    }

    //REQUIRES: portfolio already in portfolio list
    //MODIFIES: this
    //EFFECTS: removes portfolio from portfolioList
    public void deletePortfolio(Portfolio portfolioName) {
        portfolioList.remove(portfolioName);
        EventLog.getInstance().logEvent(new Event("Deleted Portfolio"));
    }

    // REQUIRES: portfolioName is the name of an existing portfolio
    // EFFECTS: returns portfolio that has name portfolioName
    public Portfolio lookupPortfolioByName(String portfolioName) {
        Portfolio port = null;
        for (Portfolio p : portfolioList) {
            if (p.getPortfolioName().equalsIgnoreCase(portfolioName)) {
                port = p;
            }
        }
        return port;
    }

    // REQUIRES: investmentName is the name of an existing portfolio
    // EFFECTS: returns investment that has name as investmentName
    public Investment lookupInvestmentByName(String investmentName) {
        Investment inv = null;
        for (Investment i : investmentList) {
            if (i.getInvestmentname().equalsIgnoreCase(investmentName)) {
                inv = i;
            }
        }
        return inv;
    }

    //REQUIRES: portfolioName is not already in use
    // MODIFIES: this
    // EFFECTS: creates a portfolio, adds it to portfolioList
    public Portfolio newPortfolio(String name, String sector, Integer capital) {
        Portfolio newPortfolio;
        newPortfolio = new Portfolio(name, sector, capital);
        newPortfolio.setInitialCapital(capital);
        newPortfolio.setAvailableCapital(capital);
        portfolioList.add(newPortfolio);
        EventLog.getInstance().logEvent(new Event("New Portfolio added to market"));
        return newPortfolio;
    }

    //REQUIRES: investmentName is not already in market
    // MODIFIES: this
    // EFFECTS: creates an investment, adds it to investmentList
    public Investment newInvestment(String name, Float returnP, String sector, Integer price) {
        Investment newInvestment;
        newInvestment = new Investment(name, returnP, sector, price);
        investmentList.add(newInvestment);
        EventLog.getInstance().logEvent(new Event("New Investment added to market"));
        return newInvestment;


    }

    // EFFECTS: Writes a market to JSON data
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("portfolio", portfolioToJson());
        json.put("investment", investmentToJson());
        return json;
    }

    // EFFECTS: Writes the list of portfolios to JSON data
    private JSONArray portfolioToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Portfolio portfolio : portfolioList) {
            jsonArray.put(portfolio.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: Writes the list of investments to JSON data
    private JSONArray investmentToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Investment investment : investmentList) {
            jsonArray.put(investment.toJson());
        }

        return jsonArray;
    }

    public EventLog getEventLog() {
        return eventLog;
    }




}
