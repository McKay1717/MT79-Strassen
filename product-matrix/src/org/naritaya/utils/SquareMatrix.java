package org.naritaya.utils;

import com.sun.tools.javac.util.ArrayUtils;

import java.util.HashMap;

/**
 * This class represent a square matrix
 */
public class SquareMatrix extends Matrix {

    /**
     * @param size Width (and depth) of the matrix
     *             <p>
     *             Create a square matrix, full of 0
     */
    public SquareMatrix(int size) {
        this.height = size;
        this.width = size;
        this.matrix = new float[height][width];

        initialize();
    }


    /**
     * @return An integer, representing the matrix size
     */
    public int getSize() {
        return this.height;
    }

    /**
     * @return An HashMap with four submatrix
     */
    public HashMap<String, SquareMatrix> split() {
        // Initialize var
        HashMap<String, SquareMatrix> result = new HashMap<>();
        float[][] temp;

        // Matrix will be split in 4 even parts
        int size = getSize() / 2;

        // If the size is odd will fill empty columns and rows with 0
        if ( getSize() % 2 == 1 ) {
            size++;
        }

        // Let's generate our submatrixes
        // (11, 12)
        // (21, 22)
        for(int row = 0; row < 2; row++) {
            for (int column = 0; column < 2; column++) {
                // Init the submatrix
                SquareMatrix m = new SquareMatrix(size);

                // Copy value for the cells into the submatrix
                // i is the row, starting from row * size (where row is the current submatrix [1 or 2])
                // Ternary operator is used to avoid OutOfIndex exceptions
                for (int i = row * size; i < (((row + 1) * size <= getSize()) ? (row + 1) * size : getSize()); i++)
                    // Same things for the column, with var j
                    for (int j = column * size; j < (((column + 1) * size <= getSize()) ? (column + 1) * size : getSize()); j++)
                        // Copy the value
                        m.setValue(i % size, j % size, this.getValue(i, j));
                // Set the matrix and it's key
                result.put("" + (row + 1) + (column + 1), m);
            }
        }
        return result;
    }

    /**
     * @param b Another matrix
     * @return This matrix plus the other one
     */
    public SquareMatrix plus(SquareMatrix b) {
        SquareMatrix result = new SquareMatrix(getSize());

        for(int i = 0; i < getSize(); i++)
            for(int j = 0; j < getSize(); j++)
                result.setValue(i, j, getValue(i, j) + b.getValue(i, j));
        return result;
    }

    /**
     * @param b Another matrix
     * @return This matrix minus the other one
     */
    public SquareMatrix minus(SquareMatrix b) {
        SquareMatrix result = new SquareMatrix(getSize());

        for(int i = 0; i < getSize(); i++)
            for(int j = 0; j < getSize(); j++)
                result.setValue(i, j, getValue(i, j) - b.getValue(i, j));
        return result;
    }
}
