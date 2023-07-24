package model;

import model.Portfolio;
import model.Investment;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class MarketTest {
    private Market testMarket;
    private Portfolio testPortfolio1;
    private Portfolio testPortfolio2;
    private Investment testInvestment;
    private Investment testInvestment2;
    private Investment testInvestment3;


    @BeforeEach
    void runBefore() {
        testMarket = new Market();
        testPortfolio1 = new Portfolio("Name1", "IT", 50000);
        testPortfolio2 = new Portfolio("Name2", "Energy", 50000);
        testInvestment = new Investment("iName", 10.0F, "IT", 50);
        testInvestment2 = new Investment("iName2", 1.0F, "Healthcare", 28);
        testInvestment3 = new Investment("iName3", 12.0F, "RANDOM", 50);

    }

    @Test
    void testConstructor() {
        assertEquals(0, testMarket.getInvestmentList().size());
        assertEquals(0, testMarket.getPortfolioList().size());
    }

    @Test
    void testAddPortfolioOne() {
        testMarket.addPortfolio(testPortfolio1);
        assertEquals(1, testMarket.getPortfolioList().size());
        assertEquals(testPortfolio1, testMarket.getPortfolioList().get(0));
    }

    @Test
    void testAddPortfolioTwo() {
        testMarket.addPortfolio(testPortfolio1);
        testMarket.addPortfolio(testPortfolio2);
        assertEquals(2, testMarket.getPortfolioList().size());
        assertEquals(testPortfolio2, testMarket.getPortfolioList().get(1));
    }

    @Test
    void testAddInitInvestments() {
        testMarket.addInitInvestments();
        assertEquals(5, testMarket.getInvestmentList().size());
    }

    @Test
    void testAddInitPortfolios() {
        testMarket.addInitPortfolios();
        assertEquals(2, testMarket.getPortfolioList().size());
    }

    @Test
    void testDeletePortfolioSingle() {
        testMarket.addPortfolio(testPortfolio1);
        testMarket.deletePortfolio(testPortfolio1);

        assertEquals(0, testMarket.getPortfolioList().size());

    }

    @Test
    void testDeletePortfolioOneLeft() {
        testMarket.addPortfolio(testPortfolio1);
        testMarket.deletePortfolio(testPortfolio1);
        testMarket.addPortfolio(testPortfolio2);

        assertEquals(1, testMarket.getPortfolioList().size());
        assertEquals(testPortfolio2, testMarket.getPortfolioList().get(0));

    }

    @Test
    void testViewPortfolio() {
        //
    }

    @Test
    void testViewInvestment() {
        //
    }

    @Test
    void testLookupPortfolioByName() {
        testMarket.addPortfolio(testPortfolio1);
        testMarket.addPortfolio(testPortfolio2);

        Portfolio tester = testMarket.lookupPortfolioByName("Name1");

        assertEquals(testPortfolio1, tester);

    }

    @Test
    void testLookupInvestmentByName() {
        testMarket.addInvestment(testInvestment);
        testMarket.addInvestment(testInvestment2);
        testMarket.addInvestment(testInvestment3);

        Investment tester2 = testMarket.lookupInvestmentByName("iName2");

        assertEquals(testInvestment2, tester2);

    }


    @Test
    void testNewPortfolio() {
        testMarket.newPortfolio("NAME5", "IT", 5000);
        assertEquals(1, testMarket.getPortfolioList().size());

    }

    @Test
    void testNewInvestment() {
        testMarket.newInvestment("NAME", 12.2F, "IT", 50);
        assertEquals(1, testMarket.getInvestmentList().size());

    }

    @Test
    void testToJson() {
        testMarket.addPortfolio(testPortfolio1);
        testMarket.addInvestment(testInvestment);

        JSONObject jsonTest = testMarket.toJson();
        assertNotNull(jsonTest);

        JSONArray portfolioArray = jsonTest.getJSONArray("portfolio");
        assertEquals(1, portfolioArray.length());

        JSONArray investmentArray = jsonTest.getJSONArray("investment");
        assertEquals(1, investmentArray.length());

    }

}

