package factorymethod;

public class AngleGrinder extends ElectricTool{

    public AngleGrinder(String typeTool, String model, double power) {
        super(typeTool, model, power);
    }

    @Override
    public String work() {
        return "It cuts and grinds";
    }
}
