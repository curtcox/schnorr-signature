package schnorr;

import java.math.BigInteger;

final class PublicKey {

    final BigInteger y;

    PublicKey(BigInteger y) {
        this.y = y;
    }

    public String toString() {
        return "< y=" + y + " >";
    }
}
