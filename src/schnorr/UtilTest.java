package schnorr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UtilTest {

    @Test
    void can_create() {
        assertNotNull(new Util());
    }

}
