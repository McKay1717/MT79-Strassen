package org.naritaya.application;

public class Main {
    public static void main(String[] args) {
        // Initialize the matrix handler by giving the SquareMatrix size
        SquareMatrixHandler mHandler = new SquareMatrixHandler(3);

        // Execute algorithms
        mHandler.benchmark();
    }
}

