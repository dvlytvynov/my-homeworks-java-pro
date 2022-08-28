package lambdaexpression;

import java.util.HashMap;
import java.util.Map;

public class HashTableMath {
    private Map<String, Calculate<Double>> mathMap;

    public HashTableMath() {
        this.mathMap = new HashMap<>();
        mathMap.put("+", (x, y) -> x + y);
        mathMap.put("-", (x, y) -> x - y);
        mathMap.put("*", (x, y) -> x * y);
        mathMap.put("/", (x, y) -> x / y);
        mathMap.put("%", (x, y) -> x % y);

    }

    public Double perform(String sign, double a, double b) {
        Calculate<Double> operation = mathMap.get(sign);
        if (operation == null) {
            return null;
        }
        return operation.execute(a, b);
    }
}
