package com.stockX.stockmarket;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Heap heap = new MaxHeap();
        RealTimeDataProvider dataProvider = new AlphaVantageApidata();

        StockMarketAnalysisTool analysisTool = new StockMarketAnalysisToolImpl(heap, dataProvider);

        analysisTool.addStock(new Stock("AAPL", 150.25, 100000, 2.5));
        analysisTool.addStock(new Stock("GOOGL", 2800.75, 75000, 1.8));
        analysisTool.addStock(new Stock("AMZN", 3200.50, 90000, 3.2));
        analysisTool.addStock(new Stock("MSFT", 350.60, 80000, 2.0));
        analysisTool.addStock(new Stock("IBM", 330.45, 85000, 1.5));

        try {
            System.out.println("Top 3 Stocks Analysis:");
            Map<String, Double> analysisResults = analysisTool.analyzeTopStocks(3);
            System.out.println("Analysis Results: " + analysisResults);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
