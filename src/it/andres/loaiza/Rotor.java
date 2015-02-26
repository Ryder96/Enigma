package it.andres.loaiza;

/**
 * Created by andres on 26/02/15   .
 */
public class Rotor {


    private StringBuffer rotorOne = new StringBuffer("JGDQOXUSCAMIFRVTPNEWKBLZYH"),
            rotorTwo = new StringBuffer("JVIUBHTCDYAKEQZPOSGXNRMWFL"),
            rotorThree = new StringBuffer("NTZPSFBOKMWRCJDIVLAEYUXHGQ"),
            reflector = new StringBuffer("QYHOGNECVPUZTFDJAXWMKISRBL");

    private StringBuffer rRotorOne, rRotorTwo, rRotorThree;

    private final StringBuffer DEFAULTLASTLETTER_R1 = new StringBuffer("H"),
            DEFAULTLASTLETTER_R2 = new StringBuffer("L");

    public Rotor() {
        initRotors();
    }

    private void initRotors() {


        rRotorOne = new StringBuffer(reflector.toString());
        rRotorTwo = new StringBuffer(reflector.toString());
        rRotorThree = new StringBuffer(reflector.toString());


    }

    public String encrypt(String s) {

        String inputString = (s.replace(" ", "")).toUpperCase();
        String ecryptedString = "";
        for (int i = 0; i < inputString.length(); ++i) {

            char output = inputString.charAt(i);

            rotate();
            output = firstRotor(output);
            ecryptedString += output;
        }

        return ecryptedString;

    }


    private void rotate() {
        StringBuilder currentR1 = new StringBuilder(rRotorOne.charAt(0) + "");
        StringBuilder currentR2 = new StringBuilder(rRotorTwo.charAt(0) + "");

        rRotorOne.append(rRotorOne.charAt(0));
        rRotorOne.delete(0, 1);

        if (currentR1.toString().equals(DEFAULTLASTLETTER_R1.toString())) {
            rRotorTwo.append(rRotorTwo.charAt(0));
            rRotorTwo.delete(0, 1);

            if (currentR2.toString().equals(DEFAULTLASTLETTER_R2.toString())) {
                rRotorThree.append(rRotorThree.charAt(0));
                rRotorThree.delete(0, 1);
            }
        }
    }


    private char firstRotor(char input) {
        int position = rRotorOne.toString().indexOf(input);
        return secondRotor(rotorOne.charAt(position));
    }

    private char secondRotor(char input) {
        int position = rRotorTwo.toString().indexOf(input);
        return thirdRotor(rotorTwo.charAt(position));
    }

    private char thirdRotor(char input) {
        int position = rRotorThree.toString().indexOf(input);
        return reflector(rotorThree.charAt(position));
    }

    public char reflector(char input) {
        int position = (int) input - 65;
        return rThirdRotor(reflector.charAt(position));
    }

    private char rFirstRotor(char input) {
        int position = rotorOne.toString().indexOf(input);
        return rRotorOne.charAt(position);
    }

    private char rSecondRotor(char input) {
        int position = rotorTwo.toString().indexOf(input);
        return rFirstRotor(rRotorTwo.charAt(position));
    }

    private char rThirdRotor(char input) {
        int position = rotorThree.toString().indexOf(input);
        return rSecondRotor(rRotorThree.charAt(position));
    }


}

