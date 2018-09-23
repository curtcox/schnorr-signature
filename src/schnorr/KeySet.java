package schnorr;

class KeySet {

    final SigningKey signing;
    final VerifyingKey verifying;

    static KeySet generate(int bitLength) {
        KeySeed seed = new KeySeedGenerator(bitLength).generate();
        return new KeyPairGenerator(seed).generate();
    }

    KeySet(KeySeed seed, PublicKey publicKey, PrivateKey privateKey) {
        this.signing = new SigningKey(seed,privateKey);
        this.verifying = new VerifyingKey(seed,publicKey);
    }

    public String toString() {
        return "signing = " + signing + " verifying = " + verifying;
    }

}
