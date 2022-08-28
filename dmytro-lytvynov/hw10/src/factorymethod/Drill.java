package factorymethod;

public class Drill extends ElectricTool {

    public Drill(String typeTool, String model, double power) {
        super(typeTool, model, power);
    }

    @Override
    public String work() {
        return "It drills holes";
    }
}
