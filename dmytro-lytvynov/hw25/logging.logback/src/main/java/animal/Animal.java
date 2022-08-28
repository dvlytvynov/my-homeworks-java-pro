package animal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Animal implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Animal.class);
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
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        LOGGER.debug("Method run() starts, Tread: " + threadName);
        LOGGER.info("{}-{}, {}", name, number, threadName);
        String prefix = String.format("%s-%d", name, number);
        boolean hasFood = feedBox.getFood(foodAmount);
        while (hasFood) {
            LOGGER.info(prefix + " has eaten");
            if (Thread.interrupted()) {
                LOGGER.info(prefix + " has been DRIVEN");
                LOGGER.debug("Method run() interrupted, Tread: " + threadName);
                return;
            }
            try {
                Thread.sleep(feedPeriod * 100L);
            } catch (InterruptedException e) {
                LOGGER.info(prefix + " has been DRIVEN while sleeping");
                LOGGER.debug("Method run() interrupted, Tread: " + threadName);
                Thread.currentThread().interrupt();
                return;
            }
            hasFood = feedBox.getFood(foodAmount);
        }
        LOGGER.info(prefix + " has RUN away");
        LOGGER.debug("Method run() finishes, Tread: " + threadName);
    }
}
