package it.andres.loaiza;


public class Enigma {

    Rotors rotors;


    public Enigma() {

        rotors = new Rotors();

    }







    public static void main(String[] args) {

        Enigma enigma = new Enigma();

        String c;

       // a = enigma.rotors.encrypt("A");
        c = enigma.rotors.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");


        System.out.print(  c + "\n");
    }



}
