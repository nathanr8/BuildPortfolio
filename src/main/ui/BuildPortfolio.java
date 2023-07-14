package ui;

import model.Investment;
import model.Portfolio;

import java.util.ArrayList;
import java.util.List;
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
        //portfolioA.printInvestments();
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

    private void processCommand2(String command2) {
    }

    // EFFECTS: displays all portfolios currently made
    private void viewPortfolios() {
        System.out.println("Currently available portfolios: ");

        for (Portfolio j : portfolioList) {
            System.out.println(j.getPortfolioname());
        }
        boolean keepGoing2 = true;
        String command2 = null;
        while (keepGoing2) {
            portfolioDisplayMenu();
            command2 = input.next();
            command2 = command.toLowerCase();

            if (command2.equals("q")) {
                keepGoing2 = false;
            } else {
                processCommand2(command2);
            }
        }

    }

    //EFFECTS: display menu for adding investment, deleting investment
    // adding portfolio, and deleting portfolio
    private void portfolioDisplayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tb -> New Portfolio");
        System.out.println("\tc -> Delete Portfolio");
        System.out.println("\td -> New Portfolio");
        System.out.println("\te -> View Portfolios");
        System.out.println("\tq -> quit");
    }
    }

    // MODIFIES: this
    // EFFECTS: creates a portfolio, adds it to portfolioList
    private void newPortfolio() {
        Portfolio newPortfolio;
        newPortfolio = new Portfolio(null, null, 1);

        System.out.print("Enter name of portfolio: ");
        String portfolioName = input.next();
        newPortfolio.setPortfolioname(portfolioName);

        System.out.print("Enter the strong economic sector in this portfolio: ");
        String sector = input.next();
        newPortfolio.setPreferredSector(sector);

        System.out.print("Enter the starting amount of capital in $: ");
        Integer capital = Integer.valueOf(input.next());

        if (capital > 1000) {
            newPortfolio.setCapital(capital);
            portfolioList.add(newPortfolio);
            System.out.println("");
            System.out.println("Portfolio successfully made!");
        } else {
            System.out.println("Portfolio cannot start with capital less than $1000... \n");
        }
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
}
