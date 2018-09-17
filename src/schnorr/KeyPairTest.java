package schnorr;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class KeyPairTest {

    @Test
    void generate() {
        int blq = 0;
        KeyPair keyPair = KeyPair.generate(blq);
        assertNotNull(keyPair);
    }

    @Test
    void makeSign() throws Exception {
        byte[] bytes = "".getBytes();
        PublicKey publicKey = new PublicKey(
                new BigInteger("0"),
                new BigInteger("0"),
                new BigInteger("0"),
                new BigInteger("0")
        );
        PrivateKey privateKey = new PrivateKey(new BigInteger("0"));
        KeyPair keyPair = new KeyPair(publicKey,privateKey);
        Signature signature = keyPair.sign(bytes);
        assertNotNull(signature);
    }

}
