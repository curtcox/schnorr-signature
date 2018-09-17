package schnorr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class KeyPairTest {

    @Test
    void generate() {
        int blq = 0;
        KeyPair keyPair = KeyPair.generate(blq);
        assertNotNull(keyPair);
    }

}
