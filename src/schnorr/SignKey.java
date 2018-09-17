package schnorr;

import java.io.FileNotFoundException;
import java.math.BigInteger;

final class SignKey {

    final BigInteger s1;
    final BigInteger s2;

    SignKey(BigInteger s1, BigInteger s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    static SignKey readFromFile(String path) throws FileNotFoundException {
        Key key = Key.readFromFile(path);
        return new SignKey(key.get(0),key.get(1));
    }
}
