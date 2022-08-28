public class MvcMain {
    public static void main(String[] args) {
        System.out.println("= = = = =  Test MVC (Model - View - Controller)  = = = = =");
        TrainModel train1 = new TrainModel("Silver Arrow");
        TrainView view1 = new TrainView();
        train1.subscribe(view1);
        TrainController controller1 = new TrainController(train1);

        controller1.start();
        while (true) {
            controller1.control();
        }
    }
}
