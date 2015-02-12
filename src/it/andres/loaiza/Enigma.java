package it.andres.loaiza;

import java.util.Random;

public class Enigma {

    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final String FIRST_ROTOR = "JGDQOXUSCAMIFRVTPNEWKBLZYH";
    private final String SECOND_ROTOR = "NTZPSFBOKMWRCJDIVLAEYUXHGQ";
    private final String THIRD_ROTOR = "JVIUBHTCDYAKEQZPOSGXNRMWFL";
    private final String REFLECTOR = "QYHOGNECVPUZTFDJAXWMKISRBL";


    private int[] first_rotor_shifts = new int[ALPHABET.length()];
    private int[] second_rotor_shifts = new int[ALPHABET.length()];
    private int[] third_rotor_shifts = new int[ALPHABET.length()];
    private int[] reflector_shifts = new int[ALPHABET.length()];

    private int[] reverse_first_rotor_shifts = new int[ALPHABET.length()];
    private int[] reverse_second_rotor_shifts = new int[ALPHABET.length()];
    private int[] reverse_third_rotor_shifts = new int[ALPHABET.length()];


    public Enigma() {
        first_rotor_shifts = generateShifts();
        second_rotor_shifts = generateShifts();
        third_rotor_shifts = generateShifts();

        reflector_shifts = generateShifts();
        reverse_first_rotor_shifts = generateShifts();
        reverse_second_rotor_shifts = generateShifts();
        reverse_third_rotor_shifts = generateShifts();
    }

    private char first_rotor(char charAt) {
        boolean find = false;
        int i = 0;
        if(charAt == ' ')
            return charAt;
        while (!find && i < ALPHABET.length()) {
            if (charAt == ALPHABET.charAt(i))
                find = true;
            else
                ++i;

        }

        return second_rotor(FIRST_ROTOR.charAt(first_rotor_shifts[i]));

    }

    private char second_rotor(char charAt) {
        boolean find = false;
        int i = 0;
        while (!find && i < FIRST_ROTOR.length()) {
            if (charAt == FIRST_ROTOR.charAt(i))
                find = true;
            else
                ++i;

        }
        return third_rotor(SECOND_ROTOR.charAt(second_rotor_shifts[i]));
    }

    private char third_rotor(char charAt) {
        boolean find = false;
        int i = 0;
        while (!find && i < SECOND_ROTOR.length()) {
            if (charAt == SECOND_ROTOR.charAt(i))
                find = true;
            else
                ++i;

        }
        return reflector(THIRD_ROTOR.charAt(third_rotor_shifts[i]));
    }

    private char reflector(char charAt) {
        boolean find = false;
        int i = 0;
        while (!find && i < THIRD_ROTOR.length()) {
            if (charAt == THIRD_ROTOR.charAt(i))
                find = true;
            else
                ++i;

        }
        return reverse_third_rotor(REFLECTOR.charAt(reflector_shifts[i]));
    }

    private char reverse_third_rotor(char charAt) {
        boolean find = false;
        int i = 0;
        while (!find && i < REFLECTOR.length()) {
            if (charAt == REFLECTOR.charAt(i))
                find = true;
            else
                ++i;

        }
        return reverse_second_rotor(THIRD_ROTOR.charAt(reverse_third_rotor_shifts[i]));
    }

    private char reverse_second_rotor(char charAt) {
        boolean find = false;
        int i = 0;
        while (!find && i < THIRD_ROTOR.length()) {
            if (charAt == THIRD_ROTOR.charAt(i))
                find = true;
            else
                ++i;

        }
        return reverse_first_rotor(SECOND_ROTOR.charAt(reverse_second_rotor_shifts[i]));
    }

    private char reverse_first_rotor(char charAt) {
        boolean find = false;
        int i = 0;
        while (!find && i < SECOND_ROTOR.length()) {
            if (charAt == SECOND_ROTOR.charAt(i))
                find = true;
            else
                ++i;

        }
        return FIRST_ROTOR.charAt(reverse_first_rotor_shifts[i]);
    }

    private int[] generateShifts() {
        int cMax = 0;
        int cMin = 0;
        boolean duplications = false;
        boolean foundMax = false;
        boolean foundMin = false;
        int[] shifts = new int[ALPHABET.length()];
        Random random = new Random();
        for (int i = 0; i < shifts.length; i++) {
            shifts[i] = random.nextInt(26);
        }


        System.out.println();
        do {
            duplications = false;
            for (int i = 0; i < shifts.length; ++i) {
                if(Max(cMax,i,shifts)) {
                    foundMax = true;
                    foundMin = false;
                }
                if(Min(cMin,i,shifts)){
                    foundMin = true;
                    foundMax = false;
                }
                for (int j = 0; j < shifts.length; ++j) {
                    if (j != i) {
                        if (shifts[i] == shifts[j]) {
                            if (foundMax) {
                                --shifts[j];
                                --cMax;
                            }
                            else if (foundMin) {
                                ++shifts[j];
                                ++cMin;
                            }

                            duplications = true;
                        }
                    }
                }
            }
        } while( duplications );


        System.out.println();
        return shifts;
    }

    private boolean Min(int cMin, int i, int[] shifts) {
        int up0 = 0+ cMin;
        if(shifts[i] <= up0)
            return true;
        return false;
    }

    private boolean Max(int cMax, int i, int[] shifts) {
        int less25 = 25 - cMax;
        if(shifts[i] >= less25)
            return true;
        return false;
    }


    public void encrypt(String string) {

        String output = "";
        for (int i = 0; i < string.length(); ++i)
            output += first_rotor(string.charAt(i));


        System.out.println(output);
    }




    public static void main(String[] args) {

        Enigma enigma = new Enigma();

        enigma.encrypt("PAMPA SCEMO");
        enigma.encrypt(enigma.ALPHABET);
    }



}
