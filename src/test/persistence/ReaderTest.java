package persistence;


import model.Investment;
import model.Market;
import model.Portfolio;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Represents a writer that writes JSON representation of investments and portfolios to file
public class ReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        Reader reader = new Reader("./data/noSuchFile.json");
        try {
            Market market = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMarket() {
        Reader reader = new Reader("./data/testWriterEmptyMarket.json");
        try {
            Market market = reader.read();
            assertEquals(0, market.getPortfolioList().size());
            assertEquals(0, market.getInvestmentList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

//    @SuppressWarnings("checkstyle:MethodLength")
//    @Test
//    void testReaderWithFilledMarket() {
//        try {
//            Market testMarket = new Market();
//            Portfolio testPortfolio = new Portfolio("NAME1", "IT", 5000);
//            Portfolio testPortfolio2 = new Portfolio("NAME2", "ENERGY", 4000);
//            Portfolio testPortfolio3 = new Portfolio("NAME3", "FINANCE", 3000);
//
//            Investment testInvestment = new Investment("TNAME", 12.2F, "IT",
//                    50);
//            Investment testInvestment2 = new Investment("TNAME2", 6.5F, "ENERGY",
//                    40);
//            Investment testInvestment3 = new Investment("TNAME3", 12.8F, "FINANCE",
//                    30);
//
//            testMarket.addInvestment(testInvestment);
//            testMarket.addInvestment(testInvestment2);
//            testMarket.addInvestment(testInvestment3);
//
//            testMarket.addPortfolio(testPortfolio);
//            testMarket.addPortfolio(testPortfolio2);
//            testMarket.addPortfolio(testPortfolio3);
//
//            testPortfolio.addInvestments(testInvestment, 1);
//            testPortfolio.addInvestments(testInvestment2, 1);
//
//
//            // Write it first
//            Writer writer = new Writer("./data/testReaderWithFilledMarket.json");
//            writer.open();
//            writer.write(testMarket);
//            writer.close();
//
//            // Then, read it to test if it is correct
//            Reader reader = new Reader("./data/testReaderWithFilledMarket.json");
//
//            testMarket = reader.read();
//            assertEquals(3, testMarket.getInvestmentList().size());
//            assertEquals(3, testMarket.getPortfolioList().size());
//            assertEquals(2, testPortfolio.getInvestments().size());
//
//            checkInvestment("TNAME", 12.2F, "IT", 50,
//                    testMarket.getInvestmentList().get(0));
//
//            checkInvestment("TNAME2", 6.5F, "ENERGY", 40,
//                    testMarket.getInvestmentList().get(1));
//
//            checkInvestment("TNAME3", 12.8F, "FINANCE", 30,
//                    testMarket.getInvestmentList().get(2));
//
//            checkPortfolio("NAME1", "IT", 5000,
//                    testMarket.getPortfolioList().get(0));
//
//            checkPortfolio("NAME2", "ENERGY", 4000,
//                    testMarket.getPortfolioList().get(1));
//
//            checkPortfolio("NAME3", "FINANCE", 3000,
//                    testMarket.getPortfolioList().get(2));
//
//        } catch (IOException e) {
//            fail("We could not write to the file and the IOException was thrown");
//        }
//    }

    @Test
    void testReaderWithFilledMarket() {
        try {
            Market testMarket = new Market();
            Portfolio testPortfolio = new Portfolio("NAME1", "IT", 5000);
            Investment testInvestment = new Investment("TNAME", 12.2F, "IT", 50);
            testMarket.addInvestment(testInvestment);
            testMarket.addPortfolio(testPortfolio);
            testPortfolio.addInvestments(testInvestment, 1);
            Writer writer = new Writer("./data/testReaderWithFilledMarket.json");
            writer.open();
            writer.write(testMarket);
            writer.close();
            Reader reader = new Reader("./data/testReaderWithFilledMarket.json");
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


