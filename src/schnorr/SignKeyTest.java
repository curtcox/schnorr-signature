package schnorr;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SignKeyTest {

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
        SignKey sign = new SignKey(new BigInteger("0"),new BigInteger("0"));
        sign.checkSign(bytes,publicKey,sign);
    }

}
