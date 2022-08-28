
public class Zoo {

    public static void main(String[] args) {

        Worker<String, Integer> TomJ = new Worker<>(
                "WR1235", "Tom", "J", 32, 894563);
        Worker<Integer, String> SaraH = new Worker<>(
                56, "Sara", "H", 26, "NNN1258744");
        TomJ.setId("WRRT5647");

        Mammal<Integer, String, String> LionFrank = new Mammal<>(
                25,"Frank", "Lion", "Dark-orange", "Big",
                8, "Very strong", "1.5m", "60km/h");
        Mammal<String, Integer, Integer> BearJim = new Mammal<>(
                "B251","Jim", "Bear", 0x8B4513, 5,
                2, "Always hungry", "1.5m", "60km/h");
        Bird<Integer, String, Double> CrowBen = new Bird<>(
                14, "Ben", "Crow", "Black", 30.5, 2,
                "Loud", "Middle", "30km/h");

        System.out.println("Zoo in some city");
        System.out.println();

        System.out.println("Workers:");
        showData(TomJ);
        showData(SaraH);

        System.out.println();
        System.out.println("Animals:");
        showData(LionFrank);
        showData(BearJim);
        showData(CrowBen);



    }
        public static void showData(Worker p){
            System.out.print("Worker:   " + p.getFname() + " " + p.getLname());
            System.out.print(", " + p.getAge() + " years old");
            System.out.print(", id: " + p.getId());
            System.out.println(", cardnumber: " + p.getCardnumber());
        }

        public static void showData(Mammal p){
            System.out.print(p.getType() + " " + p.getName() + ": ");
            System.out.print(p.getSize() + " size, " + p.getColor() + " color, ");
            System.out.println(p.getDescription() + ", Can run " + p.getRunspeed());
        }

        public static void showData(Bird p){
            System.out.print(p.getType() + " " + p.getName() + ": ");
            System.out.print(p.getSize() + " size, " + p.getColor() + " color, ");
            System.out.println(p.getDescription() + ", Can fly " + p.getFlyspeed());
        }



}
