package persistence;

import model.Investment;
import model.Market;
import model.Portfolio;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;



public class WriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Market myMarket = new Market();
            Writer writer = new Writer("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMarket() {
        try {
            Market myMarket = new Market();
            Writer writer = new Writer("./data/testWriterEmptyMarket.json");
            writer.open();
            writer.write(myMarket);
            writer.close();

            Reader reader = new Reader("./data/testWriterEmptyMarket.json");
            myMarket = reader.read();
            assertEquals(0, myMarket.getPortfolioList().size());
            assertEquals(0, myMarket.getInvestmentList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterWithFilledMarket() {
        try {
            Market testMarket = new Market();
            Portfolio testPortfolio = new Portfolio("NAME1", "IT", 5000);
            Investment testInvestment = new Investment("TNAME", 12.2F, "IT", 50);
            testMarket.addInvestment(testInvestment);
            testMarket.addPortfolio(testPortfolio);
            testPortfolio.addInvestments(testInvestment, 1);
            Writer writer = new Writer("./data/testWriterWithFilledMarket.json");
            writer.open();
            writer.write(testMarket);
            writer.close();
            Reader reader = new Reader("./data/testWriterWithFilledMarket.json");
            testMarket = reader.read();
            assertEquals(1, testMarket.getInvestmentList().size());
            assertEquals(1, testMarket.getPortfolioList().size());
            assertEquals(1, testPortfolio.getInvestments().size());
            checkInvestment("TNAME", 12.2F, "IT", 50,
                    testMarket.getInvestmentList().get(0));
            checkPortfolio("NAME1", "IT", 5000,
                    testMarket.getPortfolioList().get(0));
        } catch (IOException e) {
            fail("We could not write to the file and the IOException was thrown");
        }
    }





}

