package org.naritaya.algorithms;

import org.naritaya.utils.SquareMatrix;

import java.util.HashMap;

/**
 *
 */
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

        StrassenAlgorithm sa = new StrassenAlgorithm(a_matrix, b_matrix);
        return sa.perform();
    }

    private SquareMatrix a_matrix;
    private SquareMatrix b_matrix;

    /**
     * @param a_matrix
     * @param b_matrix
     */
    public StrassenAlgorithm(SquareMatrix a_matrix, SquareMatrix b_matrix) {
        this.a_matrix = a_matrix;
        this.b_matrix = b_matrix;
    }


    /**
     * @return A SquareMatrix, result of the Strassen Algorithm
     * @throws IllegalArgumentException
     */
    public SquareMatrix perform() throws IllegalArgumentException {
        // Somethings wrong, matrix aren't the same size
        if (a_matrix.getSize() != b_matrix.getSize())
            throw new IllegalArgumentException();

        // We are going to split our matrix in 4 equal part
        int size = a_matrix.getSize() / 2;
        // In cas of odd sizes
        if (a_matrix.getSize() % 2 == 1) {
            size++;
        }

        // Setup our result var
        SquareMatrix result = new SquareMatrix(a_matrix.getSize());

        // Matrix of size 1, return the product
        if (a_matrix.getSize() == 1) {
            result.setValue(0, 0, a_matrix.getValue(0, 0) * b_matrix.getValue(0, 0));
            return result;
        }

        // Split each matrixes into 4, same sized, matrixes
        HashMap<String, SquareMatrix> a = a_matrix.split();
        HashMap<String, SquareMatrix> b = b_matrix.split();

        // Use given Strassen formulas
        SquareMatrix q1 = new StrassenAlgorithm(a.get("11").minus(a.get("12")), b.get("22")).perform();
        SquareMatrix q2 = new StrassenAlgorithm(a.get("21").minus(a.get("22")), b.get("11")).perform();
        SquareMatrix q3 = new StrassenAlgorithm(a.get("22"), b.get("11").plus(b.get("21"))).perform();
        SquareMatrix q4 = new StrassenAlgorithm(a.get("11"), b.get("12").plus(b.get("22"))).perform();
        SquareMatrix q5 = new StrassenAlgorithm(a.get("11").plus(a.get("22")), b.get("22").minus(b.get("11"))).perform();
        SquareMatrix q6 = new StrassenAlgorithm(a.get("11").plus(a.get("21")), b.get("11").plus(b.get("12"))).perform();
        SquareMatrix q7 = new StrassenAlgorithm(a.get("12").plus(a.get("22")), b.get("21").plus(b.get("22"))).perform();

        // Use Strassen formulas result to create our result matrix
        HashMap<String, SquareMatrix> c = new HashMap<>();
        c.put("11", ((q1.minus(q3)).minus(q5)).plus(q7));
        c.put("12", q4.minus(q1));
        c.put("21", q2.plus(q3));
        c.put("22", ((((new SquareMatrix(q2.getSize())).minus(q2)).minus(q4)).plus(q5)).plus(q6));

        // Assemble the 4 matrixes into one
        for (int row = 0; row < 2; row++) {
            for (int column = 0; column < 2; column++) {
                for (int i = row * size; i < (((row + 1) * size <= result.getSize()) ? (row + 1) * size : result.getSize()); i++)
                    for (int j = column * size; j < (((column + 1) * size <= result.getSize()) ? (column + 1) * size : result.getSize()); j++)
                        result.addValue(i, j, c.get("" + (row + 1) + (column + 1)).getValue(i % size, j % size));
            }
        }

        return result;
    }

}