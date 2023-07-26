package ui;

import model.Investment;
import model.Portfolio;
import model.Market;
import persistence.Reader;
import persistence.Writer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Portfolio Builder application
public class BuildPortfolio2 {
    private Scanner input;
    private Market myMarket;

    private static final String JSON_STORAGE = "./data/market.json";
    private Writer jsonWriter;
    private Reader jsonReader;

    // EFFECTS: runs the portfolio builder application
    public BuildPortfolio2() throws FileNotFoundException {
        myMarket = new Market();
        initInvestments();
        initPortfolios();
        input = new Scanner(System.in);
        jsonWriter = new Writer(JSON_STORAGE);
        jsonReader = new Reader(JSON_STORAGE);
        runBuilder();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBuilder() {

        boolean keepGoing = true;
        String command = null;
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes investments
    private void initInvestments() {
        myMarket.addInitInvestments();
        input = new Scanner(System.in);
        input.useDelimiter("\n");

    }

    // MODIFIES: this
    // EFFECTS: initializes portfolios
    private void initPortfolios() {
        myMarket.addInitPortfolios();
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tn -> New Investment");
        System.out.println("\ti -> View Investments");
        System.out.println("\ta -> New Portfolio");
        System.out.println("\tp -> View Portfolios");
        System.out.println("\tz -> Save market to file");
        System.out.println("\ty -> Load market from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES:this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("n")) {
            newInvestment();
        } else if (command.equals("i")) {
            viewInvestments();
        } else if (command.equals("a")) {
            newPortfolio();
        } else if (command.equals("p")) {
            viewPortfolios();
        } else if (command.equals("z")) {
            saveMarket();
        } else if (command.equals("y")) {
            loadMarket();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: display menu for portfolio section
    private void portfolioDisplayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tb -> New Portfolio");
        System.out.println("\tc -> Delete Portfolio");
        System.out.println("\td -> Add investment to portfolio");
        System.out.println("\te -> Delete investment from portfolio");
        System.out.println("\tp -> View Portfolios");
        System.out.println("\ts -> View Portfolio's investments");
        System.out.println("\tq -> back");
    }

    // MODIFIES:this
    // EFFECTS: processes user command in portfolio menu
    private void processCommand2(String command2) {
        if (command2.equals("b")) {
            newPortfolio();
        } else if (command2.equals("c")) {
            deletePortfolio();
        } else if (command2.equals("d")) {
            addInvestmentToPortfolio();
        } else if (command2.equals("e")) {
            deleteInvestmentFromPortfolio();
        } else if (command2.equals("p")) {
            viewPortfolios();
        } else if (command2.equals("s")) {
            viewPortfoliosInvestments();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: displays list of selected portfolio's investments
    private void viewPortfoliosInvestments() {
        System.out.println("Enter name of portfolio to view it's investments:  ");
        String portfolioName = input.next();
        Portfolio p = myMarket.lookupPortfolioByName(portfolioName);

        System.out.printf("| %-10s | %-15s | %-20s | %-12s  |%n", "Name", "Purchase Price",
                "Expected Return (%)", "Industry");
        System.out.printf("| %-66s |%n", "-----------------------------------------" + "-"
                + "-------------------------");
        for (Investment i : p.getInvestments()) {
            System.out.printf("| %-10s | %-15s | %-20s | %-13s |%n", i.getInvestmentname(), i.getPrice(),
                    i.getReturnPercentage(), i.getSector());
        }

    }

    //REQUIRES: portfolio has at least one of specified investment
    //MODIFIES: this
    //EFFECTS: deletes investment from portfolio's list of investments
    private void deleteInvestmentFromPortfolio() {
        System.out.println("Portfolio name you would like to delete investment from?");
        String portfolioName = input.next();
        Portfolio p = myMarket.lookupPortfolioByName(portfolioName);

        System.out.println("Name of investment you would like to delete?");
        String investmentName = input.next();

        System.out.println("How many of these would you like to delete?");
        int quantity = Integer.parseInt(input.next());

        Investment i = myMarket.lookupInvestmentByName(investmentName);

        p.removeInvestment(i, quantity);

        viewPortfolios();
    }

    //REQUIRES: portfolio is already constructed
    //MODIFIES: this
    //EFFECTS: removes portfolio from portfolioList
    private void deletePortfolio() {
        System.out.println("Name of portfolio you would like to delete?");
        String portfolioName = input.next();
        Portfolio port = myMarket.lookupPortfolioByName(portfolioName);
        myMarket.deletePortfolio(port);
        viewPortfolios();
    }

    // EFFECTS: displays all portfolios currently made
    private void viewPortfolios() {
        System.out.println("Currently available portfolios: ");

        System.out.printf("| %-10s | %-20s | %-20s | %-25s | %-18s | %n", "Name", "Capital Invested ($)",
                "Expected Return (%)", "Strong Economic Sector", "Available Capital");
        System.out.printf("| %-104s |%n", "-------------------------------------------------------------------------"
                + "--------------------------------");
        for (Portfolio p : myMarket.getPortfolioList()) {
            System.out.printf("| %-10s | %-20s | %-20s | %-25s | %-18s |%n", p.getPortfolioName(),
                    p.getPortfolioCapital(), p.calculateReturnAmountPercent(), p.getPreferredSector(),
                    p.getAvailableCapital());
        }
        boolean keepGoing2 = true;
        String command2 = null;
        while (keepGoing2) {
            portfolioDisplayMenu();
            command2 = input.next();
            command2 = command2.toLowerCase();

            if (command2.equals("q")) {
                keepGoing2 = false;
            } else {
                processCommand2(command2);
            }
        }
    }



    //REQUIRES: portfolioName is not already in use
    // MODIFIES: this
    // EFFECTS: creates a portfolio, adds it to portfolioList
    private void newPortfolio() {
        System.out.print("Enter name of portfolio: ");
        String portfolioName = input.next();

        System.out.print("Enter the strong economic sector in this portfolio: ");
        String sector = input.next();

        System.out.print("Enter the starting amount of capital in $: ");
        Integer capital = Integer.valueOf(input.next());

        if (capital > 1000) {
            myMarket.newPortfolio(portfolioName, sector, capital);
            System.out.println(" ");
            System.out.println("Portfolio successfully made!");
        } else {
            System.out.println("Portfolio cannot start with capital less than $1000... \n");
        }
    }

    //MODIFIES: this
    //EFFECTS: adds investment to portfolio's list of investments
    private void addInvestmentToPortfolio() {
        System.out.println("Portfolio name you would like to add investment to: ");
        String portfolioName = input.next();
        Portfolio p = myMarket.lookupPortfolioByName(portfolioName);

        System.out.println("Name of investment you would like to add: ");
        String investmentName = input.next();

        System.out.println("How many of these would you like to purchase: ");
        int quantity = Integer.parseInt(input.next());

        Investment i = myMarket.lookupInvestmentByName(investmentName);

        p.addInvestments(i, quantity);

        viewPortfolios();
    }

    // EFFECTS: displays all investments available in the market
    private void viewInvestments() {
        System.out.println("Currently available investments in the market: ");
        System.out.printf("| %-10s | %-6s | %-20s | %-12s |%n", "Name", "Price", "Expected Return (%)", "Industry");
        System.out.printf("| %-54s |%n", "---------------------------------------------------------");
        // EFFECTS: displays all investments available in the market
        for (Investment i : myMarket.getInvestmentList()) {
            System.out.printf("| %-10s | %-6s | %-20s | %-12s |%n", i.getInvestmentname(), i.getPrice(),
                    i.getReturnPercentage(), i.getSector());
        }
    }


    //REQUIRES: investmentName is not already in market
    // MODIFIES: this
    // EFFECTS: creates an investment, adds it to investmentList
    private void newInvestment() {
        System.out.print("Enter name of investment: ");
        String investmentName = input.next();


        System.out.print("Enter expected return in a percentage: ");
        float investmentPercent = Float.parseFloat(input.next());


        System.out.print("Enter the economic sector: ");
        String investmentSector = input.next();


        System.out.print("Enter the initial purchase price: ");
        Integer investmentPrice = Integer.valueOf(input.next());

        if (investmentPrice > 0) {
            myMarket.newInvestment(investmentName, investmentPercent, investmentSector, investmentPrice);
            System.out.println(" ");
            System.out.println("Investment added successfully");
        } else {
            System.out.println("Investment cannot start with negative price... \n");
        }
    }


    // EFFECTS: saves the market to file
    private void saveMarket() {
        try {
            jsonWriter.open();
            jsonWriter.write(myMarket);
            jsonWriter.close();
            System.out.println("Saved " + "market" + " to " + JSON_STORAGE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads market from file
    private void loadMarket() {
        try {
            myMarket = jsonReader.read();
            System.out.println("Loaded " + "market" + " from " + JSON_STORAGE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORAGE);
        }
    }

}

