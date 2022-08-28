public class Bird<T, V, W> extends Animal{

    private String wingsize;
    private String flyspeed;


    public String getWingsize() {
        return wingsize;
    }

    public void setWingsize(String wingsize) {
        this.wingsize = wingsize;
    }

    public String getFlyspeed() {
        return flyspeed;
    }

    public void setFlyspeed(String flyspeed) {
        this.flyspeed = flyspeed;
    }


    public Bird(T id, String name, String type, V color, W size, int age, String description, String wingsize, String flyspeed) {
        super(id, name, type, color, size, age, description);
        this.wingsize = wingsize;
        this.flyspeed = flyspeed;
    }
}
