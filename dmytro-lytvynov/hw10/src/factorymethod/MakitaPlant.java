package factorymethod;

public class MakitaPlant extends ToolPlant{

    @Override
    public ElectricTool factoryMethod(String toolType, double power) {
        ElectricTool tool = null;
        switch (toolType) {
            case "Drill":
                if (power <= 0.5) {
                    tool = new Drill("Drill", "MD-050", 0.5);
                }
                if (power > 0.5 && power <= 1.0){
                    tool = new Drill("Drill", "MD-100", 1.0);
                }
                if (power > 1.0) {
                    tool = new Drill("Drill", "MD-150", 1.5);
                }
                break;
            case "Screwdriver":
                if (power <= 0.4) {
                    tool = new Screwdriver("Screwdriver", "MS-040", 0.4);
                }
                if (power > 0.4 && power <= 0.8){
                    tool = new Screwdriver("Screwdriver", "MS-080", 0.8);
                }
                if (power > 0.8) {
                    tool = new Screwdriver("Screwdriver", "MS-100", 1.0);
                }
                break;
            case "AngleGrinder":
                if (power <= 1.0) {
                    tool = new AngleGrinder("AngleGrinder", "MA-100", 1.0);
                }
                if (power > 1.0) {
                    tool = new AngleGrinder("AngleGrinder", "MA-150", 1.5);
                }
                break;
            default:
                tool = new ElectricTool("Tool does not exist", "", 0) {
                    @Override
                    public String work() {
                        return "";
                    }
                };
        }
        tool.setPlantName("Makita");
        return tool;
    }
}
