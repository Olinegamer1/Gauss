import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double[][] mat = {
                {1, 4, 8},
                {3, 2, 2},
                {2, 2, 8}
        };
        double[][] vec = {{1}, {3}, {7}};

        Matrix matrix = new Matrix(mat);
        Vector vector = new Vector(vec);

        double[] res = matrix.gaussSolve(vector);
        System.out.println(Arrays.toString(res));

    }
}