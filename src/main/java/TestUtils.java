public class TestUtils {
    public static void print(double[][] mas) {
        for (double[] i : mas) {
            for (double j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
