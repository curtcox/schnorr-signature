package schnorr;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UtilTest {

    @Test
    void can_create() {
        assertNotNull(new Util());
    }

    @Test
    void checkSign() throws Exception {
        Util util = new Util();
        byte[] bytes = "".getBytes();
        PublicKey publicKey = new PublicKey(
                new BigInteger("0"),
                new BigInteger("0"),
                new BigInteger("0"),
                new BigInteger("0")
        );
        SignKey sign = new SignKey(new BigInteger("0"),new BigInteger("0"));
        util.checkSign(bytes,publicKey,sign);
    }

}
