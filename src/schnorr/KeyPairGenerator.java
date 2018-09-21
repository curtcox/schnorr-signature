package schnorr;

import java.math.BigInteger;
import java.security.SecureRandom;

final class KeyPairGenerator {

    private final int bitLength;
    private static final int certainty = 100;

    public KeyPairGenerator(int bitLength) {
        this.bitLength = bitLength;
    }

    KeyPair generate() {
        SecureRandom sr = new SecureRandom();
        BigInteger q = new BigInteger(bitLength, certainty, sr);

        BigInteger p = compute_p(q);
        BigInteger g = compute_g(bitLength,p,q,sr);
        BigInteger w = new BigInteger(bitLength, sr);
        BigInteger y = g.modPow(w, p);

        return new KeyPair(new PublicKey(q, p, g, y), new PrivateKey(w));
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

    private BigInteger compute_g(int bitLength,BigInteger p, BigInteger q, SecureRandom sr) {
        BigInteger g;
        while (true) {
            BigInteger two = new BigInteger("2");
            BigInteger  a = (two.add(new BigInteger(bitLength, certainty, sr))).mod(p);
            BigInteger ga = (p.subtract(BigInteger.ONE)).divide(q);
            g = a.modPow(ga, p);
            if (g.compareTo(BigInteger.ONE) != 0)
                break;
        }
        return g;
    }

}
