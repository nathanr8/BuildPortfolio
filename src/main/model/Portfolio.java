package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.text.DecimalFormat;



// Represents an investment portfolio one can add investments to
public class Portfolio implements Writable {
    private String portfolioName;
    private ArrayList<Investment> investments;
    private String preferredSector;
    private int initialCapital;
    private int availableCapital;
    private int portfolioCapital;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private float strongSectorBonus;


    //REQUIRES: initialCapital >= 1000
    //EFFECTS: Constructs a new portfolio with name, preferred sector, and starting capital
    public Portfolio(String portfolioname, String preferredSector, int initialCapital) {
        this.portfolioName = portfolioname;
        this.preferredSector = preferredSector;
        this.initialCapital = initialCapital;
        this.availableCapital = initialCapital;
        this.investments = new ArrayList<>();
        this.portfolioCapital = 0;
        this.strongSectorBonus = 1.05F;
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

    //REQUIRES: initialCapital >= 1000
    //MODIFIES: this
    //EFFECTS: sets portfolio's starting capital
    public void setInitialCapital(int initialCapital) {
        this.initialCapital = initialCapital;
    }


    //MODIFIES: this
    //EFFECTS: sets portfolio's starting available capital
    public void setAvailableCapital(int capital) {
        this.availableCapital = capital;
    }

    //REQUIRES: (price * quantity) <= available capital, quantity >= 1
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

    //EFFECTS: calculates $ amount return for portfolio
    public double calculateReturnAmountDollar() {
        double i = 0.0F;
        for (Investment inv: this.investments) {
            if (inv.getSector() == this.getPreferredSector()) {
                float j = strongSectorBonus * inv.getReturnPercentage();
                inv.setReturnPercentage(j);
            }
            double percent = inv.getReturnPercentage() / 100;
            i += (inv.getPrice() * percent);
        }
        return (double) Math.round(i);
    }

    //EFFECTS: calculates return % for portfolio
    public String calculateReturnAmountPercent() {
        double i = this.calculateReturnAmountDollar();
        double j = this.getPortfolioCapital();
        return df.format((i / j) * 100);
    }

    //REQUIRES: quantity >= 1, portfolio # of selected investments >= quantity,
    //MODIFIES: this
    //EFFECTS: removes investment from portfolio, adds back capital to available capital
    public void removeInvestment(Investment i, int quantity) {
        for (int n = 0; n < quantity; n++) {
            this.investments.remove(i);
        }
        availableCapital = availableCapital + (i.getPrice() * quantity);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", portfolioName);
        json.put("Starting Capital", initialCapital);
        json.put("Available Capital", availableCapital);
        json.put("Strong economic sector", preferredSector);
        json.put("Investments", investmentsToJson());
        return json;
    }

    // EFFECTS: returns investments in this portfolio as a JSON array
    private JSONArray investmentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Investment t : investments) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
