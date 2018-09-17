package schnorr;

import java.io.FileNotFoundException;
import java.math.BigInteger;

final class PrivateKey {

    final BigInteger w;

    PrivateKey(BigInteger w) {
        this.w = w;
    }

    static PrivateKey readFromFile(String path) throws FileNotFoundException {
        Key key = Key.readFromFile(path);
        return new PrivateKey(key.get(0));
    }
}
