package adapter;

public class MainAdapter {
    public static void main(String[] args) {
        System.out.println("--- Adapter test ---");

        NumberAdapter adapter = new IntToStringAdapter();

        System.out.println(adapter.getStringA());
        System.out.println(adapter.getStringB());
        System.out.println(adapter.getStringC());

    }
}
