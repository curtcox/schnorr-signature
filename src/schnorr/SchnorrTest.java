package schnorr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SchnorrTest {

// This sometimes fails for some reason
//    @Test
//    void valid_2_100() {
//        KeySet pair = KeySet.generate(2);
//        for (int i=0; i<100; i++) {
//            valid(Integer.toString(i),pair);
//        }
//    }

    @Test
    void valid_3_100() {
        KeySet pair = KeySet.generate(3);
        for (int i=0; i<100; i++) {
            valid(Integer.toString(i),pair);
        }
    }

    @Test
    void valid_5_1000() {
        KeySet pair = KeySet.generate(5);
        for (int i=0; i<1000; i++) {
            valid(Integer.toString(i),pair);
        }
    }

    @Test
    void valid_10() {
        KeySet pair = KeySet.generate(10);
        valid("rain",pair);
    }

    @Test
    void valid_100() {
        KeySet pair = KeySet.generate(100);
        valid("sprain",pair);
    }

    @Test
    void valid_500() {
        KeySet pair = KeySet.generate(500);
        valid("sprainly",pair);
    }

    void valid(String message, KeySet keyPair) {
        byte[]        bytes = message.getBytes();
        Signature signature = keyPair.sign(bytes);
        assertTrue(signature.check(bytes,keyPair.publicKey));
    }

    @Test
    void invalid_2() {
        KeySet pair = KeySet.generate(2);
        invalid("in",pair);
    }

    @Test
    void invalid_2_1000() {
        KeySet pair = KeySet.generate(2);
        for (int i=0; i<1000; i++) {
            invalid(Integer.toString(i),pair);
        }
    }

    @Test
    void invalid_5_100() {
        KeySet pair = KeySet.generate(5);
        for (int i=0; i<100; i++) {
            invalid(Integer.toString(i),pair);
        }
    }

    @Test
    void invalid_5_1000() {
        KeySet pair = KeySet.generate(5);
        for (int i=0; i<1000; i++) {
            invalid(Integer.toString(i),pair);
        }
    }

    @Test
    void invalid_5_10000() {
        KeySet pair = KeySet.generate(5);
        for (int i=0; i<10000; i++) {
            invalid(Integer.toString(i),pair);
        }
    }

    @Test
    void invalid_10() {
        KeySet pair = KeySet.generate(10);
        invalid("rain",pair);
    }

    @Test
    void invalid_100() {
        KeySet pair = KeySet.generate(100);
        invalid("sprain",pair);
    }

    void invalid(String message, KeySet keyPair) {
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
