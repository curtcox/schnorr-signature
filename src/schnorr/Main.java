package schnorr;

import java.util.*;

 class Main {

     int type;
     KeySet keySet;
     Signature signature;
     byte[] bytes;
     final Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
         Main main = new Main();
         main.run();
    }

    void run() {
        printInstructions();
        loopForInput();
    }

     void loopForInput() {
         while (true) {
             type = s.nextInt();
             if (type == 1) { generateKeys();   }
             if (type == 2) { sign();           }
             if (type == 3) { checkSignature(); }
             if (type == 4) { break;            }
         }
     }

     void generateKeys() {
         int blq = s.nextInt();
         keySet = KeySet.generate(blq);
         println("keys = " + keySet);
     }

     void sign() {
         bytes = s.next().getBytes();
         signature = keySet.signing.sign(bytes);
         println("signature = " + signature);
     }

     void checkSignature() {
         if (keySet.verifying.verify(bytes,signature)) {
             println("Signature is valid");
         } else {
             println("Signature is not valid");
         }
     }

     void printInstructions() {
         println("1 <bits in q> - generate keys");
         println("2 <message> - sign");
         println("3 - check signature");
         println("4 exit");
     }

     static void println(String message) {
         System.out.println(message);
     }
}