public class DES_Utility {

    // Binary Conversion
    public static String stringToBinary(String text) {
        StringBuilder binaryText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            String binaryValue = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            binaryText.append(binaryValue);
        }

        return binaryText.toString();
    }

    public static String binaryToString(String binary) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            String byteString = binary.substring(i, i + 8);
            int byteValue = Integer.parseInt(byteString, 2);
            text.append((char) byteValue);
        }
        return text.toString();
    }

    public static String binaryToHex(String binary) {
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            String byteString = binary.substring(i, i + 8);
            int byteValue = Integer.parseInt(byteString, 2);
            String hexValue = String.format("%02X", byteValue);
            hex.append(hexValue);
        }
        return hex.toString();
    }

    //Shift Left
    public static String shiftLeft(String binary, int shift) {
        StringBuilder result = new StringBuilder();
        int i = shift;
        while (i < binary.length()) {
            result.append(binary.charAt(i));
            i++;
        }
        i = 0;
        while (i < shift) {
            result.append(binary.charAt(i));
            i++;
        }
        return result.toString();
    }

    // Key Gen
    public static Key[] generateKeys(String key64) {
        Key[] keys = new Key[16];
        Key key = new Key(key64, 1);
        keys[0] = key;
        Key previousKey = key;
        int[] shifts = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
        for (int i = 1; i < 16; i++) {
            Key newKey = new Key(previousKey.getKey56(), i + 1, key64);
            keys[i] = newKey;
            previousKey = newKey;
        }
        return keys;
    }

    //xor
    public static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                result.append("0");
            } else {
                result.append("1");
            }
        }
        return result.toString();
    }

    //divideIntoBlocksof6Bits
    public static String[] divideIntoBlocks(String input) {
        String[] blocks = new String[8];
        for (int i = 0; i < 8; i++) {
            blocks[i] = input.substring(i * 6, (i * 6) + 6);
        }
        return blocks;
    }

    // f function
    public static String function(String r, String k) {
        String expandedR = DES_Tables.expand(r);
        String xorResult = xor(expandedR, k);
        String[] blocks = divideIntoBlocks(xorResult);
        String[] sBoxResults = new String[8];

        sBoxResults[0] = DES_Tables.getSBox1(blocks[0]);
        sBoxResults[1] = DES_Tables.getSBox2(blocks[1]);
        sBoxResults[2] = DES_Tables.getSBox3(blocks[2]);
        sBoxResults[3] = DES_Tables.getSBox4(blocks[3]);
        sBoxResults[4] = DES_Tables.getSBox5(blocks[4]);
        sBoxResults[5] = DES_Tables.getSBox6(blocks[5]);
        sBoxResults[6] = DES_Tables.getSBox7(blocks[6]);
        sBoxResults[7] = DES_Tables.getSBox8(blocks[7]);

        StringBuilder result = new StringBuilder();
        for (String sBoxResult : sBoxResults) {
            result.append(sBoxResult);
        }
        return DES_Tables.permute(result.toString());
    }

    //reverse keys for decryption
    public static void reversekeys(Key[] keys, Key[] reversedKeys) {

        for (int i = 0; i < 16; i++) {
            reversedKeys[i] = keys[15 - i];
        }
    }




}