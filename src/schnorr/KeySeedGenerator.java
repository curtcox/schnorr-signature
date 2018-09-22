package schnorr;

import java.math.BigInteger;
import java.security.SecureRandom;

final class KeySeedGenerator {

    private final int bitLength;
    private final SecureRandom sr = new SecureRandom();
    private static final BigInteger one = new BigInteger("1");
    private static final BigInteger two = new BigInteger("2");
    private static final int certainty = 100;

    KeySeedGenerator(int bitLength) {
        this.bitLength = bitLength;
    }

    KeySeed generate() {
        BigInteger q = randomPrime();
        BigInteger p = compute_p(q);
        BigInteger g = compute_g(p,q);
        return new KeySeed(bitLength,q,p,g);
    }

    BigInteger randomPrime() { return new BigInteger(bitLength, certainty, sr); }

    private BigInteger compute_p(BigInteger q) {
        BigInteger qp = one;
        BigInteger p;
        do {
            p = q.multiply(qp).multiply(two).add(one);
            if (p.isProbablePrime(certainty)) {
                return p;
            }
            qp = qp.add(one);
        } while (true);
    }

    private BigInteger compute_g(BigInteger p, BigInteger q) {
        BigInteger g;
        while (true) {
            BigInteger  a = (two.add( randomPrime() )).mod(p);
            BigInteger ga = (p.subtract(one)).divide(q);
            g = a.modPow(ga, p);
            if (g.compareTo(one) != 0) {
                return g;
            }
        }
    }

}
