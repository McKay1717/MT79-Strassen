package org.naritaya.algorithms;

import org.naritaya.utils.SquareMatrix;

//TODO: Implement this algorithm
public class StrassenAlgorithm {
    /**
     * @param a_matrix SquareMatrix
     * @param b_matrix SquareMatrix
     * @return The result of the multiplication of the given parameters
     * @throws IndexOutOfBoundsException
     */
    public static SquareMatrix perform(SquareMatrix a_matrix, SquareMatrix b_matrix) throws IndexOutOfBoundsException {
        if (a_matrix.getSize() != b_matrix.getSize())
            throw new IndexOutOfBoundsException("Matrix sizes are different");

        // Initialize a new matrix (same size as given one)
        SquareMatrix result = new SquareMatrix(a_matrix.getSize());

        return result;
    }
}
