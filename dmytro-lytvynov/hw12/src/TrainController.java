import java.util.Scanner;

public class TrainController implements Controller {
    private Scanner scanner = new Scanner(System.in);
    private TrainModel trainModel;

    public TrainController(TrainModel trainModel) {
        this.trainModel = trainModel;
    }

    public void start() {
        trainModel.controlTrain("start");
    }

    public void control() {
        String command = scanner.nextLine();
        switch (command) {
            case "8":
                trainModel.controlTrain("go forward");
                break;
            case "5":
                trainModel.controlTrain("stop");
                break;
            case "2":
                trainModel.controlTrain("go backward");
                break;
            case "-":
                trainModel.controlTrain("decrease speed");
                break;
            case "+":
                trainModel.controlTrain("increase speed");
                break;
        }
    }

}
