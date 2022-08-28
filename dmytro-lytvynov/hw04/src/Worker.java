public class Worker<T, V> {

    private T id;
    private String fname;
    private String lname;
    private int age;
    private V cardnumber;


    public Worker(T id, String fname, String lname, int age, V cardnumber) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.cardnumber = cardnumber;
    }


    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public V getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(V cardnumber) {
        this.cardnumber = cardnumber;
    }





}
