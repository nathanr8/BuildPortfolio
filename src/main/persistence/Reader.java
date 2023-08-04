package persistence;

import model.Market;
import model.Portfolio;
import model.Investment;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;


// This class represents a reader that reads the market, portfolio and investments from JSON data stored in files
public class Reader {
    private final String source;

    // EFFECTS: constructs a reader to read from source files
    public Reader(String source) {
        this.source = source;
    }

    // EFFECTS: reads market from file and returns it;
    // throws the IOException if an error occurs reading data from file
    public Market read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMarket(jsonObject);
    }

    // EFFECTS: reads source file as a string then returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }


    // EFFECTS: parses market from JSON object and returns it
    private Market parseMarket(JSONObject jsonObject) {
        Market market = new Market();
        addMultipleInvestmentJson(market, jsonObject);
        addMultiplePortfolioJson(market, jsonObject);
        return market;
    }

    // MODIFIES: market
    // EFFECTS: parses multiple investments from JSON object and adds them to market
    private void addMultipleInvestmentJson(Market market, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("investment");
        for (Object json: jsonArray) {
            JSONObject nextInvestment = (JSONObject) json;
            addInvestmentJson(market, nextInvestment);
        }
    }

    // MODIFIES: market
    // EFFECTS: parses multiple portfolios from JSON object and adds them to market
    private void addMultiplePortfolioJson(Market market, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("portfolio");
        for (Object json: jsonArray) {
            JSONObject nextPortfolio = (JSONObject) json;
            addPortfolioJson(market, nextPortfolio);
        }
    }


    // MODIFIES: market
    // EFFECTS: parses singular investment from JSON object and adds it to the market
    private void addInvestmentJson(Market market, JSONObject jsonObject) {
        String investmentName = jsonObject.getString("Name");
        String sector = jsonObject.getString("Sector");
        float returnPercentage = jsonObject.getFloat("Return %");
        Integer price = jsonObject.getInt("Price");
        Investment investment = new Investment(investmentName, returnPercentage, sector, price);

        market.newInvestment(investmentName, returnPercentage, sector, price);
    }

    // MODIFIES: market
    // EFFECTS: parses singular portfolio from JSON object and adds it to the market
    private void addPortfolioJson(Market market, JSONObject jsonObject) {
        String portfolioName = jsonObject.getString("Name");
        Integer initialCapital = jsonObject.getInt("Starting Capital");
        Integer availableCapital = jsonObject.getInt("Available Capital");
        String preferredSector = jsonObject.getString("Strong economic sector");
        Portfolio portfolio = new Portfolio(portfolioName, preferredSector, initialCapital);

        addMultipleInvestmentToPortfolioJson(portfolio, jsonObject);

        market.addPortfolio(portfolio);

    }

    // MODIFIES: portfolio
    // EFFECTS: parses singular investment from JSON object and adds it to the portfolio
    private void addInvestmentPortfolioJson(Portfolio portfolio, JSONObject jsonObject) {
        String investmentName = jsonObject.getString("Name");
        String sector = jsonObject.getString("Sector");
        float returnPercentage = jsonObject.getFloat("Return %");
        Integer price = jsonObject.getInt("Price");
        Investment investment = new Investment(investmentName, returnPercentage, sector, price);

        portfolio.addInvestments(investment, 1);
    }

    // MODIFIES: portfolio
    // EFFECTS: parses multiple investments from JSON object and adds them to portfolio
    private void addMultipleInvestmentToPortfolioJson(Portfolio portfolio, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("InvestmentsP");
        for (Object json: jsonArray) {
            JSONObject nextInvestment = (JSONObject) json;
            addInvestmentPortfolioJson(portfolio, nextInvestment);
        }
    }


}
