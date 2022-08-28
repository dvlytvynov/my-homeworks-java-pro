package detail;

import exception.SerializationException;
import serialization.alternative.serializer.AltSerializer;
import serialization.alternative.serializer.Encoder;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ExtraC implements Externalizable {
    private transient String type;
    private byte holesQuantity;
    private short shortNumber;
    private char character;
    private int number;
    private long longNumber;
    private boolean available;
    private float width;
    private transient double diameter;
    private double length;

    public void setType(String type) {
        this.type = type;
    }

    public void setHolesQuantity(byte holesQuantity) {
        this.holesQuantity = holesQuantity;
    }

    public void setShortNumber(short shortNumber) {
        this.shortNumber = shortNumber;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setLongNumber(long longNumber) {
        this.longNumber = longNumber;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getDetailInfo() {
        return "ExtraC{" +
                "type='" + type + '\'' +
                ", holesQuantity=" + holesQuantity +
                ", shortNumber=" + shortNumber +
                ", character=" + character +
                ", number=" + number +
                ", longNumber=" + longNumber +
                ", available=" + available +
                ", width=" + width +
                ", diameter=" + diameter +
                ", length=" + length +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        Encoder encoder = new Encoder(36);
        AltSerializer<ExtraC> serializer = new AltSerializer<>();
        try {
            String stateData = serializer.createDataFromReflection(this);
            byte[] data = stateData.getBytes(StandardCharsets.UTF_8);
            data = encoder.encode(data);
            out.write(data);
            out.flush();
        } catch (SerializationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Encoder encoder = new Encoder(36);
        AltSerializer<ExtraC> serializer = new AltSerializer<>();
        List<Byte> listData = new ArrayList<>();
        int a = in.read();
        while (a > -1) {
            listData.add((byte) a);
            a = in.read();
        }
        byte[] data = new byte[listData.size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = listData.get(i);
        }
        data = encoder.decode(data);
        String stateData = new String(data, StandardCharsets.UTF_8);
        try {
            serializer.renewObjectFields(this, stateData);
        } catch (SerializationException e) {
            e.printStackTrace();
        }
    }
}
