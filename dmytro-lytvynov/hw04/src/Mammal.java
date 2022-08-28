public class Mammal<T, V, W> extends Animal{

    private String tailsize;
    private String runspeed;


    public Mammal(T id, String name, String type, V color, W size, int age, String description, String tailsize, String runspeed) {
        super(id, name, type, color, size, age, description);
        this.tailsize = tailsize;
        this.runspeed = runspeed;
    }


    public String getTailsize() {
        return tailsize;
    }

    public void setTailsize(String tailsize) {
        this.tailsize = tailsize;
    }

    public String getRunspeed() {
        return runspeed;
    }

    public void setRunspeed(String runspeed) {
        this.runspeed = runspeed;
    }
}
