package com.matrixcalc.linearalgebracalculatorv2.tools;

import java.util.Arrays;

public class Operations {

    private static boolean validAddSubMatrices(double[][] a, double[][] b) {
        if (a.length != b.length) return false;
        if (a.length == 0) return false;
        if (a[0].length != b[0].length) return false;
        return true;
    }

    public static double[][] add(double[][] a, double[][] b) {
        if (!validAddSubMatrices(a, b)) throw new IllegalArgumentException("Inavlid matrices for chosen opperation");

        int rows = a.length;
        int cols = a[0].length;

        System.out.println("Cols = " + cols);
        System.out.println("Rows = " + rows);

        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }

        System.out.println(Arrays.deepToString(result));

        return result;
    }

    public static double[][] subtract(double[][] a, double[][] b) {
        if (!validAddSubMatrices(a, b)) throw new IllegalArgumentException("Inavlid matrices for chosen opperation");

        int rows = a.length;
        int cols = a[0].length;

        System.out.println("Cols = " + cols);
        System.out.println("Rows = " + rows);

        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }

        System.out.println(Arrays.deepToString(result));

        return result;
    }

    public static boolean validMultiplyMatrices(double[][] A, double[][] B) {
        if (A.length == 0 || B.length == 0) return false;
        System.out.println("A Col's = " + A[0].length);
        System.out.println("B row's = " + B.length);
        return A[0].length == B.length;
    }

    public static double dot(double[] V, double[] Q) {
        double sum = 0;
        for(int i = 0; i < V.length; i++) {
            sum+=V[i]*Q[i];
        }
        return sum;
    }
    public static double[][] multiply(double[][] A, double[][] B) {
        if (!validMultiplyMatrices(A, B)) throw new IllegalArgumentException("Inavlid matrices for chosen opperation");

        int rows = A.length;
        int cols = B[0].length;

        int n = A[0].length;

        double[][] M = new double[rows][cols];

        System.out.println("rows = " + rows);
        System.out.println("cols = " + cols);

        for(int i = 0; i < rows; i++) {
            double[] row = A[i];
            for(int j = 0; j < cols; j++) {
                int finalJ = j;
                double[] col = Arrays.stream(B).mapToDouble(arr -> arr[finalJ]).toArray();
                System.out.println("Col: " + Arrays.toString(col) );
                M[i][j] = dot(row, col);
            }
        }

        System.out.println(Arrays.deepToString(M));
        return M;
    }

    public static double[][] rref(double[][] matrix) {
        double[][] rref = new double[matrix.length][];
        for (int i = 0; i < matrix.length; i++)
            rref[i] = Arrays.copyOf(matrix[i], matrix[i].length);

        int r = 0;
        for (int c = 0; c < rref[0].length && r < rref.length; c++) {
            int j = r;
            for (int i = r + 1; i < rref.length; i++)
                if (Math.abs(rref[i][c]) > Math.abs(rref[j][c]))
                    j = i;
            if (Math.abs(rref[j][c]) < 0.00001)
                continue;

            double[] temp = rref[j];
            rref[j] = rref[r];
            rref[r] = temp;

            double s = 1.0 / rref[r][c];
            for (j = 0; j < rref[0].length; j++)
                rref[r][j] *= s;
            for (int i = 0; i < rref.length; i++) {
                if (i != r) {
                    double t = rref[i][c];
                    for (j = 0; j < rref[0].length; j++)
                        rref[i][j] -= t * rref[r][j];
                }
            }
            r++;
        }

        return rref;
    }

    public static double determinant(double[][] matrix) {
        if (matrix.length != matrix[0].length)
            throw new IllegalStateException("invalid dimensions");

        if (matrix.length == 2)
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        double det = 0;
        for (int i = 0; i < matrix[0].length; i++)
            det += Math.pow(-1, i) * matrix[0][i]
                    * determinant(minor(matrix, 0, i));
        return det;
    }

    public static double[][] inverse(double[][] matrix) {

        if(matrix.length != matrix[0].length) throw new IllegalArgumentException("Matrix is not square");

        double[][] inverse = new double[matrix.length][matrix.length];

        // minors and cofactors
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                inverse[i][j] = Math.pow(-1, i + j)
                        * determinant(minor(matrix, i, j));

        // adjugate and determinant
        double det = 1.0 / determinant(matrix);
        for (int i = 0; i < inverse.length; i++) {
            for (int j = 0; j <= i; j++) {
                double temp = inverse[i][j];
                inverse[i][j] = inverse[j][i] * det;
                inverse[j][i] = temp * det;
            }
        }

        return inverse;
    }

    public static double[][] minor(double[][] matrix, int row, int column) {
        double[][] minor = new double[matrix.length - 1][matrix.length - 1];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; i != row && j < matrix[i].length; j++)
                if (j != column)
                    minor[i < row ? i : i - 1][j < column ? j : j - 1] = matrix[i][j];
        return minor;
    }


    public static double[][] transpose(double[][] matrix) {
        double[][] transpose = new double[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                transpose[j][i] = matrix[i][j];
        return transpose;
    }
}
