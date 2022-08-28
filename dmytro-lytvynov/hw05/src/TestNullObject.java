
public class TestNullObject {
    public static void main(String[] args) {

        Data test1 = new Data(100, "km");
        System.out.println("Result = " + Action(test1));

        NullObject test2 = new NullObject(10, "kg");
        System.out.println("Result = " + Action(test2));
    }

    static String Action (Data dt) {
        return dt.getA() + " " + dt.getB();
    }

}


class Data {
    private int a;
    private String b;

    public Data(int x, String y) {
        this.a = x;
        this.b = y;
    }

    public int getA() {
        return a;
    }

    public String getB() {
        return b;
    }
}



class NullObject extends Data {

    public NullObject(int x, String y) {
        super(x, y);
    }

    @Override
    public int getA() {
        return 0;
    }

    @Override
    public String getB() {
        return "NullObject";
    }
}


