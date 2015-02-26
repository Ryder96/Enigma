package it.andres.loaiza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Andres on 19/02/2015  .
 */
public class EnigmaMachine {

    Rotor rotors;

    public EnigmaMachine() {
        rotors = new Rotor();
    }

    public static void main(String[] args) throws IOException {

        EnigmaMachine myMachineToCrypt = new EnigmaMachine();
        BufferedReader myKey = new BufferedReader(new InputStreamReader(System.in));
        String a, b, c;

        do {

            System.out.print("Inserire messaggio da criptare/decriptare: ");
            a = myKey.readLine();
            System.out.println("Messaggio criptato/decriptato:");
            b = myMachineToCrypt.rotors.encrypt(a);
            System.out.println("Testo iniziale: " + a + "\n" + "Testo criptato/decriptato:" + b);
            System.out.print("Continuare? [S/N]");
            c = myKey.readLine();
            c = c.toUpperCase();
            System.out.println();
        } while (c.compareTo("N") != 0);
        System.out.print("Chiusura..");
        System.exit(0);
    }



}


