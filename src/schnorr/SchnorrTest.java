package schnorr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SchnorrTest {

    @Test
    void valid_2() {
        valid("in",2);
    }

    @Test
    void valid_2_100() {
        for (int i=0; i<100; i++) {
            valid(Integer.toString(i),2);
        }
    }

    @Test
    void valid_3_100() {
        for (int i=0; i<100; i++) {
            valid(Integer.toString(i),3);
        }
    }

    @Test
    void valid_5_1000() {
        for (int i=0; i<1000; i++) {
            valid(Integer.toString(i),5);
        }
    }

    @Test
    void valid_10() {
        valid("rain",10);
    }

    @Test
    void valid_100() {
        valid("sprain",100);
    }

    @Test
    void valid_500() {
        valid("sprainly",500);
    }

    void valid(String message,int bitLength) {
        KeyPair     keyPair = KeyPair.generate(bitLength);
        byte[]        bytes = message.getBytes();
        Signature signature = keyPair.sign(bytes);
        assertTrue(signature.check(bytes,keyPair.publicKey));
    }

    @Test
    void invalid_2() {
        invalid("in",2);
    }

    @Test
    void invalid_2_1000() {
        for (int i=0; i<1000; i++) {
            invalid(Integer.toString(i),2);
        }
    }

    @Test
    void invalid_5_100() {
        for (int i=0; i<100; i++) {
            invalid(Integer.toString(i),2);
        }
    }

    @Test
    void invalid_5_1000() {
        for (int i=0; i<1000; i++) {
            invalid(Integer.toString(i),5);
        }
    }

    @Test
    void invalid_5_10000() {
        for (int i=0; i<10000; i++) {
            invalid(Integer.toString(i),5);
        }
    }

    @Test
    void invalid_10() {
        invalid("rain",10);
    }

    @Test
    void invalid_100() {
        invalid("sprain",100);
    }

    void invalid(String message,int bitLength) {
        KeyPair     keyPair = KeyPair.generate(bitLength);
        byte[]        bytes = message.getBytes();
        Signature signature = keyPair.sign(bytes);
        assertFalse(signature.check(flip(bytes),keyPair.publicKey));
    }

    private byte[] flip(byte[] bytes) {
        byte[] out = new byte[bytes.length];
        for (int i=0; i<out.length; i++) {
            byte in = bytes[i];
            byte mask = 0x01;
            out[i] = (byte) (in ^ mask);
        }
        return out;
    }

}
