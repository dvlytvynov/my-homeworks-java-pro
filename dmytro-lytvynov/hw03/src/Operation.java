public class Operation {
    private char typeOperation;
    private double number1, number2;
    private double result;

    double get() throws ZeroDivisionException {
        switch (typeOperation){
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number1 - number2;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                if (number2 == 0) {
                    throw new ZeroDivisionException("Деление на ноль");
                }
                result = number1 / number2;
                break;
        }
        return result;
    }

    Operation(double a, double b, char c) {
        number1 = a;
        number2 = b;
        typeOperation = c;
    }


}
