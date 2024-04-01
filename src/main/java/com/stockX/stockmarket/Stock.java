package com.stockX.stockmarket;

public class Stock {
    private String symbol;
    private double price;
    private long volume;
    private double changePercentage;

    public Stock(String symbol, double price, long volume, double changePercentage) {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.changePercentage = changePercentage;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public long getVolume() {
        return volume;
    }

    public double getChangePercentage() {
        return changePercentage;
    }

    public double calculateMarketCap() {
        return price * volume;
    }

    public double calculateChangeInValue() {
        return price * changePercentage / 100.0;
    }
}
