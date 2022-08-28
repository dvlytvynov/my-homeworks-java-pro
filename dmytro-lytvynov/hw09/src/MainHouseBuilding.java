public class MainHouseBuilding {

    public static void main(String[] args) {

        Director director1 = new Director();
        House.HouseBuilder builder1 = new House.HouseBuilder();

        House houseA = director1.buildHouse(builder1, "private");
        getInfo(houseA, "\"House A\"");
        House houseB = director1.buildHouse(builder1, "country");
        getInfo(houseB, "\"House B\"");
        House houseC = director1.buildHouse(builder1, "");
        getInfo(houseC, "\"House C\"");

    }

    static void getInfo(House house, String title) {
        System.out.println("\n--- Building of a house " + title + ": ---");
        System.out.println("House id number:  " + house.getId());
        System.out.println("House destination:  " + house.getDestination());
        System.out.println("Building beginning date:  " + house.getDate());
        System.out.println("House name:  " + house.getName());
        System.out.println("Address:  " + house.getAddress());
        System.out.println("Foundation type:  " + house.getFoundation());
        System.out.println("Number of floor:  " + house.getFloor());
        System.out.println("Basement type:  " + house.getBasement());
        System.out.println("Walls material:  " + house.getWall());
        System.out.println("Roof material:  " + house.getRoof());
        System.out.println("Garden availability:  " + house.isGarden());
        System.out.println("SwimmingPool availability:  " + house.isSwimmingPool());
    }


}
