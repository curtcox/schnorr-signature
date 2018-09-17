package schnorr;

import java.io.*;
import java.math.*;
import java.nio.file.*;
import java.security.*;

/**
 * Created by Dmitrii on 17.11.2016.
 */
class Signature {

    boolean checkSign(byte[] bytes, PublicKey publicKey, Key sign) throws NoSuchAlgorithmException
     {
        println("cheking sign");
        BigInteger q = publicKey.q;
        BigInteger p = publicKey.p;
        BigInteger g = publicKey.g;
        BigInteger y = publicKey.y;
        BigInteger s1 = sign.get(0);
        BigInteger s2 = sign.get(1);

        BigInteger x1 = g.modPow(s2, p);
        BigInteger x2 = (y.modPow(s1, p)).mod(p);
        BigInteger x = x1.multiply(x2).mod(p);

        byte[] digest55 = md5(bytes,x);

        BigInteger hh = new BigInteger(1, digest55);

        return s1.equals(hh);
    }

    byte[] md5(byte[] bytes, BigInteger x) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(bytes);
        md5.update(x.toString().getBytes());

        return md5.digest();
    }

    void makeSign(String path, PublicKey publicKey, String pathPrivateKey, String pathSign) throws IOException, NoSuchAlgorithmException {
        Key privateKey = Key.readFromFile(pathPrivateKey);
        BigInteger q = publicKey.q;
        BigInteger p = publicKey.p;
        BigInteger g = publicKey.g;
        BigInteger y = publicKey.y;
        BigInteger w = privateKey.get(0);

        SecureRandom sr = new SecureRandom();
        BigInteger r, x, W, s2, s1;
        r = new BigInteger(q.bitLength(), sr);
        x = g.modPow(r, p);

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(Files.readAllBytes(Paths.get(path)));;
        md5.update(x.toString().getBytes());
        byte[] digest = md5.digest();
        s1 = new BigInteger(1, digest);
        s2 = (r.subtract(w.multiply(s1))).mod(q);

        Key sign = new Key(new BigInteger[]{s1, s2});
        sign.writeToFile(pathSign);
        println("Success!");
    }

    KeyPair generate(int blq, String pathPrivateKey) throws FileNotFoundException {
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
