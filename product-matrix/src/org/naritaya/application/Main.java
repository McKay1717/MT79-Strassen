package org.naritaya.application;

public class Main {
    public static void main(String[] args) {
        // Initialize the matrix handler by giving the SquareMatrix size
        SquareMatrixHandler mHandler = new SquareMatrixHandler(4);

        // Execute algorithms
        //mHandler.perform();

        // Execute benchmark
        SquareMatrixHandler.benchmark();
    }
}

