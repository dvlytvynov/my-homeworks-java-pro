package animal;

public class Cat extends Animal {
    public Cat(FeedBox feedBox) {
        super(feedBox);
        super.feedPeriod = 8;
        super.foodAmount = 5;
    }
}
