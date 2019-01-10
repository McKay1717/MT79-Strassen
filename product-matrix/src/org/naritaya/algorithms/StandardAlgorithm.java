package org.naritaya.algorithms;

import org.naritaya.utils.SquareMatrix;

/**
 * Class managing the naive algorithm for (square) matrix multiplication
 */
public class StandardAlgorithm {
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

        // Iterate over rows
        for (int row = 0; row < a_matrix.getSize(); row++) {
            // Store row for this iteration
            float[] currentRow = a_matrix.getRow(row);

            // Iterate over columns
            for (int col = 0; col < b_matrix.getSize(); col++) {
                // Store column for this iteration
                float[] currentCol = b_matrix.getCol(col);

                // Iterate over values
                for (int index = 0; index < result.getSize(); index++) {
                    // Add to the result the product of the values
                    result.addValue(row, col, currentRow[index] * currentCol[index]);
                }
            }
        }

        return result;
    }
}
