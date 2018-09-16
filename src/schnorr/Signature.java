package schnorr;

import java.io.*;
import java.math.*;
import java.nio.file.*;
import java.security.*;

/**
 * Created by Dmitrii on 17.11.2016.
 */
class Signature {

    void checkSign(String path, String pathPublicKey, String pathSign)
             throws IOException, NoSuchAlgorithmException
     {
        println("cheking sign");
        Key publicKey = new Key(pathPublicKey);
        Key sign = new Key(pathSign);
        BigInteger q = publicKey.get(0);
        BigInteger p = publicKey.get(1);
        BigInteger g = publicKey.get(2);
        BigInteger y = publicKey.get(3);
        BigInteger s1 = sign.get(0);
        BigInteger s2 = sign.get(1);

        BigInteger x1 = g.modPow(s2, p);
        BigInteger x2 = (y.modPow(s1, p)).mod(p);
        BigInteger x = x1.multiply(x2).mod(p);

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(Files.readAllBytes(Paths.get(path)));;
        md5.update(x.toString().getBytes());

        byte[] digest55 = md5.digest();
        BigInteger hh = new BigInteger(1, digest55);
        if (s1.equals(hh))
            println("Schnorr signature is valid");
        else
            println("Schnorr signature is not valid");
    }

    void makeSign(String path, String pathPublicKey, String pathPrivateKey, String pathSign) throws IOException, NoSuchAlgorithmException {
        Key publicKey = new Key(pathPublicKey);
        Key privateKey = new Key(pathPrivateKey);
        BigInteger q = publicKey.get(0);
        BigInteger p = publicKey.get(1);
        BigInteger g = publicKey.get(2);
        BigInteger y = publicKey.get(3);
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

    void generate(int blq, String pathPublicKey, String pathPrivateKey) throws FileNotFoundException {
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

        Key publicKey = new Key(new BigInteger[]{q, p, g, y});
        publicKey.writeToFile(pathPublicKey);
        println("public key:");
        println("q = " + q);
        println("p = " + p);
        println("g = " + g);
        println("y = " + y);

        Key privateKey = new Key(new BigInteger[]{w});
        privateKey.writeToFile(pathPrivateKey);
        println("private key:");
        println("w = "+ w);
    }

    static void println(String message) {
         System.out.println(message);
    }
}
