//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
                                                           //plaintext to encrypt
        DES encryptionExample = new DES("ABCDEFGH",    "WASDWASD"         ,false);
        String cipherText = encryptionExample.encrypt();
        encryptionExample.printEncryption(cipherText);

                                        //key is the same but in binary
        DES decryptionExample = new DES(DES_Utility.stringToBinary("ABCDEFGH"), cipherText,true);
        String plainText = decryptionExample.decrypt();
        decryptionExample.printDecryption(plainText);





        DES encryptionExample2 = new DES("0001001100110100010101110111100110011011101111001101111111110001","0000000100100011010001010110011110001001101010111100110111101111",true);
        String cipherText2 = encryptionExample2.encrypt();
        encryptionExample2.printEncryption(cipherText2);

        DES decryptionExample2 = new DES("0001001100110100010101110111100110011011101111001101111111110001", cipherText2,true);
        String plainText2 = decryptionExample2.decrypt();
        decryptionExample2.printDecryption(plainText2);




        DES encryptionExample3 = new DES("TESTTEST",    "TESTTEST"         ,false);
        String cipherText3 = encryptionExample3.encrypt();
        encryptionExample3.printEncryption(cipherText3);

        DES decryptionExample3 = new DES("TESTTEST", DES_Utility.binaryToString(cipherText3),false);
        String plainText3 = decryptionExample3.decrypt();
        decryptionExample3.printDecryption(plainText3);





    }

}
