package schnorr;

import java.math.BigInteger;
import java.security.SecureRandom;

class KeyPair {

    final PublicKey publicKey;
    final PrivateKey privateKey;

    KeyPair(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    static KeyPair generate(int blq) {
        println("generating:");

        BigInteger one = new BigInteger("1");
        BigInteger two = new BigInteger("2");
        BigInteger q, qp, p, a, g, w, y;
        int certainty = 100;
        SecureRandom sr = new SecureRandom();
        q = new BigInteger(blq, certainty, sr);

        qp = BigInteger.ONE;

        do {
            p = q.multiply(qp).multiply(two).add(one);
            if (p.isProbablePrime(certainty)) break;
            qp = qp.add(BigInteger.ONE);
        } while (true);

        while (true) {
            a = (two.add(new BigInteger(blq, 100, sr))).mod(p);
            BigInteger ga = (p.subtract(BigInteger.ONE)).divide(q);
            g = a.modPow(ga, p);
            if (g.compareTo(BigInteger.ONE) != 0)
                break;
        }

        w = new BigInteger(blq, sr);
        y = g.modPow(w, p);

        PublicKey publicKey = new PublicKey(q, p, g, y);
        println("public key:");
        println("q = " + q);
        println("p = " + p);
        println("g = " + g);
        println("y = " + y);

        PrivateKey privateKey = new PrivateKey(w);
        println("private key:");
        println("w = "+ w);

        return new KeyPair(publicKey,privateKey);
    }

    static void println(String message) {
        System.out.println(message);
    }

}
