package animal;

public class FeedBox {
    private int foodAmount;
    private final Object o = new Object();

    public FeedBox(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public boolean getFood(int portion) {
        synchronized (o) {
            int remainder = foodAmount - portion;
            if (remainder >= 0) {
                foodAmount = remainder;
                return true;
            } else {
                return false;
            }
        }
    }
}
