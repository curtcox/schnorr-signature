package schnorr;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class KeySetTest {

    @Test
    void makeSign() {
        byte[] bytes = "".getBytes();
        KeySeed seed = new KeySeed(
                0,
                new BigInteger("0"),
                new BigInteger("0"),
                new BigInteger("0")
        );
        PublicKey publicKey = new PublicKey(
                new BigInteger("0")
        );
        PrivateKey privateKey = new PrivateKey(new BigInteger("0"));
        KeySet keyPair = new KeySet(seed,publicKey,privateKey);
        Signature signature = keyPair.sign(bytes);
        assertNotNull(signature);
    }

}
