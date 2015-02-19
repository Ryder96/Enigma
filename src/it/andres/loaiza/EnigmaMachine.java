package it.andres.loaiza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Andres on 19/02/2015.
 */

public class EnigmaMachine {

    private final StringBuffer ALPHABET = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    private StringBuffer rotorOne, rotorTwo, rotorThree, reflector;
    private StringBuffer rRotorOne, rRotorTwo, rRotorThree;

    private StringBuffer lastLetterR1, lastLetterR2;

    public EnigmaMachine() {


        initRotors();
        lastLetterR1 = new StringBuffer("D");
        lastLetterR2 = new StringBuffer("S");
    }

    public EnigmaMachine(EnigmaMachine enigmaMachineObject) {
        this.rotorOne = enigmaMachineObject.getRotorOne();
        this.rotorTwo = enigmaMachineObject.getRotorTwo();
        this.rotorThree = enigmaMachineObject.getRotorThree();
        this.reflector = enigmaMachineObject.getReflector();
        this.rRotorOne = enigmaMachineObject.getrRotorOne();
        this.rRotorTwo = enigmaMachineObject.getrRotorTwo();
        this.rRotorThree = enigmaMachineObject.getrRotorThree();
        this.lastLetterR1 = enigmaMachineObject.getLastLetterR1();
        this.lastLetterR2 = enigmaMachineObject.getLastLetterR2();
    }

    public static void main(String[] args) throws IOException {

        EnigmaMachine myMachineToCrypt = new EnigmaMachine();
        EnigmaMachine myMachineToDecrypt = new EnigmaMachine(myMachineToCrypt);
        BufferedReader myKey = new BufferedReader(new InputStreamReader(System.in));
        String a, b, c, d;

        do {

            System.out.print("Inserire messaggio da criptare/decriptare: ");
            a = myKey.readLine();
            System.out.println("Messaggio criptato/decriptato:");
            b = myMachineToCrypt.encrypt(a);
            d = myMachineToDecrypt.encrypt(b);
            System.out.println("Testo iniziale: " + a + "\n" + "Testo criptato/decriptato:" + b + " " + d);

            System.out.print("Continuare? [S/N]");
            c = myKey.readLine();
            c = c.toUpperCase();
            System.out.println();
        } while (c.compareTo("N") != 0);
        System.out.print("Chiusura..");
        System.exit(0);
    }


    public StringBuffer getRotorOne() {
        return rotorOne;
    }

    public StringBuffer getRotorTwo() {
        return rotorTwo;
    }

    public StringBuffer getRotorThree() {
        return rotorThree;
    }

    public StringBuffer getReflector() {
        return reflector;
    }

    public StringBuffer getrRotorOne() {
        return rRotorOne;
    }

    public StringBuffer getrRotorTwo() {
        return rRotorTwo;
    }

    public StringBuffer getrRotorThree() {
        return rRotorThree;
    }

    public StringBuffer getLastLetterR2() {
        return lastLetterR2;
    }


    private void initRotors() {

        rotorOne = new StringBuffer(Generator.shambleCharArray(ALPHABET.toString()));
        rotorTwo = new StringBuffer(Generator.shambleCharArray(ALPHABET.toString()));
        rotorThree = new StringBuffer(Generator.shambleCharArray(ALPHABET.toString()));
        reflector = new StringBuffer(Generator.shambleCharArray(ALPHABET.toString()));
        rRotorOne = new StringBuffer(Generator.shambleCharArray(ALPHABET.toString()));
        rRotorTwo = new StringBuffer(Generator.shambleCharArray(ALPHABET.toString()));
        rRotorThree = new StringBuffer(Generator.shambleCharArray(ALPHABET.toString()));


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
        StringBuffer currentR1 = new StringBuffer(rRotorOne.charAt(0) + "");
        StringBuffer currentR2 = new StringBuffer(rRotorTwo.charAt(0) + "");

        rRotorOne.append(rRotorOne.charAt(0));
        rRotorOne.delete(0, 1);

        if (currentR1.toString().equals(lastLetterR1.toString())) {
            rRotorTwo.append(rRotorTwo.charAt(0));
            rRotorTwo.delete(0, 1);

            if (currentR2.toString().equals(lastLetterR2.toString())) {
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

    public StringBuffer getLastLetterR1() {
        return lastLetterR1;
    }


}


