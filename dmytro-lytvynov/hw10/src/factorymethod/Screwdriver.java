package factorymethod;

public class Screwdriver extends ElectricTool{

    public Screwdriver(String typeTool, String model, double power) {
        super(typeTool, model, power);
    }

    @Override
    public String work() {
        return "It turns screws";
    }
}
