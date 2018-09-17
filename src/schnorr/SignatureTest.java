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
    void generate() throws Exception {
        Signature signature = new Signature();
        int blq = 0;
        String pathPrivateKey = "";
        KeyPair keyPair = signature.generate(blq,pathPrivateKey);
    }

    @Test
    void makeSign() throws Exception {
        Signature signature = new Signature();
        String path ="";
        PublicKey publicKey = new PublicKey(
                new BigInteger("0"),
                new BigInteger("0"),
                new BigInteger("0"),
                new BigInteger("0")
        );
        String pathPrivateKey="";
        String pathSign="";
        signature.makeSign(path,publicKey,pathPrivateKey,pathSign);
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
        Key sign = new Key(new BigInteger[0]);
        signature.checkSign(bytes,publicKey,sign);
    }

}
