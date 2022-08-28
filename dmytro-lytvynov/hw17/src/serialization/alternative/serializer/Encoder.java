package serialization.alternative.serializer;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Encoder {
    private final int RADIX;

    public Encoder(int radix) {
        this.RADIX = radix;
    }

    public byte[] encode(byte[] byteArr) {
        BigInteger bi = new BigInteger(1, byteArr);
        String str = bi.toString(RADIX);
        return str.getBytes(StandardCharsets.UTF_8);
    }

    public byte[] decode(byte[] byteArr) {
        String str = new String(byteArr, StandardCharsets.UTF_8);
        BigInteger bi = new BigInteger(str, RADIX);
        return bi.toByteArray();
    }
}