package sample;

public class Detail implements Sample {
    private String name;
    private transient String altName;
    private byte position;
    private short quantity;
    private char character;
    private int number;
    private long personalCode;
    private boolean isAvailable;
    private float width;
    private double length;

    public void setName(String name) {
        this.name = name;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }

    public void setPosition(byte position) {
        this.position = position;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPersonalCode(long personalCode) {
        this.personalCode = personalCode;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "name='" + name + '\'' +
                ", altName='" + altName + '\'' +
                ", position=" + position +
                ", quantity=" + quantity +
                ", character=" + character +
                ", number=" + number +
                ", personalCode=" + personalCode +
                ", isAvailable=" + isAvailable +
                ", width=" + width +
                ", length=" + length +
                '}';
    }
}
