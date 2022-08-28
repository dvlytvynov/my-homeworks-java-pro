package test;

import animal.Animal;
import animal.FeedBox;
import thread.pool.ThreadPool;

public class TestJul {
    public void test() {
        ThreadPool threadPool = new ThreadPool();
        FeedBox feedBox = new FeedBox(50);
        Animal[] animals = new Animal[]{
                new Animal("Cat", 8, 5, feedBox),
                new Animal("Dog", 6, 20, feedBox),
                new Animal("Racoon", 11, 15, feedBox),
                new Animal("Mouse", 2, 0, feedBox)
        };
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
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.stop();
    }
}
