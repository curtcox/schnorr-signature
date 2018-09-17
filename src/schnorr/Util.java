package schnorr;

import java.math.*;
import java.security.*;

/**
 * Created by Dmitrii on 17.11.2016.
 */
class Util {
    
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

}
