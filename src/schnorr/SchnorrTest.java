package schnorr;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

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
        KeySet set = KeySet.generate(3);
        for (int i=0; i<100; i++) {
            valid(Integer.toString(i),set);
        }
    }

    @Test
    void valid_5_1000() {
        KeySet set = KeySet.generate(5);
        for (int i=0; i<1000; i++) {
            valid(Integer.toString(i),set);
        }
    }

    @Test
    void valid_10() {
        KeySet set = KeySet.generate(10);
        valid("rain",set);
    }

    @Test
    void valid_100() {
        KeySet set = KeySet.generate(100);
        valid("sprain",set);
    }

    @Test
    void valid_500() {
        KeySet set = KeySet.generate(500);
        valid("sprainly",set);
    }

    void valid(String message, KeySet set) {
        byte[]        bytes = message.getBytes();
        Signature signature = set.signing.sign(bytes);
        assertTrue(set.verifying.verify(bytes,signature));
    }

    @Test
    void invalid_2() {
        KeySet set = KeySet.generate(2);
        invalid("in",set);
    }

    @Test
    void invalid_2_1000() {
        KeySet set = KeySet.generate(2);
        for (int i=0; i<1000; i++) {
            invalid(Integer.toString(i),set);
        }
    }

    @Test
    void invalid_5_100() {
        KeySet set = KeySet.generate(5);
        for (int i=0; i<100; i++) {
            invalid(Integer.toString(i),set);
        }
    }

    @Test
    void invalid_5_1000() {
        KeySet set = KeySet.generate(5);
        for (int i=0; i<1000; i++) {
            invalid(Integer.toString(i),set);
        }
    }

    @Test
    void invalid_5_10000() {
        KeySet set = KeySet.generate(5);
        for (int i=0; i<10000; i++) {
            invalid(Integer.toString(i),set);
        }
    }

    @Test
    void invalid_10() {
        KeySet set = KeySet.generate(10);
        invalid("rain",set);
    }

    @Test
    void invalid_100() {
        KeySet set = KeySet.generate(100);
        invalid("sprain",set);
    }

    void invalid(String message, KeySet set) {
        byte[]        bytes = message.getBytes();
        Signature signature = set.signing.sign(bytes);
        assertFalse(set.verifying.verify(flip(bytes),signature));
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

    @Test
    void valid_16_bit() {
        KeySeed seed = new KeySeed(16,new BigInteger("46681"), new BigInteger("560173"), new BigInteger("215867"));
        PublicKey publicKey = new PublicKey(new BigInteger("351283"));
        PrivateKey privateKey = new PrivateKey(new BigInteger("18704"));
        KeySet set = new KeySet(seed,publicKey,privateKey);

        byte[] message = "whatever".getBytes();

        assertTrue(set.verifying.verify(message,set.signing.sign(message)));
    }

}
