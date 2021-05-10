import java.util.Arrays;

public class Matrix {
    private static final String NO_ONE_COLUMN = "Matrix hasn't column";
    private static final String INCORRECT_MATRIX = "Incorrect matrix";
    private final double[][] data;
    private double[][] tempData;
    public double[][] vector;

    public Matrix(double[][] data) {
        this.data = copy(data);
    }

    public double[] gaussSolve(Vector vector) {
        tempData = copy(data);
        this.vector = copy(vector.getData());
        convertToTriangle();
        return inverseStepGauss();
    }

    private double[] inverseStepGauss() {
        double[] result = new double[getColumnDimension()];
        for (int i = getRowDimension() - 1; i >= 0; i--) {
            double temp = vector[i][0] / tempData[i][i];
            for (int j = i + 1; j < getColumnDimension(); j++) {
                result[i] -= (this.tempData[i][j] * result[j]) / this.tempData[i][i];
            }
            result[i] += temp;
        }
        return result;
    }

    private void convertToTriangle() {
        for (int i = 0; i < getRowDimension(); i++) {
            int maxElement = maxColumnElement(i);
            swapLines(i, maxElement);
            for (int j = i + 1; j < getRowDimension(); j++) {
                transform(j, i);
            }
        }
    }

    private int maxColumnElement(int step) {
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

    private void swapLines(int firstLine, int secondLine) {
        double[] bufferVector = vector[firstLine];
        vector[firstLine] = vector[secondLine];
        vector[secondLine] = bufferVector;

        double[] bufferMatrix = tempData[firstLine];
        tempData[firstLine] = tempData[secondLine];
        tempData[secondLine] = bufferMatrix;
    }

    private void transform(int currentRow, int subtrahendRow) {
        double scalingFactor = tempData[currentRow][subtrahendRow] /
                tempData[subtrahendRow][subtrahendRow];
        vector[currentRow][0] -= vector[subtrahendRow][0] * scalingFactor;
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