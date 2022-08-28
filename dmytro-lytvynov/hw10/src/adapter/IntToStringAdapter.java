package adapter;

public class IntToStringAdapter extends NumberStore implements NumberAdapter {

    @Override
    public String getStringA() {
        return "Number A = " + String.valueOf(getNumberA());
    }

    @Override
    public String getStringB() {
        return "Number B = " + String.valueOf(getNumberB());
    }

    @Override
    public String getStringC() {
        return "Number C = " + String.valueOf(getNumberC());
    }
}
