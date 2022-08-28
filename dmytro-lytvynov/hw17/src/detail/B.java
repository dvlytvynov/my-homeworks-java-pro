package detail;

public class B extends A implements Detail {
    private double length;
    private double width;
    private C screw;
    private transient int screwQuantity;

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setScrew(C screw) {
        this.screw = screw;
    }

    public void setScrewQuantity(int screwQuantity) {
        this.screwQuantity = screwQuantity;
    }

    @Override
    public String getDetailInfo() {
        return "detail.B{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", weight=" + weight +
                ", currentTemperature=" + currentTemperature +
                ", length=" + length +
                ", width=" + width +
                ", screw=" + screw.getDetailInfo() +
                ", screwQuantity=" + screwQuantity +
                '}';
    }
}
