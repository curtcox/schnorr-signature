package schnorr;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

public class KeySeedGeneratorTest {

    @Test
    void bitLength_10() {
        valid(10);
    }

    @Test
    void bitLength_100() {
        valid(100);
    }

    @Test
    void bitLength_500() {
        valid(500);
    }

    private void valid(int bitLength) {
        KeySeedGenerator generator = new KeySeedGenerator(bitLength);

        KeySeed seed = generator.generate();

        assertTarget(bitLength,seed.g);
        assertTarget(bitLength,seed.p);
        assertTarget(bitLength,seed.q);
    }

    private void assertTarget(int target, BigInteger big) {
        int length = big.bitLength();
        double ratio = (double)length / (double)target;
        boolean ok = ratio > 0.6 && ratio < 1.5;
        String message = "Unexpected " + length + "/" + target + " = " + ratio;
        assertTrue(ok,message);
    }
}
