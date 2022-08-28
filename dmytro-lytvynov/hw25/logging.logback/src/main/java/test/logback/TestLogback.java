package test.logback;

import animal.Animal;
import animal.FeedBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import thread.pool.ThreadPool;

public class TestLogback {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestLogback.class);

    public void test() {
        LOGGER.debug("TestBacklog.test() starts");
        ThreadPool threadPool = new ThreadPool();
        FeedBox feedBox = new FeedBox(300);
        Animal[] animals = new Animal[]{
                new Animal("Cat", 8, 5, feedBox),
                new Animal("Dog", 6, 20, feedBox),
                new Animal("Racoon", 11, 15, feedBox),
                new Animal("Mouse", 2, 0, feedBox)
        };
        LOGGER.info(animals.length + " animals has been invited");
        for (Animal animal : animals) {
            threadPool.run(animal);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int foodRemain = feedBox.getFoodAmount();
        while (foodRemain > 5) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            foodRemain = feedBox.getFoodAmount();
            LOGGER.info("Food remain: " + foodRemain);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.stop();
        LOGGER.debug("Thread pool stops");
        LOGGER.debug("TestBacklog.test() finishes");
    }
}
