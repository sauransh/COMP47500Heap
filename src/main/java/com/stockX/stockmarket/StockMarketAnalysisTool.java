package com.stockX.stockmarket;

import java.io.IOException;
import java.util.Map;

public interface StockMarketAnalysisTool {
    void addStock(Stock stock);
    Map<String, Double> analyzeTopStocks(int topN) throws IOException, InterruptedException;
}
