package schnorr;

import java.math.*;
import java.security.*;

/**
 * Created by Dmitrii on 17.11.2016.
 */
class Util {

    boolean checkSign(byte[] bytes, PublicKey publicKey, SignKey sign) {
        println("checking sign");
        BigInteger q = publicKey.q;
        BigInteger p = publicKey.p;
        BigInteger g = publicKey.g;
        BigInteger y = publicKey.y;
        BigInteger s1 = sign.s1;
        BigInteger s2 = sign.s2;

        BigInteger x1 = g.modPow(s2, p);
        BigInteger x2 = (y.modPow(s1, p)).mod(p);
        BigInteger x = x1.multiply(x2).mod(p);

        byte[] digest55 = md5(bytes,x);

        BigInteger hh = new BigInteger(1, digest55);

        return s1.equals(hh);
    }

    static byte[] md5(byte[] bytes, BigInteger x) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(bytes);
            md5.update(x.toString().getBytes());
            return md5.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    static void println(String message) {
         System.out.println(message);
    }
}
