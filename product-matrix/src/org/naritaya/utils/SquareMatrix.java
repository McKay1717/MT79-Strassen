package org.naritaya.utils;

/**
 * This class represent a square matrix
 */
public class SquareMatrix extends Matrix {

    /**
     * @param size Width (and depth) of the matrix
     *
     * Create a square matrix, full of 0
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
}
