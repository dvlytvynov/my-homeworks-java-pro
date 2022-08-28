package animal;

import adjuster.LoggerAdjuster;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Animal implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Animal.class.getName());
    private static int staticNumber = 0;
    private final String name;
    private final int number;
    private final int feedPeriod;
    private final int foodAmount;
    private final FeedBox feedBox;

    public Animal(String name, int feedPeriod, int foodAmount, FeedBox feedBox) {
        Animal.staticNumber++;
        this.name = name;
        this.number = staticNumber;
        this.feedPeriod = feedPeriod;
        this.foodAmount = foodAmount;
        this.feedBox = feedBox;
        new LoggerAdjuster().adjustLogger(LOGGER);
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        LOGGER.severe("Method run() starts, Tread: " + threadName);
        LOGGER.log(Level.INFO, "{0}-{1}, {2}",
                new String[]{name, String.valueOf(number), threadName});
        String prefix = String.format("%s-%d", name, number);
        boolean hasFood = feedBox.getFood(foodAmount);
        while (hasFood) {
            LOGGER.info(prefix + " has eaten");
            if (Thread.interrupted()) {
                LOGGER.info(prefix + " has been DRIVEN");
                LOGGER.severe("Method run() interrupted, Tread: " + threadName);
                return;
            }
            try {
                Thread.sleep(feedPeriod * 100L);
            } catch (InterruptedException e) {
                LOGGER.info(prefix + " has been DRIVEN while sleeping");
                LOGGER.severe("Method run() interrupted, Tread: " + threadName);
                Thread.currentThread().interrupt();
                return;
            }
            hasFood = feedBox.getFood(foodAmount);
        }
        LOGGER.info(prefix + " has RUN away");
        LOGGER.severe("Method run() finishes, Tread: " + threadName);
    }
}
