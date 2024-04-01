package com.stockX.stockmarket;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StockMarketAnalysisToolTest {

    @Test
    void testStockMarketAnalysisTool() {
        Heap heap = new MaxHeap();
        RealTimeDataProvider dataProvider = new AlphaVantageApidata();

        StockMarketAnalysisTool analysisTool = new StockMarketAnalysisToolImpl(heap, dataProvider);

        analysisTool.addStock(new Stock("AAPL", 150.25, 100000, 2.5));
        analysisTool.addStock(new Stock("GOOGL", 2800.75, 75000, 1.8));
        analysisTool.addStock(new Stock("AMZN", 3200.50, 90000, 3.2));
        analysisTool.addStock(new Stock("MSFT", 350.60, 80000, 2.0));
        analysisTool.addStock(new Stock("FB", 330.45, 85000, 1.5));

        assertDoesNotThrow(() -> analysisTool.analyzeTopStocks(3));
    }
}
