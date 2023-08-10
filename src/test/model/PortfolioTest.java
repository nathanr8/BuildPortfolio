package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PortfolioTest {
    private Portfolio testPortfolio;
    private Investment testInvestment;
    private Investment testInvestment2;
    private Investment testInvestment3;

    @BeforeEach
    void runBefore() {
        testPortfolio = new Portfolio("Pname", "IT", 50000);
        testInvestment = new Investment("iName", 10.0F, "IT", 50);
        testInvestment2 = new Investment("iName2", 1.0F, "Healthcare", 28);
        testInvestment3 = new Investment("iName3", 10.0F, "RANDOM", 50);
    }

    @Test
    void testConstructor() {
        assertEquals("Pname", testPortfolio.getPortfolioName());
        assertEquals("IT", testPortfolio.getPreferredSector());
        assertEquals(50000, testPortfolio.getInitialCapital());
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
        assertEquals(50000, testPortfolio.getInitialCapital());
        testPortfolio.setInitialCapital(10000);
        assertEquals(10000, testPortfolio.getInitialCapital());
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
        assertEquals(50000, testPortfolio.getInitialCapital());
        testPortfolio.addInvestments(testInvestment, 1);
        assertEquals(50000-50, testPortfolio.getAvailableCapital());
        testPortfolio.removeInvestment(testInvestment, 1);
        assertEquals(50000, testPortfolio.getAvailableCapital());

    }

    @Test
    void testCalculatePortfolioReturnDollarWithSector() {
        testPortfolio.addInvestments(testInvestment, 5);
        double i = testPortfolio.calculateReturnAmountDollar();
        assertEquals(26.25, i);

    }

    @Test
    void testCalculatePortfolioReturnDollarWithoutSector() {
        testPortfolio.addInvestments(testInvestment3, 5);
        double i = testPortfolio.calculateReturnAmountDollar();
        assertEquals(25.00, i);

    }

    @Test
    void testCalculatePortfolioReturnDollarMultiWithSector() {
        testPortfolio.addInvestments(testInvestment, 5);
        testPortfolio.addInvestments(testInvestment2, 16);
        double i = testPortfolio.calculateReturnAmountDollar();
        assertEquals(30.73, i);

    }

    @Test
    void testCalculatePortfolioReturnDollarMultiNoSector() {
        testPortfolio.addInvestments(testInvestment3, 5);
        testPortfolio.addInvestments(testInvestment2, 16);
        double i = testPortfolio.calculateReturnAmountDollar();
        assertEquals(29.48, i);

    }

    @Test
    void testGetPortfolioCapital() {
        testPortfolio.addInvestments(testInvestment, 5);
        testPortfolio.addInvestments(testInvestment2, 16);
        int i = testPortfolio.getPortfolioCapital();
        int ii = testPortfolio.getPortfolioCapitalChart();
        assertEquals(698, i);
        assertEquals(698, ii);

    }


    @Test
    void testCalculatePortfolioReturnPercent() {
        testPortfolio.addInvestments(testInvestment, 5);
        String j = testPortfolio.calculateReturnAmountPercent();
        assertEquals("10.50", j);
    }

    @Test
    void testCalculatePortfolioReturnPercent2() {
        testPortfolio.addInvestments(testInvestment3, 5);
        String j = testPortfolio.calculateReturnAmountPercent();
        assertEquals("10.00", j);
    }

    @Test
    void testCalculatePortfolioReturnPercentMulti() {
        testPortfolio.addInvestments(testInvestment, 5);
        testPortfolio.addInvestments(testInvestment2, 16);
        String j = testPortfolio.calculateReturnAmountPercent();
        assertEquals("4.40", j);
    }

    @Test
    void testCalculatePortfolioReturnPercentMultiNotSector() {
        testPortfolio.addInvestments(testInvestment3, 5);
        testPortfolio.addInvestments(testInvestment2, 16);
        String j = testPortfolio.calculateReturnAmountPercent();
        assertEquals("4.22", j);
    }

    @Test
    void testReturnCalcEmpty() {
        assertEquals(0.0, testPortfolio.calculateReturnAmountDollar());
    }

    @Test
    void testReturnCalcEmpty2() {
        assertEquals("0.00", testPortfolio.calculateReturnAmountPercent());
    }

    @Test
    void testDeleteInvEmpty() {
        testPortfolio.removeInvestment(testInvestment2, 5);
        assertEquals(50000, testPortfolio.getAvailableCapital());
    }

    @Test
    void testDeleteInvTooMany() {
        testPortfolio.addInvestments(testInvestment2, 5);
        testPortfolio.removeInvestment(testInvestment2, 6);

        assertTrue(testPortfolio.getInvestments().contains(testInvestment2));
        assertEquals(49860, testPortfolio.getAvailableCapital());
    }

    @Test
    void testDeleteInvTooMany2() {
        testPortfolio.addInvestments(testInvestment2, 5);
        testPortfolio.removeInvestment(testInvestment2, 3);
        assertTrue(testPortfolio.getInvestments().contains(testInvestment2));
        assertEquals(49944, testPortfolio.getAvailableCapital());

        testPortfolio.removeInvestment(testInvestment2, 6);
        assertTrue(testPortfolio.getInvestments().contains(testInvestment2));
        assertEquals(49944, testPortfolio.getAvailableCapital());
    }

    @Test
    void testDeleteInvExact() {
        testPortfolio.addInvestments(testInvestment2, 5);
        testPortfolio.removeInvestment(testInvestment2, 3);
        assertTrue(testPortfolio.getInvestments().contains(testInvestment2));
        assertEquals(49944, testPortfolio.getAvailableCapital());

        testPortfolio.removeInvestment(testInvestment2, 2);
        assertFalse(testPortfolio.getInvestments().contains(testInvestment2));
        assertEquals(50000, testPortfolio.getAvailableCapital());
    }



}
