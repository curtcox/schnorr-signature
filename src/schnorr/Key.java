package schnorr;

import java.math.*;
import java.util.*;

/**
 * Created by Dmitrii on 17.11.2016.
 */
class Key {

    private final List<BigInteger> entity = new ArrayList<>();

    Key(BigInteger[] keys) {
        for (int i = 0; i < keys.length; i++){
            entity.add(keys[i]);
        }
    }

    BigInteger get(int idx) {
        if (idx >= entity.size()) {
            throw new IllegalArgumentException("idx more then size");
        }
        return entity.get(idx);
    }

}
