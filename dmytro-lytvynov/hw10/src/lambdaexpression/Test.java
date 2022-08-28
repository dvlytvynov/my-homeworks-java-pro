package lambdaexpression;

public class Test {
    public static void testLambda() {
        System.out.println("\n--- Test Lambda Expressions and default methods of Calculate Interface---");
        Calculate<Double> operation;
        operation = (x, y) -> x - y;
        System.out.println("\"(x = 5, y = 2) -> x - y\" = " + operation.execute(5d, 2d));
        operation = (x, y) -> x * y;
        System.out.println("\"(x = 10, y = 20) -> x * y\" = " + operation.execute(10d, 20d));
        operation = (x, y) -> x * 2 + y / 3;
        System.out.println("\"(x = 10, y = 33) -> x * 2 + y / 3\" = " + operation.execute(10d, 33d));
        System.out.println("add(10d, 20d) = " + operation.add(10d, 20d));
        System.out.println("subtract(8d, 1.3d) = " + operation.subtract(8d, 1.3d));
        System.out.println("divide(5d, 20d) = " + operation.divide(5d, 20d));
        System.out.println("multiply(10d, 25d) = " + operation.multiply(10d, 25d));
    }

    public static void testHashMapLambda() {
        System.out.println("\n--- Test Lambda Expressions in HashMap---");
        HashTableMath test = new HashTableMath();
        String[] operationsArray = new String[]{
                "+", "10d", "20d",
                "-", "100d", "16d",
                "*", "5d", "2.5d",
                "/", "10d", "4d",
                "%", "17d", "10d",
                "sqrt", "10d", "10d"
        };
        for (int i = 0; i < operationsArray.length; i += 3) {
            Double result = test.perform(
                    operationsArray[i],
                    Double.parseDouble(operationsArray[i + 1]),
                    Double.parseDouble(operationsArray[i + 2]));
            if (result != null) {
                System.out.println(
                                operationsArray[i + 1] + " " +
                                operationsArray[i] + " " +
                                operationsArray[i + 2] + " = " + result);
            } else {
                System.out.println(
                                operationsArray[i + 1] + " " +
                                operationsArray[i] + " " +
                                operationsArray[i + 2] + " = " + "No such operation");
            }
        }

    }

}
