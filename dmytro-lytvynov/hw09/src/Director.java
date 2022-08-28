public class Director {

    public House buildHouse(House.HouseBuilder builder, String type) {

        if (type.equals("private")) {
            builder.setDate("01.02.2022");
            builder.setName("Orange One");
            builder.setDestination("Private house");
            builder.setAddress("25, Bud street");
            builder.setFoundation("Concrete foundation");
            builder.setFloor(2);
            builder.setBasement("Deep basement with a workshop and a larder");
            builder.setWall("Brick walls");
            builder.setRoof("Metal tile roof");
            builder.setGarden(true);
            builder.setSwimmingPool(false);
            return builder.build();
        }

        if (type.equals("country")) {
            builder.setDate("01.04.2022");
            builder.setName("Small forest");
            builder.setDestination("Country house");
            builder.setAddress("88, Fountain street");
            builder.setFoundation("Concrete foundation");
            builder.setFloor(1);
            builder.setBasement("The basement is not available");
            builder.setWall("Brick walls");
            builder.setRoof("Slate roof");
            builder.setGarden(true);
            builder.setSwimmingPool(true);
            return builder.build();
        }

        builder.setDate("01.06.2022");
        builder.setName("Basic home");
        builder.setDestination("Block of flats");
        builder.setAddress("11, Main avenue");
        builder.setFoundation("Concrete foundation");
        builder.setFloor(10);
        builder.setBasement("Basement with engineering equipment");
        builder.setWall("Concrete walls");
        builder.setRoof("Concrete roof");
        builder.setGarden(false);
        builder.setSwimmingPool(false);
        return builder.build();

    }


}
