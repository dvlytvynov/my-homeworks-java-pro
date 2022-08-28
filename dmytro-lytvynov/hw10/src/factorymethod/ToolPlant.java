package factorymethod;

public abstract class ToolPlant {

    public abstract ElectricTool factoryMethod(String toolType, double power);
}
