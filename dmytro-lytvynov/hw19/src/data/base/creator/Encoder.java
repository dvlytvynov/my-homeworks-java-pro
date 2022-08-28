package data.base.creator;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Encoder {
    private final int RADIX;

    public Encoder(int radix) {
        this.RADIX = radix;
    }

    public byte[] encode(byte[] byteArr) {
        byte[] addByteArr = new byte[byteArr.length + 1];
        System.arraycopy(byteArr, 0, addByteArr, 1, byteArr.length);
        addByteArr[0] = 1;
        BigInteger bi = new BigInteger(1, addByteArr);
        String str = bi.toString(RADIX);
        return str.getBytes(StandardCharsets.UTF_8);
    }

    public byte[] decode(byte[] byteArr) {
        String str = new String(byteArr, StandardCharsets.UTF_8);
        BigInteger bi = new BigInteger(str, RADIX);
        byte[] addByteArr = bi.toByteArray();
        byte[] result = new byte[addByteArr.length - 1];
        System.arraycopy(addByteArr, 1, result, 0, addByteArr.length - 1);
        return result;
    }
}