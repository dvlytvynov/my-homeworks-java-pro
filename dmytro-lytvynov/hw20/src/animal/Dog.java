package animal;

public class Dog extends Animal {
    public Dog(FeedBox feedBox) {
        super(feedBox);
        super.feedPeriod = 6;
        super.foodAmount = 20;
    }
}
