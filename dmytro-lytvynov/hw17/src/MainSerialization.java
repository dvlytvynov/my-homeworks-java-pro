import detail.Detail;
import exception.SerializationException;
import test.SerializerTest;

public class MainSerialization {
    public static void main(String[] args) throws SerializationException {
        System.out.println("- - - - Homework-17 Serialization - - - -");
        SerializerTest<Detail> serializerTest = new SerializerTest<>();
        serializerTest.test();
    }
}
