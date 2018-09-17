package schnorr;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SignatureTest {

    @Test
    void can_create() {
        assertNotNull(new Signature());
    }

    @Test
    void checkSign() throws Exception {
        Signature signature = new Signature();
        byte[] bytes = "".getBytes();
        PublicKey publicKey = new PublicKey(
                new BigInteger("0"),
                new BigInteger("0"),
                new BigInteger("0"),
                new BigInteger("0")
        );
        SignKey sign = new SignKey(new BigInteger("0"),new BigInteger("0"));
        signature.checkSign(bytes,publicKey,sign);
    }

}