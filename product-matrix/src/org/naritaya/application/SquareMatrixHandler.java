package org.naritaya.application;

import org.naritaya.algorithms.StandardAlgorithm;
import org.naritaya.algorithms.StrassenAlgorithm;
import org.naritaya.utils.SquareMatrix;

import java.util.concurrent.TimeUnit;

/**
 * Handle SquareMatrix algorithm and benchmark
 */
public class SquareMatrixHandler {
    private SquareMatrix a_matrix;
    private SquareMatrix b_matrix;

    /**
     * @param size Matrix size
     *
     * Initialize the two matrix needed and print them
     */
    public SquareMatrixHandler(int size) {
        // Create a new SquareMatrix and fill it with random values
        a_matrix = new SquareMatrix(size);
        a_matrix.randomize();

        b_matrix = new SquareMatrix(size);
        b_matrix.randomize();

        System.out.println("Matrix A:");
        a_matrix.print();
        System.out.println();

        System.out.println("Matrix B:");
        b_matrix.print();
        System.out.println();
    }

    /**
     * Execute and benchmark both algorithms
     */
    public void benchmark() {
        // A temp var, used to store algorithm results
        SquareMatrix matrix;

        // Execute naive algorithm and record elapsed time
        System.out.println("Using Standard Algorithm:");
        long beginTime = System.nanoTime();
        matrix = StandardAlgorithm.perform(a_matrix, b_matrix);
        long endTime = System.nanoTime();

        // Display algorithm result and elapsed time
        matrix.print();
        System.out.println("Calculation time: " + TimeUnit.NANOSECONDS.toMicros(endTime - beginTime) + "µs");

        // Execute Strassen algorithm and record elapsed time
        System.out.println("Using Strassen Algorithm:");
        beginTime = System.nanoTime();
        matrix = StrassenAlgorithm.perform(a_matrix, b_matrix);
        endTime = System.nanoTime();

        // Display algorithm result and elapsed time
        matrix.print();
        System.out.println("Calculation time: " + TimeUnit.NANOSECONDS.toMicros(endTime - beginTime) + "µs");

    }
}
