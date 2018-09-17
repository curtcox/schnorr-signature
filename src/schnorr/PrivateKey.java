package schnorr;

import java.math.BigInteger;

final class PrivateKey {

    final BigInteger w;

    PrivateKey(BigInteger w) {
        this.w = w;
    }

    public String toString() {
        return "< w=" + w + " >";
    }

}
