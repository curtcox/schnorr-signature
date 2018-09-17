package schnorr;

import java.util.*;

 class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        println("1 <bits in q> - generate keys");
        println("2 <message> - sign");
        println("3 - check signature");
        println("4 exit");

        int type;
        KeyPair keyPair = null;
        Signature signature = null;
        byte[] bytes = null;
        while (true) {
            type = s.nextInt();
            if (type == 1) {
                int blq = s.nextInt();
                keyPair = KeyPair.generate(blq);
                println("keys = " + keyPair);
            }
            if (type == 2) {
                bytes = s.next().getBytes();
                signature = keyPair.sign(bytes);
                println("signature = " + signature);
            }
            if (type == 3) {
                if (signature.check(bytes,keyPair.publicKey)) {
                    println("Signature is valid");
                } else {
                    println("Signature is not valid");
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