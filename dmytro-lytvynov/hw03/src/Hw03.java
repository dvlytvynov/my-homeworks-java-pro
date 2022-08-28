import java.util.Scanner;
public class Hw03 {


    public static void main(String[] args){

        System.out.println("Simple calculator. Type 'exit' to quit.");
        System.out.println("Examples: 5.1+12[Enter]  32-8.25[Enter]  5*1.2[Enter]  6/3[Enter]");

        String inData;
        char typeOp = '_';
        double num1 = 0;
        double num2 = 0;
        double result;

        Scanner in = new Scanner(System.in);


        do {
            System.out.print("Input: ");
            inData = in.nextLine();

            for (int i=0; i<inData.length(); i++) {
                char c = inData.charAt(i);

                if (c=='+'||c=='-'||c=='*'||c=='/'){
                    typeOp = c;
                    break;
                }
            }

            String inData1 = inData.replace(typeOp, ' ');

            String[] numbers = inData1.split(" ");


            try {
                if (numbers.length<2 && !(inData.equals("exit"))) {
                    throw new NotEnoughDataException("Недостаточно данных");
                }
                if (numbers.length>2) {
                    throw new TooManyDataException("Слишком много данных");
                }
            }
            catch (NotEnoughDataException e) {
//                e.printStackTrace();
                System.out.println("Недостаточно данных");
            }
            catch (TooManyDataException e) {
//                e.printStackTrace();
                System.out.println("Слишком много данных");
            }


            if (numbers.length==2) {
                try {
                    num1 = Double.parseDouble(numbers[0]);
                    num2 = Double.parseDouble(numbers[1]);
                } catch (NumberFormatException e) {

                    try {
                        throw new WrongDataException("Неправильный ввод данных");
                    }
                    catch (WrongDataException e1){
//                        e1.printStackTrace();
                        System.out.println("Неправильный ввод данных");
                    }

                }
                finally {
//                    System.out.println("Finally");
                }

                Operation op1 = new Operation(num1,num2,typeOp);
                try {
                    System.out.print("= ");
                    result = op1.get();
                    System.out.println(result);
                }
                catch (ZeroDivisionException ex) {
//                    ex.printStackTrace();
                    System.out.println("Деление на ноль");
                }


            }


        } while (!(inData.equals("exit")));

        int t;
        Test d = new Test();
        t = d.getResult(16,2);
        System.out.println("16 / 2 = " + t);
        t = d.getResult(45,1);
        System.out.println("45 / 0 = " + t);


        System.out.println("End of work");


    }
}
