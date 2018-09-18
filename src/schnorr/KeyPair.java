package schnorr;

import java.math.BigInteger;
import java.security.SecureRandom;

class KeyPair {

    final PublicKey publicKey;
    final PrivateKey privateKey;
    static final int certainty = 100;


    KeyPair(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    static KeyPair generate(int blq) {
        SecureRandom sr = new SecureRandom();
        BigInteger q = new BigInteger(blq, certainty, sr);

        BigInteger p = compute_p(q);
        BigInteger g = compute_g(blq,p,q,sr);
        BigInteger w = new BigInteger(blq, sr);
        BigInteger y = g.modPow(w, p);

        return new KeyPair(new PublicKey(q, p, g, y), new PrivateKey(w));
    }

    static BigInteger compute_p(BigInteger q) {
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

    static BigInteger compute_g(int blq,BigInteger p, BigInteger q, SecureRandom sr) {
        BigInteger g;
        BigInteger two = new BigInteger("2");
        while (true) {
            BigInteger  a = (two.add(new BigInteger(blq, 100, sr))).mod(p);
            BigInteger ga = (p.subtract(BigInteger.ONE)).divide(q);
            g = a.modPow(ga, p);
            if (g.compareTo(BigInteger.ONE) != 0)
                break;
        }
        return g;
    }

    Signature sign(byte[] bytes) {
        BigInteger q = publicKey.q;
        BigInteger p = publicKey.p;
        BigInteger g = publicKey.g;
        BigInteger w = privateKey.w;

        SecureRandom sr = new SecureRandom();

        BigInteger r = new BigInteger(q.bitLength(), sr);
        BigInteger x = g.modPow(r, p);

        byte[] digest = Util.md5(bytes,x);

        BigInteger s1 = new BigInteger(1, digest);
        BigInteger s2 = (r.subtract(w.multiply(s1))).mod(q);

        return new Signature(s1, s2);
    }

    public String toString() {
        return "public = " + publicKey + " private = " + privateKey;
    }

}
