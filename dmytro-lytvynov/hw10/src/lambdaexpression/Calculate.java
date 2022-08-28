package lambdaexpression;

@FunctionalInterface
interface Calculate<T extends Number> {
    T execute(T x, T y);

    default double add(T x, T y) {
        return x.doubleValue() + y.doubleValue();
    }

    default double subtract(T x, T y) {
        return x.doubleValue() - y.doubleValue();
    }

    default double multiply(T x, T y) {
        return x.doubleValue() * y.doubleValue();
    }

    default double divide(T x, T y) {
        return x.doubleValue() / y.doubleValue();
    }
}
