package com.stockX.stockmarket;

import java.util.ArrayList;
import java.util.List;

public class PortfolioManager {
    private List<Stock> portfolio;

    public PortfolioManager() {
        this.portfolio = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        this.portfolio.add(stock);
    }

    public double calculateTotalMarketCap() {
        double totalMarketCap = 0;
        for (Stock stock : this.portfolio) {
            totalMarketCap += stock.calculateMarketCap();
        }
        return totalMarketCap;
    }

    public double calculateTotalChangeInValue() {
        double totalChangeInValue = 0;
        for (Stock stock : this.portfolio) {
            totalChangeInValue += stock.calculateChangeInValue();
        }
        return totalChangeInValue;
    }

    public double calculateTotalPortfolioValue() {
        double totalPortfolioValue = 0;
        for (Stock stock : this.portfolio) {
            totalPortfolioValue += stock.getPrice();
        }
        return totalPortfolioValue;
    }

    public void displayPortfolio() {
        for (Stock stock : this.portfolio) {
            System.out.println(stock.getSymbol() + ": $" + stock.getPrice());
        }
    }

    public static void main(String[] args) {
        PortfolioManager portfolioManager = new PortfolioManager();

        Stock AAPL = new Stock("AAPL", 150.25, 1000, 2.5);
        Stock GOOGL = new Stock("GOOGL", 2850.75, 500, -1.2);
        Stock MSFT = new Stock("MSFT", 300.5, 750, -3.3);
        portfolioManager.addStock(AAPL);
        portfolioManager.addStock(GOOGL);
        portfolioManager.addStock(MSFT);

        System.out.println("Total Market Cap: $" + portfolioManager.calculateTotalMarketCap());
        System.out.println("Total Change in Value: $" + portfolioManager.calculateTotalChangeInValue());
        System.out.println("Total Portfolio Value: $" + portfolioManager.calculateTotalPortfolioValue());

        System.out.println("\nPortfolio Stocks:");
        portfolioManager.displayPortfolio();
    }
}
