package model;

public class Investment {
    private String investmentname;
    private float returnPercentage;
    private String sector;
    private int price;


    //EFFECTS: constructs an investment with a given name, return#,
    // and economic sector
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

}

