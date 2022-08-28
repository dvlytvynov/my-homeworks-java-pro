package animal;

public class Racoon extends Animal {
    public Racoon(FeedBox feedBox) {
        super(feedBox);
        super.feedPeriod = 11;
        super.foodAmount = 15;
    }
}
