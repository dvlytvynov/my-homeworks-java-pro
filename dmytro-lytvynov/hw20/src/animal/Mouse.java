package animal;

public class Mouse extends Animal{
    public Mouse(FeedBox feedBox) {
        super(feedBox);
        super.feedPeriod = 2;
        super.foodAmount = 0;
    }
}
