package schnorr;

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

    public String toString() {
        return "< q=" + q + " p=" + p + " g=" + g + " y=" + y + " >";
    }
}
