package detail;

public class A implements Detail {
    protected String name;
    protected int number;
    protected double weight;
    protected transient double currentTemperature;

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

    @Override
    public String getDetailInfo() {
        return "detail.A{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", weight=" + weight +
                ", currentTemperature=" + currentTemperature +
                '}';
    }
}
