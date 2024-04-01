package com.stockX.stockmarket;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap implements Heap {
    private List<Stock> heap;

    public MaxHeap() {
        heap = new ArrayList<>();
    }

    @Override
    public void insert(Stock stock) {
        heap.add(stock);
        heapifyUp(heap.size() - 1);
    }

    @Override
    public Stock extractMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        Stock maxStock = heap.get(0);
        int lastIndex = heap.size() - 1;
        heap.set(0, heap.get(lastIndex));
        heap.remove(lastIndex);

        heapifyDown(0);

        return maxStock;
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public List<Stock> getHeap() {
        return heap;
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && heap.get(index).getVolume() > heap.get(parentIndex).getVolume()) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int largestIndex = index;

        if (leftChildIndex < heap.size() && heap.get(leftChildIndex).getVolume() > heap.get(largestIndex).getVolume()) {
            largestIndex = leftChildIndex;
        }

        if (rightChildIndex < heap.size() && heap.get(rightChildIndex).getVolume() > heap.get(largestIndex).getVolume()) {
            largestIndex = rightChildIndex;
        }

        if (largestIndex != index) {
            swap(index, largestIndex);
            heapifyDown(largestIndex);
        }
    }

    private void swap(int index1, int index2) {
        Stock temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }
}
