package schnorr;

import java.math.BigInteger;

final class KeySeed {

    final int bitLength;
    final BigInteger q;
    final BigInteger p;
    final BigInteger g;

    KeySeed(int bitLength, BigInteger q, BigInteger p, BigInteger g) {
        this.bitLength = bitLength;
        this.q = q;
        this.p = p;
        this.g = g;
    }

    public String toString() {
        return "< bitLength=" + bitLength + " q=" + q + " p=" + p + " g=" + g +" >";
    }
}
