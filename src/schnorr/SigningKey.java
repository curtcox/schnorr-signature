package schnorr;

import java.math.BigInteger;
import java.security.SecureRandom;

final class SigningKey {

    final KeySeed seed;
    final PrivateKey privateKey;

    SigningKey(KeySeed seed, PrivateKey privateKey) {
        this.seed = seed;
        this.privateKey = privateKey;
    }

    Signature sign(byte[] bytes) {
        BigInteger q = seed.q;
        BigInteger w = privateKey.w;
        BigInteger r = new BigInteger(q.bitLength(), new SecureRandom());

        BigInteger s1 = s1(bytes,r);
        BigInteger s2 = r.subtract(w.multiply(s1)).mod(q);

        return new Signature(s1, s2);
    }

    BigInteger s1(byte[] bytes,BigInteger r) {
        BigInteger p = seed.p;
        BigInteger g = seed.g;
        BigInteger x = g.modPow(r, p);

        return Util.bigMd5(bytes,x);
    }

    public String toString() {
        return "< seed=" + seed + " privateKey=" + privateKey + " >";
    }
}
