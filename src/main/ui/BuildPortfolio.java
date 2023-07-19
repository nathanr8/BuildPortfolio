package ui;

import model.Investment;
import model.Portfolio;

import java.util.ArrayList;
import java.util.Scanner;

public class BuildPortfolio {
    private Investment google;
    private Investment blackrock;
    private Investment tesla;
    private Investment nextEra;
    private Investment pfizer;
    private Portfolio portfolioA;
    private Investment newInvestment;
    private ArrayList<Investment> investmentList;
    private ArrayList<Portfolio> portfolioList;
    private Scanner input;

    // EFFECT: runs the portfolio builder application
    public BuildPortfolio() {
        runBuilder();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBuilder() {
        initInvestments();
        initPortfolios();
        this.investmentList = new ArrayList<>();
        this.portfolioList = new ArrayList<>();
        investmentList.add(google);
        investmentList.add(blackrock);
        investmentList.add(tesla);
        investmentList.add(nextEra);
        investmentList.add(pfizer);
        portfolioList.add(portfolioA);
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
        google = new Investment("Google", 8.1F, "IT", 200);
        blackrock = new Investment("Blackrock", 6.2F, "Financial", 150);
        tesla = new Investment("Tesla", 12.2F, "Energy", 300);
        nextEra = new Investment("NextEra", 7.2F, "Utilities", 50);
        pfizer = new Investment("Pfizer", 5.7F, "Healthcare", 100);
        //System.out.println();
        input = new Scanner(System.in);
        input.useDelimiter("\n");

    }

    // MODIFIES: this
    // EFFECTS: initializes portfolio
    private void initPortfolios() {
        portfolioA = new Portfolio("PortfolioA", "Technology", 10000);
        portfolioA.addInvestments(google, 2);
        portfolioA.addInvestments(blackrock, 2);
        portfolioA.addInvestments(tesla, 2);
        portfolioA.addInvestments(nextEra, 2);
        portfolioA.addInvestments(pfizer, 2);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tn -> New Investment");
        System.out.println("\ti -> View Investments");
        System.out.println("\ta -> New Portfolio");
        System.out.println("\tp -> View Portfolios");
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
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: display menu for adding investment, deleting investment
    // adding portfolio, and deleting portfolio
    private void portfolioDisplayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tb -> New Portfolio");
        System.out.println("\tc -> Delete Portfolio");
        System.out.println("\td -> Add investment to portfolio");
        System.out.println("\te -> Delete investment from portfolio");
        System.out.println("\tp -> View Portfolios");
        System.out.println("\ts -> View Portfolio's investments");
        System.out.println("\tq -> quit");
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
        Portfolio p = lookupPortfolioByName(portfolioName);

        System.out.printf("| %-10s | %-8s | %-15s | %-20s | %-12s  |%n", "Name", "Quantity", "Purchase Price", "Expected Return (%)", "Industry");
        System.out.printf("| %-74s |%n", "------------------------------------------------------------------------------");
        for (Investment i : p.getInvestments()) {
            System.out.printf("| %-10s | %-8s | %-15s | %-20s | %-13s |%n", i.getInvestmentname(), i.getPrice(), i.getPrice(),
                    i.getReturnPercentage(), i.getSector());
        }

    }

    //MODIFIES: this
    //EFFECTS: deletes investment from portfolio's list of investments
    private void deleteInvestmentFromPortfolio() {
        System.out.println("Portfolio name you would like to delete investment from?");
        String portfolioName = input.next();
        Portfolio p = lookupPortfolioByName(portfolioName);

        System.out.println("Name of investment you would like to delete?");
        String investmentName = input.next();

        System.out.println("How many of these would you like to delete?");
        int quantity = Integer.parseInt(input.next());

        Investment i = lookupInvestmentByName(investmentName);

        p.removeInvestment(i, quantity);

        viewPortfolios();
    }

    //REQUIRES: portfolio is already constructed
    //MODIFIES: this
    //EFFECTS: removes portfolio from portfolioList
    private void deletePortfolio() {
        System.out.println("Name of portfolio you would like to delete?");
        String portfolioName = input.next();
        Portfolio port = lookupPortfolioByName(portfolioName);
        this.portfolioList.remove(port);
        viewPortfolios();
    }


    // EFFECTS: displays all portfolios currently made
    private void viewPortfolios() {
        System.out.println("Currently available portfolios: ");

        System.out.printf("| %-10s | %-20s | %-20s | %-25s | %-18s | %n", "Name", "Capital Invested ($)",
                "Expected Return (%)", "Strong Economic Sector", "Available Capital");
        System.out.printf("| %-104s |%n", "-------------------------------------------------------------------------"
            + "--------------------------------");
        for (Portfolio p : portfolioList) {
            System.out.printf("| %-10s | %-20s | %-20s | %-25s | %-18s |%n", p.getPortfolioName(), p.getCapital(),
                    p.getPortfolioName(), p.getPreferredSector(), p.getAvailableCapital());
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

    // MODIFIES: this
    // EFFECTS: creates a portfolio, adds it to portfolioList
    private void newPortfolio() {
        Portfolio newPortfolio;
        newPortfolio = new Portfolio(null, null, 1);

        System.out.print("Enter name of portfolio: ");
        String portfolioName = input.next();
        newPortfolio.setPortfolioName(portfolioName);

        System.out.print("Enter the strong economic sector in this portfolio: ");
        String sector = input.next();
        newPortfolio.setPreferredSector(sector);

        System.out.print("Enter the starting amount of capital in $: ");
        Integer capital = Integer.valueOf(input.next());

        if (capital > 1000) {
            newPortfolio.setCapital(capital);
            newPortfolio.setAvailableCapital(capital);
            portfolioList.add(newPortfolio);
            System.out.println("");
            System.out.println("Portfolio successfully made!");
        } else {
            System.out.println("Portfolio cannot start with capital less than $1000... \n");
        }
    }

    //MODIFIES: this
    //EFFECTS: adds investment to portfolio's list of investments
    private void addInvestmentToPortfolio() {
        Investment selectedInvestment;
        Portfolio selectedPortfolio;
        System.out.println("Portfolio name you would like to add investment to: ");
        String portfolioName = input.next();
        Portfolio p = lookupPortfolioByName(portfolioName);

        System.out.println("Name of investment you would like to add: ");
        String investmentName = input.next();

        System.out.println("How many of these would you like to purchase: ");
        int quantity = Integer.parseInt(input.next());

        Investment i = lookupInvestmentByName(investmentName);

        p.addInvestments(i, quantity);

        viewPortfolios();
    }


    // EFFECTS: displays all investments available in the market
    private void viewInvestments() {
        System.out.println("Currently available investments in the market: ");
        System.out.printf("| %-10s | %-6s | %-20s | %-12s |%n", "Name", "Price", "Expected Return (%)", "Industry");
        System.out.printf("| %-54s |%n", "---------------------------------------------------------");
        for (Investment i : investmentList) {
            System.out.printf("| %-10s | %-6s | %-20s | %-12s |%n", i.getInvestmentname(), i.getPrice(),
                    i.getReturnPercentage(), i.getSector());
        }
    }

    // MODIFIES: this
    // EFFECTS: creates an investment, adds it to investmentList
    private void newInvestment() {
        Investment newInvestment;
        newInvestment = new Investment(null, 0F, null, 1);

        System.out.print("Enter name of investment: ");
        String investmentName = input.next();
        newInvestment.setInvestmentname(investmentName);

        System.out.print("Enter expected return in a percentage: ");
        float investmentPercent = Float.parseFloat(input.next());
        newInvestment.setReturnPercentage(investmentPercent);

        System.out.print("Enter the economic sector: ");
        String investmentSector = input.next();
        newInvestment.setSector(investmentSector);

        System.out.print("Enter the initial purchase price: ");
        Integer investmentPrice = Integer.valueOf(input.next());

        if (investmentPrice > 0) {
            newInvestment.setPrice(investmentPrice);
            investmentList.add(newInvestment);
            System.out.println("");
            System.out.println("Investment added successfully");
        } else {
            System.out.println("Investment cannot start with negative price... \n");
        }
    }

    // REQUIRES: investmentName is the name of an existing Investment
    // EFFECTS: returns Investment object that has name investmentName
    public Investment lookupInvestmentByName(String investmentName) {
        Investment inv = null;
        for (Investment i : investmentList) {
            if (i.getInvestmentname().equalsIgnoreCase(investmentName)) {
                inv = i;
            }
        }
        return inv;
    }

    // REQUIRES: portfolioName is the name of an existing portfolio
    // EFFECTS: returns portfolio that has name portfolioName
    public Portfolio lookupPortfolioByName(String portfolioName) {
        Portfolio port = null;
        for (Portfolio p : portfolioList) {
            if (p.getPortfolioName().toLowerCase().equals(portfolioName.toLowerCase())) {
                port = p;
            }
        }
        return port;
    }

}
