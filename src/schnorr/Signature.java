package schnorr;

import java.math.BigInteger;

final class Signature {

    final BigInteger s1;
    final BigInteger s2;

    Signature(BigInteger s1, BigInteger s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    boolean check(byte[] bytes, KeySeed seed, PublicKey publicKey) {
        BigInteger p = seed.p;
        BigInteger g = seed.g;
        BigInteger y = publicKey.y;

        BigInteger x1 = g.modPow(s2, p);
        BigInteger x2 = (y.modPow(s1, p)).mod(p);
        BigInteger x = x1.multiply(x2).mod(p);

        BigInteger hh = Util.bigMd5(bytes,x);

        return s1.equals(hh);
    }

    public String toString() {
        return "< s1=" + s1 + " s2=" + s2 + " >";
    }
}
