package persistence;

import model.Investment;
import model.Portfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkInvestment(String name, Float returnP, String sector, Integer price, Investment investment) {
        assertEquals(name, investment.getInvestmentname());
        assertEquals(returnP, investment.getReturnPercentage());
        assertEquals(sector, investment.getSector());
        assertEquals(price, investment.getPrice());
    }

    protected void checkPortfolio(String name, String sector, Integer capital, Portfolio portfolio) {
        assertEquals(name, portfolio.getPortfolioName());
        assertEquals(sector, portfolio.getPreferredSector());
        assertEquals(capital, portfolio.getInitialCapital());

    }

}

