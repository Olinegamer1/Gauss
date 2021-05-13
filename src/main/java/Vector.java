public class Vector {
    private final double[] vector;

    public Vector(double[] vector) {
        this.vector = vector;
    }

    public int size() {
        return vector.length;
    }
    public double[] getData() {
        return vector;
    }
}
