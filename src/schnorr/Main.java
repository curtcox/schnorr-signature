package schnorr;

import java.io.*;
import java.util.*;
import java.security.*;

 class Main {

    static String pathSign = "Sign.txt";
    static String pathPublicKey = "PublicKey.txt";
    static String pathPrivateKey = "PrivateKey.txt";

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Scanner s = new Scanner(System.in);
        System.out.println("1 <bits in q> - generate keys");
        System.out.println("2 <file path> - make sign");
        System.out.println("3 <file path> - check sign");

        int type;
        Signature signature = new Signature();
        while (true) {
            type = s.nextInt();
            if (type == 1) {
                int blq = s.nextInt();
                signature.generate(blq, pathPublicKey, pathPrivateKey);
            }
            if (type == 2) {
                String pathFile = s.next();
                signature.makeSign(pathFile, pathPublicKey, pathPrivateKey, pathSign);
            }
            if (type == 3) {
                String pathFile = s.next();
                signature.checkSign(pathFile, pathPublicKey, pathSign);
            }
            if (type == 4){
                break;
            }
        }
    }
}