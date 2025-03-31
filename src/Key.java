public class Key {
    private String key64;
    private String key56;
    private String left28;
    private String right28;
    private String key48;

    public Key(String keyText, int round) {
        key64 = keyText;
        key56 = DES_Tables.permutePC1(key64);
        splitKey56();
        key48 = generateSubkey(round);
    }

    public Key(String key56, int round, String key64) {
        this.key56 = key56;
        this.key64 = key64;
        splitKey56();
        key48 = generateSubkey(round);
    }



    private void splitKey56() {
        left28 = key56.substring(0, 28);
        right28 = key56.substring(28);
    }

    public String generateSubkey(int round) {
        int[] shifts = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
        String left = DES_Utility.shiftLeft(left28, shifts[round - 1]);
        String right = DES_Utility.shiftLeft(right28, shifts[round - 1]);
        left28 = left;
        right28 = right;
        key56 = left + right;
        return DES_Tables.permutePC2(key56);
    }

    String getKey56() {
        return key56;
    }
    String getSubkey() {
        return key48;
    }
}