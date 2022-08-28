import java.util.Objects;

public class TestClass {
    private String name;
    private int id;

    public TestClass(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name) + Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        TestClass temp = (TestClass) obj;
        if (temp.name == name && temp.id == id) return true;
        return false;
    }
}
