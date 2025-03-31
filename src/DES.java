public class DES {
    private Key[] keys;
    private String intialText;
    private String text;
    private String l0;
    private String r0;
    private Key[] reversedKeys = new Key[16];


    public DES(String key64, String text, boolean Binary) {
        intialText=text;
        if (Binary) {
            keys = DES_Utility.generateKeys(key64);

            this.text = DES_Tables.initialPermutation(text);

        } else {
            keys = DES_Utility.generateKeys(DES_Utility.stringToBinary(key64));
            this.text = DES_Tables.initialPermutation(DES_Utility.stringToBinary(text));
        }
        l0 = this.text.substring(0, 32);
        r0 = this.text.substring(32);
    }

    public String[] round(String l, String r, String k) {
        String fResult = DES_Utility.function(r, k);
        String newL = r;
        String newR = DES_Utility.xor(l, fResult);
        return new String[]{newL, newR};
    }

    public String encrypt() {
        String l = l0;
        String r = r0;

        for (int i = 0; i < 16; i++) {
            String[] result = round(l, r, keys[i].getSubkey());
            l = result[0];
            r = result[1];

        }

        return DES_Tables.finalPermutation(r + l);
    }

    public String decrypt() {
        DES_Utility.reversekeys(keys,reversedKeys);
        String l = l0;
        String r = r0;

        for (int i = 0; i < 16; i++) {
            String[] result = round(l, r, reversedKeys[i].getSubkey());
            l = result[0];
            r = result[1];

        }

        return DES_Tables.finalPermutation(r + l);
    }
    //print ciphertext in Binary, Hex, and Text

    public  void printEncryption(String encryptedText){
        System.out.println("Enryption of plaintext: "+  intialText);
        System.out.println("ciphertext in binary "+encryptedText);
        System.out.println("ciphertext in hex "+DES_Utility.binaryToHex(encryptedText));
        System.out.println("ciphertext in text "+DES_Utility.binaryToString(encryptedText));
        System.out.println("------------------------------------------------------------------------");

    }
    //print plaintext in Binary, Hex, and Text
    public void printDecryption(String decryptedText){
        System.out.println("Decryption of ciphertext: "+ intialText);
        System.out.println("plaintext in binary "+decryptedText);
        System.out.println("p[laintext in hex "+DES_Utility.binaryToHex(decryptedText));
        System.out.println("plaintext in text "+DES_Utility.binaryToString(decryptedText));
        System.out.println("------------------------------------------------------------------------");
    }


}




