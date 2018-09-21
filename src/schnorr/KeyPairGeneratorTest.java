package schnorr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class KeyPairGeneratorTest {

    @Test
    void can_generate() {
        int bitLength = 0;
        KeyPair keyPair = KeyPair.generate(bitLength);
        assertNotNull(keyPair);
    }

    @Test
    void bitLength_5() {
        int bitLength = 5;
        KeyPair keyPair = KeyPair.generate(bitLength);

        System.out.println(keyPair);
    }

    @Test
    void bitLength_500() {
        int bitLength = 500;
        KeyPair keyPair = KeyPair.generate(bitLength);

        System.out.println(keyPair);
    }

}
