package schnorr;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SignatureTest {

    @Test
    void can_create() {
        assertNotNull(new Util());
    }

    @Test
    void checkSign() {
        byte[] bytes = "".getBytes();
        KeySeed seed = new KeySeed(
                0,
                new BigInteger("0"),
                new BigInteger("0"),
                new BigInteger("0")
        );
        PublicKey publicKey = new PublicKey(new BigInteger("0"));
        VerifyingKey verifyingKey = new VerifyingKey(seed,publicKey);
        Signature sign = new Signature(new BigInteger("0"),new BigInteger("0"));
        verifyingKey.verify(bytes,sign);
    }

}
