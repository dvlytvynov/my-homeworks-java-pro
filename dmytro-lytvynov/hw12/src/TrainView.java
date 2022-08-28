public class TrainView implements View, Observer {

    @Override
    public void update(TrainState modelState) {
        System.out.println("Train name: " + modelState.getName());
        System.out.println("State: " + modelState.getState());
        System.out.println("Speed: " + modelState.getSpeed());
        System.out.println("Input: \"8\" to go forward, \"5\" to stop, \"2\" to go backward, " +
                "\"+\" - to increase speed, \"-\" to decrease speed");
    }
}
