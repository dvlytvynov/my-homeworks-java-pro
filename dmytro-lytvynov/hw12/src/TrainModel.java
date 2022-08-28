import java.util.ArrayList;
import java.util.List;

public class TrainModel implements Model, Observable {
    private String name;
    private double speed;
    private String state;
    private final List<Observer> observerList = new ArrayList<>();
    private TrainState newState;

    public TrainModel(String name) {
        this.name = name;
        this.speed = 0;
        this.state = "stop";
    }

    public void controlTrain(String command) {
        TrainState previousTrainState = new TrainState(name, speed, state);
        switch (command) {
            case "start":
                sendNewState();
                break;
            case "stop":
                state = "stop";
                speed = 0;
                break;
            case "go forward":
                if (!previousTrainState.getState().equals("go forward")) {
                    state = "go forward";
                    speed = 10;
                }
                break;
            case "increase speed":
                if (!previousTrainState.getState().equals("stop")) {
                    speed += 10;
                }
                break;
            case "decrease speed":
                if (previousTrainState.getSpeed() >= 10
                        && !previousTrainState.getState().equals("stop")) {
                    speed -= 10;
                }
                break;
            case "go backward":
                if (!previousTrainState.getState().equals("go backward")) {
                    state = "go backward";
                    speed = 10;
                }
                break;
        }
        if (!state.equals(previousTrainState.getState()) || previousTrainState.getSpeed() != speed) {
            sendNewState();
        }
    }

    @Override
    public void subscribe(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void sendNewState() {
        newState = new TrainState(name, speed, state);
        for (int i = 0; i < observerList.size(); i++) {
            Observer o = observerList.get(i);
            o.update(newState);
        }
    }
}
