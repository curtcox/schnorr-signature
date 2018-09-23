package schnorr;

final class VerifyingKey {

    final KeySeed seed;
    final PublicKey publicKey;

    VerifyingKey(KeySeed seed, PublicKey publicKey) {
        this.seed = seed;
        this.publicKey = publicKey;
    }

    public String toString() {
        return "< seed=" + seed + " publicKey=" + publicKey + " >";
    }
}
