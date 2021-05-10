import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double[][] mat = {
                {3, 4, -9, 5},
                {-15, -12, 50, -16},
                {-27, -36, 73, 8},
                {9, 12, -10, -16}
        };
        double[][] vec = {{-14}, {44}, {142}, {-76}};

        Matrix matrix = new Matrix(mat);
        Vector vector = new Vector(vec);
        double[] res = matrix.gaussSolve(vector);
        TestUtils.print(matrix.vector);
        System.out.println(Arrays.toString(res));
    }
}