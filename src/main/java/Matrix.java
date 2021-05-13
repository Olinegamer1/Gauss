import java.util.Arrays;

public class Matrix {
    private static final String NO_ONE_COLUMN = "Matrix hasn't column";
    private static final String INCORRECT_MATRIX = "Incorrect matrix";
    private static final String INCORRECT_VECTOR = "Incorrect vector";
    private final double[][] data;

    public Matrix(double[][] data) {
        this.data = copy(data);
    }

    public double[] gaussSolve(Vector vector) {
        double[][] tempData = copy(data);
        double[] tempVector = Arrays.copyOf(vector.getData(), vector.size());

        if (tempVector.length != getRowDimension()) {
            throw new IllegalArgumentException(INCORRECT_VECTOR);
        }

        convertToTriangle(tempVector, tempData);
        return inverseStepGauss(tempVector, tempData);
    }

    private double[] inverseStepGauss(double[] vector, double[][] tempData) {
        double[] result = new double[getColumnDimension()];
        for (int i = getRowDimension() - 1; i >= 0; i--) {
            double temp = vector[i] / tempData[i][i];
            for (int j = i + 1; j < getColumnDimension(); j++) {
                result[i] -= (tempData[i][j] * result[j]) / tempData[i][i];
            }
            result[i] += temp;
        }
        return result;
    }

    private void convertToTriangle(double[] vector, double[][] tempData) {
        for (int i = 0; i < getRowDimension(); i++) {
            int maxElement = maxColumnElement(i, tempData);
            swapLines(i, maxElement, vector, tempData);
            for (int j = i + 1; j < getRowDimension(); j++) {
                transform(j, i, vector, tempData);
            }
        }
    }

    private int maxColumnElement(int step, double[][] tempData) {
        double maxElem = 0;
        int lineWithMaxElem = 0;
        for (int i = step; i < getRowDimension(); i++) {
            if (Math.abs(tempData[i][step]) > maxElem) {
                maxElem = Math.abs(tempData[i][step]);
                lineWithMaxElem = i;
            }
        }
        return lineWithMaxElem;
    }

    private void swapLines(int firstLine, int secondLine, double[] vector, double[][] tempData) {
        double bufferVector = vector[firstLine];
        vector[firstLine] = vector[secondLine];
        vector[secondLine] = bufferVector;

        double[] bufferMatrix = tempData[firstLine];
        tempData[firstLine] = tempData[secondLine];
        tempData[secondLine] = bufferMatrix;
    }

    private void transform(int currentRow, int subtrahendRow, double[] vector, double[][] tempData) {
        double scalingFactor = tempData[currentRow][subtrahendRow] /
                tempData[subtrahendRow][subtrahendRow];
        vector[currentRow] -= vector[subtrahendRow] * scalingFactor;
        for (int i = 0; i < getRowDimension(); i++) {
            tempData[currentRow][i] -= tempData[subtrahendRow][i] * scalingFactor;
        }
    }

    private int getRowDimension() {
        return this.data != null ? this.data.length : 0;
    }

    private int getColumnDimension() {
        return this.data != null && this.data[0] != null ? this.data[0].length : 0;
    }

    private double[][] copy(double[][] data) {
        if (data == null) {
            throw new NullPointerException();
        }

        int columns = data[0].length;
        if (columns == 0) {
            throw new IllegalArgumentException(NO_ONE_COLUMN);
        }

        int rows = data.length;
        double[][] copied = new double[rows][columns];

        for (int i = 0; i < copied.length; ++i) {
            if (data[i].length != columns) {
                throw new IllegalArgumentException(INCORRECT_MATRIX);
            }

            System.arraycopy(data[i], 0, copied[i], 0, columns);
        }
        return copied;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(data);
    }
}