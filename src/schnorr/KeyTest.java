package schnorr;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class KeyTest {

    @Test
    void can_create() {
        assertNotNull(new Key(new BigInteger[0]));
    }

}
