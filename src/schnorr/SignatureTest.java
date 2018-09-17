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
    void checkSign() throws Exception {
        byte[] bytes = "".getBytes();
        PublicKey publicKey = new PublicKey(
                new BigInteger("0"),
                new BigInteger("0"),
                new BigInteger("0"),
                new BigInteger("0")
        );
        Signature sign = new Signature(new BigInteger("0"),new BigInteger("0"));
        sign.check(bytes,publicKey);
    }

}
