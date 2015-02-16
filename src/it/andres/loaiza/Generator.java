package it.andres.loaiza;

import java.util.Random;

/**
 * Created by andres on 15/02/15.
 */
public class Generator {

    private int dimension_array;
    private int[] array_generated;

    public Generator(int dv) {
        this.dimension_array = dv;

    }


    public int[] shamble() {

        int[] array = new int[dimension_array];

        for (int j = 0; j < array.length; ++j)
            array[j] = array_generated[j];


        int k;
        Random random = new Random();
        for(int i = 0; i < dimension_array; ++i){
            k = random.nextInt(dimension_array);
            array = swap(array,i,k);
        }

        return array;
    }

    private int[] swap(int[] array, int i, int k) {

        int[] tempArray = array;
        int temp = tempArray[i];
        Random random = new Random();

        while ( i == k) {
            k = random.nextInt(array.length);
        }

        tempArray[i] = tempArray[k];
        tempArray[k] = temp;
        return tempArray;
    }

    public void initArray(){
        int cMax = 0;
        int cMin = 0;
        boolean duplications;
        boolean foundMax = false;
        int[] shifts = new int[dimension_array];
        Random random = new Random();
        for (int i = 0; i < shifts.length; i++) {
            shifts[i] = random.nextInt(26);
        }

        do {
            duplications = false;
            for (int i = 0; i < shifts.length; ++i) {
                if (shifts[i] > 25)
                    shifts[i] = 0;
                if (shifts[i] < 0)
                    shifts[i] = 25;
                if (Max(cMax, i, shifts)) {
                    foundMax = true;
                }
                if (Min(cMin, i, shifts)) {
                    foundMax = false;
                }
                for (int j = 0; j < shifts.length; ++j) {
                    if (j != i) {
                        if (shifts[i] == shifts[j]) {
                            if (foundMax) {
                                --shifts[j];
                                --cMax;
                            } else {
                                ++shifts[j];
                                ++cMin;
                            }

                            duplications = true;
                        }
                    }
                }
            }
        } while (duplications);

        array_generated = shifts;
    }


    private boolean Min(int cMin, int i, int[] shifts) {
        int up = cMin;
        if(up > 25)
            up = 0;
        return shifts[i] <= up;
    }

    private boolean Max(int cMax, int i, int[] shifts) {
        int less = 25 - cMax;
        if(less < 0 )
            less = 25;
        return shifts[i] >= less;

    }


    public int[] getArrayGenerated(){
        return array_generated;
    }
}
