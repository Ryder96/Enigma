package it.andres.loaiza;

import java.util.Random;

/**
 * Created by andres on 15/02/15.
 */
public abstract class Generator {

    private int dimensionArray;
    private int[] intArray;
    private char[] charArray;

    public Generator(int dimArray) {
        dimensionArray = dimArray;

        intArray = new int[dimensionArray];
        charArray = new char[dimensionArray];
        initCharArray();
        initIntArray();

    }

    public static String shambleCharArray(String s) {


        int k;
        Random random = new Random();
        for (int i = 0; i < s.length(); ++i) {
            k = random.nextInt(s.length());
            s = swap(s, i, k);
        }

        return s;
    }

    private static String swap(String s, int i, int k) {
        char[] tempArray = s.toCharArray();
        char temp = tempArray[i];
        Random random = new Random();

        while (i == k) {
            k = random.nextInt(s.length());
        }
        tempArray[i] = tempArray[k];
        tempArray[k] = temp;
        return new String(tempArray);
    }

    public int[] shambleIntArray() {

        int[] array = new int[dimensionArray];

        for (int j = 0; j < array.length; ++j)
            array[j] = intArray[j];


        int k;
        Random random = new Random();
        for (int i = 0; i < dimensionArray; ++i) {
            k = random.nextInt(dimensionArray);
            array = swap(array, i, k);
        }

        return array;
    }

    private int[] swap(int[] array, int i, int k) {

        int[] tempArray = array;
        int temp = tempArray[i];
        Random random = new Random();

        while (i == k) {
            k = random.nextInt(array.length);
        }

        tempArray[i] = tempArray[k];
        tempArray[k] = temp;
        return tempArray;
    }

    private char[] swap(char[] array, int i, int k) {

        char[] tempArray = array;
        char temp = tempArray[i];
        Random random = new Random();

        while (i == k) {
            k = random.nextInt(array.length);
        }

        tempArray[i] = tempArray[k];
        tempArray[k] = temp;
        return tempArray;
    }


    public void initCharArray() {

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < alphabet.length(); ++i)
            charArray[i] = alphabet.charAt(i);

    }

    public void initIntArray() {
        for (int i = 0; i < 26; ++i) {
            intArray[i] = i;
        }

    }


}
