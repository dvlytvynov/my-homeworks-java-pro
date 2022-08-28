package animal;

public abstract class Animal implements Runnable {
    private static int staticNumber = 0;
    private final int number;
    protected int feedPeriod;
    protected int foodAmount;
    private final FeedBox feedBox;

    public Animal(FeedBox feedBox) {
        Animal.staticNumber++;
        this.number = staticNumber;
        this.feedBox = feedBox;
    }

    @Override
    public void run() {
        String space = " ".repeat(number * 10);
        System.out.printf("%sAnimal-%d\n%s%s\n\n",
                space, number, space, Thread.currentThread().getName());
        String prefix = String.format("%s%s-%d \n%s",
                space, this.getClass().getSimpleName(), number, space);
        boolean hasFood = feedBox.getFood(foodAmount);
        while (hasFood) {
            System.out.println(prefix + "has eaten");
            if (Thread.interrupted()) {
                System.out.println(prefix + "has been DRIVEN");
                return;
            }
            try {
                Thread.sleep(feedPeriod * 100L);
            } catch (InterruptedException e) {
                System.out.println(prefix + "has been DRIVEN while sleeping");
                Thread.currentThread().interrupt();
                return;
            }
            hasFood = feedBox.getFood(foodAmount);
        }
        System.out.println(prefix + "has RUN away");
    }
}
