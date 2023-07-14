package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PortfolioTest {
    private Portfolio testPortfolio;

    @BeforeEach
    void runBefore() {
        testPortfolio = new Portfolio("Pname", "IT", 50000);
    }

    @Test
    void testConstructor() {
        assertEquals("Pname", testPortfolio.getPortfolioname());
        assertEquals("IT", testPortfolio.getPreferredSector());
        assertEquals(50000, testPortfolio.getCapital());
        assertEquals(50000, testPortfolio.getAvailablecapital());

    }
}
