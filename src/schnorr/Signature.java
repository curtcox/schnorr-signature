package schnorr;

import java.math.BigInteger;

final class Signature {

    final BigInteger s1;
    final BigInteger s2;

    Signature(BigInteger s1, BigInteger s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public String toString() {
        return "< s1=" + s1 + " s2=" + s2 + " >";
    }
}
