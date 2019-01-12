package org.naritaya.utils;

import java.util.Arrays;
import java.util.Random;


/**
 * This abstract class represent a Matrix
 */
public abstract class Matrix {
    protected int width;
    protected int height;
    protected float[][] matrix;

    /**
     * Fill the array representing the matrix with 0
     */
    protected void initialize() {
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                setValue(row, col, 0);
            }
        }
    }

    /**
     * Fill the array representing the matrix with random values
     */
    public void randomize() {
        Random r = new Random();

        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                //setValue(row, col, r.nextFloat() * 100);
                setValue(row, col, r.nextInt(100));
            }
        }
    }

    /**
     * @param matrix A 2D float array
     *
     * Set the given array as the matrix content
     * @throws IllegalArgumentException
     */
    public void set(float[][] matrix) throws IllegalArgumentException {
        if (matrix.length != width)
            throw new IllegalArgumentException();
        this.matrix = matrix;
    }

    public float[][] get() {
        return matrix;
    }

    /**
     * @param row Row index
     * @param col Column index
     * @param value Value to set
     *
     * Set the given value to the value at the given indexes
     * @throws IndexOutOfBoundsException
     */
    public void setValue(int row, int col, float value) throws IndexOutOfBoundsException {
        if (row >= this.height || col >= this.width)
            throw new IndexOutOfBoundsException();
        matrix[row][col] = value;
    }

    /**
     * @param row Row index
     * @param col Column index
     * @param value Value to set
     *
     * Add the given value to the value at the given indexes
     * @throws IndexOutOfBoundsException
     */
    public void addValue(int row, int col, float value) throws IndexOutOfBoundsException {
        if (row >= this.height || col >= this.width)
            throw new IndexOutOfBoundsException();
        matrix[row][col] += value;
    }

    /**
     * @param row Row index
     * @param col Column index
     * @return The values of the given indexes
     * @throws IndexOutOfBoundsException
     */
    public float getValue(int row, int col) throws IndexOutOfBoundsException {
        if (row >= this.height || col >= this.width)
            throw new IndexOutOfBoundsException();
        return matrix[row][col];
    }

    /**
     * @param index Row index
     * @return 1D Array corresponding to the row
     * @throws IndexOutOfBoundsException
     */
    public float[] getRow(int index) throws IndexOutOfBoundsException {
        if (index >= this.height)
            throw new IndexOutOfBoundsException("Index " + index + " out of bound (" + this.height + ")");
        return Arrays.copyOfRange(this.matrix[index], 0, this.width);
    }

    /**
     * @param index Column index
     * @return 1D Array corresponding to the column
     * @throws IndexOutOfBoundsException
     */
    public float[] getCol(int index) throws IndexOutOfBoundsException {
        if (index >= this.width)
            throw new IndexOutOfBoundsException("Index " + index + " out of bound (" + this.width + ")");

        float[] result = new float[this.width];

        for (int i = 0; i < this.width; i++)
            result[i] = this.matrix[i][index];

        return result;
    }

    /**
     * Print the matrix values in the console
     */
    public void print() {
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                System.out.print("|" + getValue(row, col));
            }
            System.out.println("|");
        }
    }
}
