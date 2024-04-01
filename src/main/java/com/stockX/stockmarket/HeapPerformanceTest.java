package com.stockX.stockmarket;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class HeapPerformanceTest {

    public static void main(String[] args) {
        int[] ipsize = {1000, 10000, 100000}; // input size for benchmarck
        StringBuilder resultsBuilder = new StringBuilder();
        resultsBuilder.append("Size,Insertion Time (ns),Removal Time (ns),Heapify Time (ns)\n");

        for (int size : ipsize) {
            long starttime, endtime, elapsetime;

            Heap heap = new MaxHeap();
            int[] randdata = generateRandomData(size);

            starttime = System.nanoTime();
            for (int num : randdata) {
                Stock stock = new Stock("DUMMY", num, num, num);
                heap.insert(stock);
            }
            endtime = System.nanoTime();
            elapsetime = endtime - starttime;
            resultsBuilder.append(size).append(",").append(elapsetime).append(",");

            starttime = System.nanoTime();
            while (!heap.isEmpty()) {
                heap.extractMax();
            }
            endtime = System.nanoTime();
            elapsetime = endtime - starttime;
            resultsBuilder.append(elapsetime).append(",");

            starttime = System.nanoTime();
            heapify(randdata);
            endtime = System.nanoTime();
            elapsetime = endtime - starttime;
            resultsBuilder.append(elapsetime).append("\n");
        }

        try {
            FileWriter writer = new FileWriter("benchmark_results.csv");
            writer.write(resultsBuilder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] generateRandomData(int size) {
        int[] data = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt();
        }
        return data;
    }

    private static void heapify(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(array, i, n);
        }
    }

    private static void heapifyDown(int[] array, int index, int heapSize) {
        int largest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < heapSize && array[leftChild] > array[largest]) {
            largest = leftChild;
        }

        if (rightChild < heapSize && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        if (largest != index) {
            int temp = array[index];
            array[index] = array[largest];
            array[largest] = temp;
            heapifyDown(array, largest, heapSize);
        }
    }

}
