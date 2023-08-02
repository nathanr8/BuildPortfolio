package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

// Represents an investment in the market one can too to an investment portfolio
public class Investment implements Writable {
    private String investmentname;
    private float returnPercentage;
    private String sector;
    private int price;


    //REQUIRES: price > 0
    //EFFECTS: constructs an investment with a given name, return %, economic sector, and price
    public Investment(String investmentname, float returnPercentage, String sector, int price) {
        this.investmentname = investmentname;
        this.returnPercentage = returnPercentage;
        this.sector = sector;
        this.price = price;
    }


    // getters
    public String getInvestmentname() {
        return investmentname;
    }

    public String getSector() {
        return sector;
    }

    public float getReturnPercentage() {
        return returnPercentage;
    }

    public int getPrice() {
        return price;
    }


    // setters

    //MODIFIES: this
    //EFFECTS: sets investment's name
    public void setInvestmentname(String investmentname) {
        this.investmentname = investmentname;
    }

    //MODIFIES: this
    //EFFECTS: sets investment's expected return percentage
    public void setReturnPercentage(float returnPercentage) {
        this.returnPercentage = returnPercentage;
    }

    //MODIFIES: this
    //EFFECTS: sets investment's sector
    public void setSector(String sector) {
        this.sector = sector;
    }

    //MODIFIES: this
    //EFFECTS: sets investment's price
    public void setPrice(int price) {
        this.price = price;
    }

    // EFFECTS: Writes an investment to JSON data
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", investmentname);
        json.put("Sector", sector);
        json.put("Return %", returnPercentage);
        json.put("Price", price);
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Investment)) {
            return false;
        }

        Investment that = (Investment) o;

        if (Float.compare(that.returnPercentage, returnPercentage) != 0) {
            return false;
        }
        if (price != that.price) {
            return false;
        }
        if (!Objects.equals(investmentname, that.investmentname)) {
            return false;
        }
        return Objects.equals(sector, that.sector);
    }


    @Override
    public int hashCode() {
        int result = investmentname != null ? investmentname.hashCode() : 0;
        result = 31 * result + (returnPercentage != +0.0f ? Float.floatToIntBits(returnPercentage) : 0);
        result = 31 * result + (sector != null ? sector.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }
}


