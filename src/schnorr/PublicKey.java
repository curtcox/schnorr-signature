package schnorr;

import java.io.FileNotFoundException;
import java.math.BigInteger;

final class PublicKey {

    final BigInteger q;
    final BigInteger p;
    final BigInteger g;
    final BigInteger y;

    PublicKey(BigInteger q, BigInteger p, BigInteger g, BigInteger y) {
        this.q = q;
        this.p = p;
        this.g = g;
        this.y = y;
    }

    static PublicKey readFromFile(String path) throws FileNotFoundException {
        Key key = Key.readFromFile(path);
        return new PublicKey(key.get(0),key.get(1),key.get(2),key.get(3));
    }
}
