public class TrainState {
    private String name;
    private double speed;
    private String state;

    public TrainState(String name, double speed, String state) {
        this.name = name;
        this.speed = speed;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public String getState() {
        return state;
    }
}
