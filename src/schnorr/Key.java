package schnorr;

import java.io.*;
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

    static Key readFromFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(path));
        List<BigInteger> entity = new ArrayList<>();
        while (scanner.hasNextBigInteger()){
            entity.add(scanner.nextBigInteger());
        }
        scanner.close();
        return new Key(entity.toArray(new BigInteger[0]));
    }

    void writeToFile(String path) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File(path));
        for (BigInteger element : entity){
            printWriter.println(element);
        }
        printWriter.close();
    }
}
