package it.andres.loaiza;


public class Enigma {

    Rotor rotor;


    public Enigma() {

        rotor = new Rotor();

    }







    public static void main(String[] args) {

        Enigma enigma = new Enigma();

        String a,b,c;

        a = enigma.rotor.startEncrypt("A");
        b = enigma.rotor.startDecrypt(enigma.rotor.startEncrypt("A"));
        System.out.print(b);
       // c = enigma.rotor.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");


    }



}
