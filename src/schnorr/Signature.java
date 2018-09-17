package schnorr;

import java.math.*;
import java.security.*;

/**
 * Created by Dmitrii on 17.11.2016.
 */
class Signature {

    boolean checkSign(byte[] bytes, PublicKey publicKey, SignKey sign) throws NoSuchAlgorithmException
     {
        println("cheking sign");
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

    byte[] md5(byte[] bytes, BigInteger x) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(bytes);
        md5.update(x.toString().getBytes());

        return md5.digest();
    }

    SignKey makeSign(byte[] bytes, PublicKey publicKey, PrivateKey privateKey) throws NoSuchAlgorithmException {
        BigInteger q = publicKey.q;
        BigInteger p = publicKey.p;
        BigInteger g = publicKey.g;
        BigInteger y = publicKey.y;
        BigInteger w = privateKey.w;

        SecureRandom sr = new SecureRandom();
        BigInteger r, x, W, s2, s1;
        r = new BigInteger(q.bitLength(), sr);
        x = g.modPow(r, p);

        byte[] digest = md5(bytes,x);

        s1 = new BigInteger(1, digest);
        s2 = (r.subtract(w.multiply(s1))).mod(q);

        return new SignKey(s1, s2);
    }

    static void println(String message) {
         System.out.println(message);
    }
}
