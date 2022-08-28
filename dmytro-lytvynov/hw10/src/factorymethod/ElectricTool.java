package factorymethod;

public abstract class ElectricTool {
    private String plantName;
    private String typeTool;
    private String model;
    private double power;

    public ElectricTool(String typeTool, String model, double power) {
        this.typeTool = typeTool;
        this.model = model;
        this.power = power;
    }

    public String getPlantName() {
        return plantName;
    }

    public String getTypeTool() {
        return typeTool;
    }

    public String getModel() {
        return model;
    }

    public double getPower() {
        return power;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getInfo() {
        return "Factory \"" + getPlantName() + "\", " + getTypeTool() + " " +
                getModel() + ", power = " + getPower() + " kW";
    }

    public abstract String work();
}
