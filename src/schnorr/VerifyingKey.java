package schnorr;

import java.math.BigInteger;

final class VerifyingKey {

    final KeySeed seed;
    final PublicKey publicKey;

    VerifyingKey(KeySeed seed, PublicKey publicKey) {
        this.seed = seed;
        this.publicKey = publicKey;
    }

    boolean verify(byte[] bytes, Signature signature) {
        return signature.s1.equals(hh(bytes,signature));
    }

    BigInteger hh(byte[] bytes,Signature signature) {
        return Util.bigMd5(bytes,x(signature));
    }

    BigInteger x(Signature signature) {
        BigInteger p = seed.p;
        BigInteger g = seed.g;
        BigInteger y = publicKey.y;

        BigInteger x1 = g.modPow(signature.s2, p);
        BigInteger x2 = y.modPow(signature.s1, p).mod(p);

        return  x1.multiply(x2).mod(p);
    }


    public String toString() {
        return "< seed=" + seed + " publicKey=" + publicKey + " >";
    }
}
