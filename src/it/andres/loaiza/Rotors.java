package it.andres.loaiza;


/**
 * Created by andres on 15/02/15.
 */
public class Rotors {
    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    private int[][] matrix_shifts = new int[7][ALPHABET.length()];

    private final String[] ROTORS_ALPHABETS = {"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "JGDQOXUSCAMIFRVTPNEWKBLZYH", "NTZPSFBOKMWRCJDIVLAEYUXHGQ",
            "JVIUBHTCDYAKEQZPOSGXNRMWFL", "QYHOGNECVPUZTFDJAXWMKISRBL"};

    public Rotors() {

        generateShifts();

    }

    private void generateShifts() {
        Generator myGenerator = new Generator(ALPHABET.length());
        myGenerator.initArray();


        for (int i = 0; i < matrix_shifts.length; ++i)
            matrix_shifts[i] = myGenerator.shamble();


    }


    private char outputRotors(char cInput, int start) {

        int iStart = start;
        int indexRotors = start;
        switch (indexRotors) {
            case 4:
                indexRotors = 3;
                break;
            case 5:
                indexRotors = 2;
                break;
            case 6:
                indexRotors  = 1;
                break;
        }

        char output = cInput;
        if (start == matrix_shifts.length - 1) {
            return output;
        } else {
            boolean find = false;
            int in = 0;
            while (!find && in < ALPHABET.length()) {
                if (cInput == ROTORS_ALPHABETS[indexRotors].charAt(matrix_shifts[start][in])) {
                    find = true;
                    ++iStart;
                    if (indexRotors + 1 < ROTORS_ALPHABETS.length)
                        output = ROTORS_ALPHABETS[indexRotors + 1].charAt(in);
                    else {
                        switch (indexRotors) {
                            case 4:
                                output = ROTORS_ALPHABETS[indexRotors - 1].charAt(in);
                                break;
                            case 5:
                                output = ROTORS_ALPHABETS[indexRotors - 2].charAt(in);
                                break;
                            case 6:
                                output = ROTORS_ALPHABETS[indexRotors - 3].charAt(in);
                                break;
                        }

                    }
                } else
                    ++in;

            }

            outputRotors(output, iStart);
        }
        return output;
    }


    public String encrypt(String string) {
        string = string.toUpperCase();
        String output = "";
        for (int i = 0; i < string.length(); ++i) {
            output += outputRotors(string.charAt(i), 0);

        }

        for(int i = 0; i < output.length(); ++i ) {
            System.out.print( output.charAt(i) + ",");
        }

        System.out.println();
        return output;
    }
}
