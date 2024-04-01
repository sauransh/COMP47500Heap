package com.stockX.stockmarket;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class AlphaVantageDataProviderTest {

    @Test
    void testFetchRealTimeStockData() {
        AlphaVantageApidata dataProvider = new AlphaVantageApidata();

        try {
            Stock stock = dataProvider.fetchRealTimeStockData("AAPL");
            assertNotNull(stock);
            assertNotNull(stock.getSymbol());
            assertTrue(stock.getPrice() > 0);
            assertTrue(stock.getVolume() >= 0);
            assertTrue(stock.getChangePercentage() >= 0);
        } catch (IOException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
