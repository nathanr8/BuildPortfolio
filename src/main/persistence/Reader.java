package persistence;

import model.Portfolio;
import model.Investment;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

import javax.sound.sampled.Port;

// This class represents a reader that reads the portfolios and investments from JSON data stored in files
public class Reader {
    private String source;

    // EFFECTS: constructs a reader to read from source files
    public Reader(String source) {
        this.source = source;
    }

    // EFFECTS: reads portfolio from file and returns it;
    // throws the IOException if an error occurs reading data from file
    public Portfolio readPortfolio() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePortfolio(jsonObject);
    }

    // EFFECTS: reads investment from file and returns it;
    // throws the IOException if an error occurs reading data from file
    public Investment readInvestment() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseInvestment(jsonObject);
    }

    // EFFECTS: reads source file as a string then returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses the portfolio from JSON object and returns it
    private Portfolio parsePortfolio(JSONObject jsonObject) {
        String portfolioName = jsonObject.getString("Name");
        Integer initialCapital = jsonObject.getInt("Starting Capital");
        Integer availableCapital = jsonObject.getInt("Available Capital");
        String preferredSector = jsonObject.getString("Strong economic sector");
        Portfolio portfolio = new Portfolio(portfolioName, preferredSector, initialCapital);
        addMultipleInvestmentJson(portfolio, jsonObject);
        return portfolio;
    }

    // EFFECTS: parses the investment from JSON object and returns it
    private Investment parseInvestment(JSONObject jsonObject) {
        String investmentName = jsonObject.getString("Name");
        String sector = jsonObject.getString("Sector");
        float returnPercentage = jsonObject.getFloat("Return %");
        Integer price = jsonObject.getInt("Price");
        Investment investment = new Investment(investmentName, returnPercentage, sector, price);
        return investment;

    }

    // MODIFIES: portfolio
    // EFFECTS: parses multiple investments from JSON object and adds them to a portfolio
    private void addMultipleInvestmentJson(Portfolio portfolio, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("portfolio");
        for (Object json: jsonArray) {
            JSONObject nextInvestment = (JSONObject) json;
            addInvestmentJson(portfolio, nextInvestment);
        }
    }

    // MODIFIES: wardrobe
    // EFFECTS: parses singular clothing from JSON object and adds it to the wardrobe
    private void addInvestmentJson(Portfolio portfolio, JSONObject jsonObject) {
        String investmentName = jsonObject.getString("Name");
        String sector = jsonObject.getString("Sector");
        float returnPercentage = jsonObject.getFloat("Return %");
        Integer price = jsonObject.getInt("Price");
        Investment investment = new Investment(investmentName, returnPercentage, sector, price);

        portfolio.addInvestments(investment, 1);
    }
}
