package detail;

public class C implements Detail {
    private transient String type;
    private double diameter;
    private double length;
    private int number;

    public void setType(String type) {
        this.type = type;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String getDetailInfo() {
        return "detail.C{" +
                "type='" + type + '\'' +
                ", diameter=" + diameter +
                ", length=" + length +
                ", number=" + number +
                '}';
    }
}

