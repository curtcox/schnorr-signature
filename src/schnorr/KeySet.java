package schnorr;

import java.math.BigInteger;
import java.security.SecureRandom;

class KeySet {

    final KeySeed seed;
    final PublicKey publicKey;
    final PrivateKey privateKey;

    static KeySet generate(int bitLength) {
        KeySeed seed = new KeySeedGenerator(bitLength).generate();
        return new KeyPairGenerator(seed).generate();
    }

    KeySet(KeySeed seed, PublicKey publicKey, PrivateKey privateKey) {
        this.seed = seed;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    Signature sign(byte[] bytes) {
        BigInteger q = seed.q;
        BigInteger p = seed.p;
        BigInteger g = seed.g;
        BigInteger w = privateKey.w;

        SecureRandom sr = new SecureRandom();

        BigInteger r = new BigInteger(q.bitLength(), sr);
        BigInteger x = g.modPow(r, p);

        BigInteger s1 = Util.bigMd5(bytes,x);
        BigInteger s2 = (r.subtract(w.multiply(s1))).mod(q);

        return new Signature(s1, s2);
    }


    public String toString() {
        return "seed = " + seed + " public = " + publicKey + " private = " + privateKey;
    }

}
