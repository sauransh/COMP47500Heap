package com.stockX.stockmarket;

import java.util.List;

public interface Heap {
    void insert(Stock stock);
    Stock extractMax();
    boolean isEmpty();
    List<Stock> getHeap();
}
