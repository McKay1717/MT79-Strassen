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
    }

    /**
     * Execute both algorithms
     */
    public void perform() {
        // A temp var, used to store algorithm results
        SquareMatrix matrix;

        System.out.println("Matrix A:");
            a_matrix.print();
        System.out.println();

        System.out.println("Matrix B:");
            b_matrix.print();
        System.out.println();

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

    public static void benchmark() {
        SquareMatrixHandler mHandler;
        long beginTime, endTime, naiveTime, strassenTime;

        for(int i = 10; i < 1000; i += 100) {
            mHandler = new SquareMatrixHandler(i);

            beginTime = System.nanoTime();
                StandardAlgorithm.perform(mHandler.a_matrix, mHandler.b_matrix);
            endTime = System.nanoTime();
            naiveTime = endTime - beginTime;

            beginTime = System.nanoTime();
                StrassenAlgorithm.perform(mHandler.a_matrix, mHandler.b_matrix);
            endTime = System.nanoTime();
            strassenTime = endTime - beginTime;

            System.out.println("Matrix Size: " + i +
                    " | Naive time: " + TimeUnit.NANOSECONDS.toMillis(naiveTime) + "ms" +
                    " | Strassen time: " + TimeUnit.NANOSECONDS.toMillis(strassenTime) + "ms");

        }
    }
}
