package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class InvestmentTest {
    private Investment testInvestment;

    @BeforeEach
    void runBefore() {
        testInvestment = new Investment("Name", 5.6F, "IT", 50);
    }

    @Test
    void testConstructor() {
        assertEquals("Name", testInvestment.getInvestmentname());
        assertEquals(5.6F, testInvestment.getReturnPercentage());
        assertEquals("IT", testInvestment.getSector());
        assertEquals(50, testInvestment.getPrice());

    }

    @Test
    void testSetInvestmentName() {
        testInvestment.setInvestmentname("Name2");
        assertEquals("Name2", testInvestment.getInvestmentname());
    }

    @Test
    void testSetReturnPercentage() {
        testInvestment.setReturnPercentage(10.2F);
        assertEquals(10.2F, testInvestment.getReturnPercentage());
    }

    @Test
    void testSetSector() {
        testInvestment.setSector("Healthcare");
        assertEquals("Healthcare", testInvestment.getSector());
    }

    @Test
    void testSetPrice() {
        testInvestment.setPrice(100);
        assertEquals(100, testInvestment.getPrice());
    }
}