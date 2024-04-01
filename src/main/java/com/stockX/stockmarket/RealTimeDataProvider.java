package com.stockX.stockmarket;

import java.io.IOException;

public interface RealTimeDataProvider {
    Stock fetchRealTimeStockData(String symbol) throws IOException;
}
