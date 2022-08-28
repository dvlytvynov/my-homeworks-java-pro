package cars;

import metadata.Action;
import metadata.Author;
import metadata.Data;
import metadata.Version;

@Version(value = "2.0.1.1", date = "28.04.2022", note = "Class is used for observing after cars")
@Author(authors = {"John", "Sarah", "Elizabeth"})
public class Car {

    @Data("note_name")
    private final String NAME;

    @Data("note_model")
    private final String MODEL;

    @Data("note_average_speed")
    private final double AVERAGE_SPEED;

    @Data("note_passengers_quantity")
    private int passengersQuantity;

    @Data("note_distance")
    private double distance;

    @Data("note_start_time")
    private double startTime;

    public Car(String NAME, String MODEL, double AVERAGE_SPEED) {
        this.NAME = NAME;
        this.MODEL = MODEL;
        this.AVERAGE_SPEED = AVERAGE_SPEED;
    }

    @Version(date = "28.04.2022")
    @Action(number = 1, value = "start_journey", purpose = "To start car journey")
    public void startJourney(int passengersQuantity, double distance, double startTime) {
        this.startTime = startTime;
        this.passengersQuantity = passengersQuantity;
        this.distance = distance;
    }

    @Version(date = "28.04.2022")
    @Action(number = 2, value = "get_car_info", purpose = "To get info during journey")
    public String getCarInfo(double currentTime) {
        if (distance == 0) {
            return "Journey hasn't started yet!";
        }
        double finishTime = startTime + distance / AVERAGE_SPEED;
        double timeInJourney = currentTime - startTime;
        String CarInfo =
                "\nInfo about car %s %s:\n" +
                        "Average speed: %.2f km/h\n" +
                        "Passengers quantity: %d\n" +
                        "Distance: %.2f km\n" +
                        "Start time: %.2f h\n" +
                        "Current time: %.2f h\n" +
                        "Time in Journey: %.2f h\n" +
                        "Finish time: %.2f h\n";
        return String.format(CarInfo, NAME, MODEL, AVERAGE_SPEED, passengersQuantity,
                distance, startTime, currentTime, timeInJourney, finishTime);
    }

    @Version(date = "28.04.2022")
    @Action(number = 3, value = "finish_journey",
            purpose = "To move the class into an initial state after journey finish")
    public String finishJourney() {
        this.startTime = 0;
        this.passengersQuantity = 0;
        this.distance = 0;
        return "Journey has finished!";
    }

    @Action(number = 4)
    public String getCarModel() {
        return "Car model: " + NAME + " " + MODEL;
    }
}



