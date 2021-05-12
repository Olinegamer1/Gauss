public class TestUtils {
    public static void print(double[][] mas) {
        for (double[] i : mas) {
            for (double j : i) {
                System.out.printf("%10.5f", j);
            }
            System.out.println();
        }
        System.out.println();
    }

}
