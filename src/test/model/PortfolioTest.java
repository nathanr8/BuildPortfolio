package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PortfolioTest {
    private Portfolio testPortfolio;
    private Investment testInvestment;
    private Investment testInvestment2;

    @BeforeEach
    void runBefore() {
        testPortfolio = new Portfolio("Pname", "IT", 50000);
        testInvestment = new Investment("iName", 10.0F, "IT", 50);
        testInvestment2 = new Investment("iName2", 1.0F, "Healthcare", 28);
    }

    @Test
    void testConstructor() {
        assertEquals("Pname", testPortfolio.getPortfolioName());
        assertEquals("IT", testPortfolio.getPreferredSector());
        assertEquals(50000, testPortfolio.getCapital());
        assertEquals(50000, testPortfolio.getAvailableCapital());
        assertEquals(0, testPortfolio.getInvestments().size());
    }

    @Test
    void testAddInvestmentsSingle() {
        testPortfolio.addInvestments(testInvestment, 1);
        ArrayList<Investment> getII = testPortfolio.getInvestments();
        assertEquals(testInvestment, getII.get(0));
        assertEquals(1, getII.size());

    }

    @Test
    void testAddInvestmentsMulti() {
        testPortfolio.addInvestments(testInvestment, 2);
        ArrayList<Investment> getII = testPortfolio.getInvestments();
        assertEquals(testInvestment, getII.get(0));
        assertEquals(testInvestment, getII.get(1));
        assertEquals(2, getII.size());

    }

    @Test
    void testAddInvestmentsMultiDifferent() {
        testPortfolio.addInvestments(testInvestment, 1);
        testPortfolio.addInvestments(testInvestment2, 2);
        testPortfolio.addInvestments(testInvestment, 1);
        ArrayList<Investment> getII = testPortfolio.getInvestments();
        assertEquals(testInvestment, getII.get(0));
        assertEquals(testInvestment2, getII.get(1));
        assertEquals(testInvestment, getII.get(3));
        assertEquals(4, getII.size());

    }

    @Test
    void testAddInvestmentsError() {
        String success = testPortfolio.addInvestments(testInvestment, 1);
        assertEquals("Investment added successfully!", success);
        ArrayList<Investment> getII = testPortfolio.getInvestments();
        assertEquals(testInvestment, getII.get(0));
        assertEquals(1, getII.size());

        String errorM = testPortfolio.addInvestments(testInvestment2, 50000);
        assertEquals("Sorry, this portfolio does not have enough available capital.", errorM);

    }
    @Test
    void testSetName() {
        assertEquals("Pname", testPortfolio.getPortfolioName());
        testPortfolio.setPortfolioName("BOB");
        assertEquals("BOB", testPortfolio.getPortfolioName());
    }

    @Test
    void testSetPrefferedSector() {
        assertEquals("IT", testPortfolio.getPreferredSector());
        testPortfolio.setPreferredSector("TECH");
        assertEquals("TECH", testPortfolio.getPreferredSector());
    }

    @Test
    void testSetCapital() {
        assertEquals(50000, testPortfolio.getCapital());
        testPortfolio.setCapital(10000);
        assertEquals(10000, testPortfolio.getCapital());
    }

    @Test
    void testSetAvailableCapital() {
        assertEquals(50000, testPortfolio.getAvailableCapital());
        testPortfolio.setAvailableCapital(10000);
        assertEquals(10000, testPortfolio.getAvailableCapital());
    }

    @Test
    void testRemoveInvestmentSingleHaveMany() {
        assertEquals(0, testPortfolio.getInvestments().size());
        testPortfolio.addInvestments(testInvestment, 2);
        assertEquals(2, testPortfolio.getInvestments().size());
        testPortfolio.removeInvestment(testInvestment, 1);
        assertEquals(1, testPortfolio.getInvestments().size());

    }

    @Test
    void testRemoveInvestmentSingleHaveSingle() {
        assertEquals(0, testPortfolio.getInvestments().size());
        testPortfolio.addInvestments(testInvestment, 1);
        assertEquals(1, testPortfolio.getInvestments().size());
        testPortfolio.removeInvestment(testInvestment, 1);
        assertEquals(0, testPortfolio.getInvestments().size());

    }

    @Test
    void testRemoveInvestmentMulti() {
        assertEquals(0, testPortfolio.getInvestments().size());
        testPortfolio.addInvestments(testInvestment, 3);
        assertEquals(3, testPortfolio.getInvestments().size());
        testPortfolio.removeInvestment(testInvestment, 2);
        assertEquals(1, testPortfolio.getInvestments().size());

    }

    @Test
    void testRemoveInvestmentMultiNotTogether() {
        assertEquals(0, testPortfolio.getInvestments().size());
        testPortfolio.addInvestments(testInvestment, 1);
        testPortfolio.addInvestments(testInvestment2, 2);
        testPortfolio.addInvestments(testInvestment, 1);
        assertEquals(4, testPortfolio.getInvestments().size());
        testPortfolio.removeInvestment(testInvestment, 2);
        assertEquals(2, testPortfolio.getInvestments().size());

    }

    @Test
    void testRemoveInvestmentGetBackCapital() {
        assertEquals(50000, testPortfolio.getAvailableCapital());
        assertEquals(50000, testPortfolio.getCapital());
        testPortfolio.addInvestments(testInvestment, 1);
        assertEquals(50000-50, testPortfolio.getAvailableCapital());
        testPortfolio.removeInvestment(testInvestment, 1);
        assertEquals(50000, testPortfolio.getAvailableCapital());

    }
}
