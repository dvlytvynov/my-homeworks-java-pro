package test;

import animal.Animal;
import animal.Cat;
import animal.Dog;
import animal.FeedBox;
import animal.Mouse;
import animal.Racoon;
import thread.pool.ThreadPool;

public class TestThreadPool {
    public void test() {
        System.out.println("- - Test Thread Pool - -");
        ThreadPool threadPool = new ThreadPool();
        FeedBox feedBox = new FeedBox(1000);
        Animal[] animals = new Animal[]{
                new Cat(feedBox),
                new Dog(feedBox),
                new Racoon(feedBox),
                new Cat(feedBox),
                new Mouse(feedBox),
                new Dog(feedBox),
                new Dog(feedBox),
                new Cat(feedBox),
                new Racoon(feedBox)
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
            System.out.println("Food remain: " + foodRemain);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.stop();
    }
}
