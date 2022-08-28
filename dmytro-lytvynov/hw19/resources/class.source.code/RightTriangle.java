package figure;

public class RightTriangle implements Figure {
    private final double a;
    private final double b;

    public RightTriangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double getPerimeter() {
        return a + b + Math.sqrt(a * a + b * b);
    }

    @Override
    public double getSquare() {
        return a * b / 2;
    }

    @Override
    public String calculate() {
        String title = String.format("Right triangle(a = %.2f  b = %.2f): ", a, b);
        String line = String.format("perimeter = %.2f\n%s square = %.2f",
                getPerimeter(), " ".repeat(title.length() - 1), getSquare());
        return "\n" + title + line;
    }
}
