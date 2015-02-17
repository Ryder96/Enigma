package it.andres.loaiza;


/**
 * Created by andres on 15/02/15.
 */
public class Rotor {
    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private int[][] shifts = new int[7][ALPHABET.length()];
    private int[][] nShifts = new int[7][ALPHABET.length()];
    private char[][] alphabets = new char[4][ALPHABET.length()];

    private String alphabetCrypted;



    public Rotor() {
        generateAlphabets();

    }

    private void generateAlphabets() {
        Generator myGenerator = new Generator(ALPHABET.length());
        for (int i = 0; i < alphabets.length; ++i)
            alphabets[i] = myGenerator.shambleCharArray();
        for (int i = 0; i < shifts.length; ++i)
            shifts[i] = myGenerator.shambleIntArray();

        for (int i = 0; i < shifts.length; ++i) {
            for (int j = 0; j < 26; ++j)
                nShifts[i][j] = shifts[i][j] * -1;
        }

        alphabetCrypted = startEncrypt(ALPHABET);

    }





    private char encrypt(char input) {


        char output;
        char inputChar = input;
        boolean find = false;
        int ind = 0;

        int indf = findOnAlpha(inputChar);
        int limit = 0;
        for (int i = 0; i < shifts.length; ++i) {
            while (limit < shifts[i][indf]) {
                if (inputChar + limit > 'Z')
                    inputChar = 'A';

                inputChar += limit;
                ++limit;
            }
            limit = 1;
        }



        output = inputChar;
        return output;

    }

    public int findOnAlpha(char inputChar) {

        int i = 0;
        boolean find = false;
        while (!find && i < 26) {
            if (inputChar == ALPHABET.charAt(i))
                find = true;
            else
                ++i;
        }
        return i;

    }


    public int findOnAlphaCript(char inputChar) {
        int i = 0;
        boolean find = false;
        while (!find && i < 26) {
            if (inputChar == alphabetCrypted.charAt(i))
                find = true;
            else
                ++i;
        }
        return ALPHABET.charAt(i);
    }

    private char decrypt(char input) {
        char output, inputChar = input;
        boolean find = false;

        int indf = findOnAlphaCript(inputChar);
        int limit = 0;
        for (int i = nShifts.length - 1; i > 0 ; --i) {
            while (limit > nShifts[i][indf]) {
                if (inputChar + limit < 'A')
                    inputChar = 'Z';
                inputChar += limit;
                --limit;
            }
            limit = 0;
        }



        output = inputChar;
        return output;
    }


    public String startEncrypt(String string) {

        string = string.toUpperCase();
        string = string.replace(" ", "");
        String output = "";
        for (int i = 0; i < string.length(); ++i) {
            output += encrypt(string.charAt(i));
        }
        return output;
    }


    public String startDecrypt(String string) {
        string = string.toUpperCase();
        string = string.replace(" ", "");
        String output = "";
        for (int i = 0; i < string.length(); ++i) {
            output += decrypt(string.charAt(i));
        }
        return output;
    }
}
