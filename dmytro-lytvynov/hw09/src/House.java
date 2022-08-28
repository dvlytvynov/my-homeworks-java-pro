public class House {
    private final String date;
    private final String name;
    private static int id;
    private final String destination;
    private final String address;
    private final String foundation;
    private final int floor;
    private final String basement;
    private final String wall;
    private final String roof;
    private final boolean garden;
    private final boolean swimmingPool;

    private House(String date, String name, int id, String destination,
                  String address, String foundation, int floor, String basement,
                  String wall, String roof, boolean garden, boolean swimmingPool) {
        this.date = date;
        this.name = name;
        this.id++;
        this.destination = destination;
        this.address = address;
        this.foundation = foundation;
        this.floor = floor;
        this.basement = basement;
        this.wall = wall;
        this.roof = roof;
        this.garden = garden;
        this.swimmingPool = swimmingPool;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public String getAddress() {
        return address;
    }

    public String getFoundation() {
        return foundation;
    }

    public int getFloor() {
        return floor;
    }

    public String getBasement() {
        return basement;
    }

    public String getWall() {
        return wall;
    }

    public String getRoof() {
        return roof;
    }

    public boolean isGarden() {
        return garden;
    }

    public boolean isSwimmingPool() {
        return swimmingPool;
    }



    public static class HouseBuilder implements Builder<House> {
        public String date;
        public String name;
        public String destination;
        public String address;
        public String foundation;
        public int floor;
        public String basement;
        public String wall;
        public String roof;
        public boolean garden;
        public boolean swimmingPool;


        @Override
        public House build() {
            return new House(date, name, id, destination,
                    address, foundation, floor, basement,
                    wall, roof, garden, swimmingPool);
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setFoundation(String foundation) {
            this.foundation = foundation;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public void setBasement(String basement) {
            this.basement = basement;
        }

        public void setWall(String wall) {
            this.wall = wall;
        }

        public void setRoof(String roof) {
            this.roof = roof;
        }

        public void setGarden(boolean garden) {
            this.garden = garden;
        }

        public void setSwimmingPool(boolean swimmingPool) {
            this.swimmingPool = swimmingPool;
        }
    }

}
