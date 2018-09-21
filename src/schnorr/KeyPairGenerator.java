package schnorr;

import java.math.BigInteger;
import java.security.SecureRandom;

final class KeyPairGenerator {

    private final int bitLength;
    private final SecureRandom sr = new SecureRandom();
    private static final int certainty = 100;

    public KeyPairGenerator(int bitLength) {
        this.bitLength = bitLength;
    }

    KeyPair generate() {
        BigInteger q = randomPrime();
        BigInteger p = compute_p(q);
        BigInteger g = compute_g(p,q);
        BigInteger w = new BigInteger(bitLength, sr);
        BigInteger y = g.modPow(w, p);

        return new KeyPair(new PublicKey(q, p, g, y), new PrivateKey(w));
    }

    BigInteger randomPrime() {
        return new BigInteger(bitLength, certainty, sr);
    }

    private BigInteger compute_p(BigInteger q) {
        BigInteger one = new BigInteger("1");
        BigInteger two = new BigInteger("2");
        BigInteger qp = BigInteger.ONE;
        BigInteger p;
        do {
            p = q.multiply(qp).multiply(two).add(one);
            if (p.isProbablePrime(certainty)) break;
            qp = qp.add(BigInteger.ONE);
        } while (true);
        return p;
    }

    private BigInteger compute_g(BigInteger p, BigInteger q) {
        BigInteger g;
        while (true) {
            BigInteger two = new BigInteger("2");
            BigInteger  a = (two.add( randomPrime() )).mod(p);
            BigInteger ga = (p.subtract(BigInteger.ONE)).divide(q);
            g = a.modPow(ga, p);
            if (g.compareTo(BigInteger.ONE) != 0)
                break;
        }
        return g;
    }

}
