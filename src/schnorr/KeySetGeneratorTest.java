package schnorr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class KeySetGeneratorTest {

    @Test
    void can_generate() {
        int bitLength = 0;
        KeySeed seed = new KeySeedGenerator(bitLength).generate();
        KeySet keyPair = new KeyPairGenerator(seed).generate();
        assertNotNull(keyPair);
    }

    @Test
    void bitLength_5() {
        int bitLength = 5;
        KeySeed seed = new KeySeedGenerator(bitLength).generate();
        KeySet keyPair = new KeyPairGenerator(seed).generate();

        System.out.println(keyPair);
    }

    @Test
    void bitLength_500() {
        int bitLength = 500;
        KeySeed seed = new KeySeedGenerator(bitLength).generate();
        KeySet keyPair = new KeyPairGenerator(seed).generate();

        System.out.println(keyPair);
    }

}
