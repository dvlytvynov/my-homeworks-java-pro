package factorymethod;

public class MainFactoryMethod {
    public static void main(String[] args) {
        System.out.println("--- Factory Method test ---");
        ToolPlant deWaltPlant = new DeWaltPlant();
        ToolPlant makitaPlant = new MakitaPlant();
        ElectricTool[] toolArray = new ElectricTool[]{
                deWaltPlant.factoryMethod("Drill", 0.6),
                deWaltPlant.factoryMethod("Screwdriver", 0.3),
                deWaltPlant.factoryMethod("AngleGrinder", 0.8),
                deWaltPlant.factoryMethod("Another tool", 1.8),
                makitaPlant.factoryMethod("Drill", 0.2),
                makitaPlant.factoryMethod("Screwdriver", 0.3),
                makitaPlant.factoryMethod("AngleGrinder", 0.8),
                makitaPlant.factoryMethod("Another tool", 1.8),
        };
        for (ElectricTool tools : toolArray) {
            System.out.println(tools.getInfo() + ". " + tools.work());
        }
    }
}
