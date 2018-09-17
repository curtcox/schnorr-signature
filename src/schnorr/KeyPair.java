package schnorr;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
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

    Signature sign(byte[] bytes, PublicKey publicKey, PrivateKey privateKey) {
        BigInteger q = publicKey.q;
        BigInteger p = publicKey.p;
        BigInteger g = publicKey.g;
        BigInteger y = publicKey.y;
        BigInteger w = privateKey.w;

        SecureRandom sr = new SecureRandom();
        BigInteger r, x, W, s2, s1;
        r = new BigInteger(q.bitLength(), sr);
        x = g.modPow(r, p);

        byte[] digest = Util.md5(bytes,x);

        s1 = new BigInteger(1, digest);
        s2 = (r.subtract(w.multiply(s1))).mod(q);

        return new Signature(s1, s2);
    }


    static void println(String message) {
        System.out.println(message);
    }

}
