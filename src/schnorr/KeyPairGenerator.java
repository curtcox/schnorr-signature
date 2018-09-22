package schnorr;

import java.math.BigInteger;
import java.security.SecureRandom;

final class KeyPairGenerator {

    private final KeySeed seed;
    private final SecureRandom sr = new SecureRandom();

    KeyPairGenerator(KeySeed seed) {
        this.seed = seed;
    }

    KeySet generate() {
        BigInteger g = seed.g;
        BigInteger p = seed.p;
        BigInteger w = random();
        BigInteger y = g.modPow(w, p);
        return new KeySet(seed, new PublicKey(y), new PrivateKey(w));
    }

    BigInteger random() {
        return new BigInteger(seed.bitLength, sr);
    }


}
