public class Test {
    private int number1;
    private int number2;
    private int result;

    int getResult(int a, int b) {
        number1 = a;
        number2 = b;

        try {
            result = a/b;
            return result;
        }
        catch (ArithmeticException ex){
            ex.printStackTrace();
            return 0;
        }
        finally {

            return 100;
        }


    }

}
