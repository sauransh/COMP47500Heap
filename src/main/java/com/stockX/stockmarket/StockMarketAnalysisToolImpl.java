package com.stockX.stockmarket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StockMarketAnalysisToolImpl implements StockMarketAnalysisTool {
    private Heap heap;
    private RealTimeDataProvider dataProvider;

    public StockMarketAnalysisToolImpl(Heap heap, RealTimeDataProvider dataProvider) {
        this.heap = heap;
        this.dataProvider = dataProvider;
    }

    @Override
    public void addStock(Stock stock) {
        heap.insert(stock);
    }

    @Override
    public Map<String, Double> analyzeTopStocks(int topN) throws IOException, InterruptedException {
        Map<String, Double> analysisResults = new HashMap<>();
        for (int i = 0; i < topN && !heap.isEmpty(); i++) {
            Stock maxStock = heap.extractMax();
            Stock realTimeStockData = dataProvider.fetchRealTimeStockData(maxStock.getSymbol());
            double marketCap = realTimeStockData.calculateMarketCap();
            double changeInValue = realTimeStockData.calculateChangeInValue();
            analysisResults.put(realTimeStockData.getSymbol(), marketCap);
            System.out.println("Stock: " + realTimeStockData.getSymbol() +
                    ", Market Cap: $" + marketCap +
                    ", Change in Value: $" + changeInValue);
        }
        return analysisResults;
    }
}
