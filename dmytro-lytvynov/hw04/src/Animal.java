public class Animal<T, V, W> {


    private T id;
    private String name;
    private String type;
    private V color;
    private W size;
    private int age;
    private String description;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public V getColor() {
        return color;
    }

    public void setColor(V color) {
        this.color = color;
    }

    public W getSize() {
        return size;
    }

    public void setSize(W size) {
        this.size = size;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Animal(T id, String name, String type, V color, W size, int age, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.color = color;
        this.size = size;
        this.age = age;
        this.description = description;
    }



}
