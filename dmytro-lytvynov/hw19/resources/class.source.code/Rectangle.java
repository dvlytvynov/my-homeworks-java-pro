package figure;

public class Rectangle implements Figure {
    private final double a;
    private final double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double getPerimeter() {
        return 2 * (a + b);
    }

    @Override
    public double getSquare() {
        return a * b;
    }

    @Override
    public String calculate() {
        String title = String.format("Rectangle(a = %.2f  b = %.2f): ", a, b);
        String line = String.format("perimeter = %.2f\n%s square = %.2f",
                getPerimeter(), " ".repeat(title.length() - 1), getSquare());
        return "\n" + title + line;
    }
}
