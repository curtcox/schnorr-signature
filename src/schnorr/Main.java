package schnorr;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.security.*;

 class Main {

    static String pathSign = "Sign.txt";
    static String pathPublicKey = "PublicKey.txt";
    static String pathPrivateKey = "PrivateKey.txt";

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Scanner s = new Scanner(System.in);
        println("1 <bits in q> - generate keys");
        println("2 <file path> - make sign");
        println("3 <file path> - check sign");
        println("4 exit");

        int type;
        Signature signature = new Signature();
        while (true) {
            type = s.nextInt();
            if (type == 1) {
                int blq = s.nextInt();
                KeyPair keyPair = signature.generate(blq);
            }
            if (type == 2) {
                String pathFile = s.next();
                byte[]  bytes = Files.readAllBytes(Paths.get(pathFile));
                PublicKey publicKey = PublicKey.readFromFile(pathPublicKey);
                PrivateKey privateKey = PrivateKey.readFromFile(pathPrivateKey);
                signature.makeSign(bytes, publicKey, privateKey);
            }
            if (type == 3) {
                String pathFile = s.next();
                byte[]  bytes = Files.readAllBytes(Paths.get(pathFile));
                PublicKey publicKey = PublicKey.readFromFile(pathPublicKey);
                SignKey      sign = SignKey.readFromFile(pathSign);
                if (signature.checkSign(bytes,publicKey,sign)) {
                    println("Schnorr signature is valid");
                } else {
                    println("Schnorr signature is not valid");
                }
            }
            if (type == 4){
                break;
            }
        }
    }

     static void println(String message) {
         System.out.println(message);
     }
}