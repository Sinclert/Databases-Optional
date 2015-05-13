package src;

import java.util.Arrays;

/**
 * Created by blayhem on 13/5/15.
 */
public class Test {
    public static void main(String[] args) {
        byte[] ffps = {3, -100}; // 128, 255 = 384
        int[] array = new int[10];

        int ffps0 = (int) ffps[0] + 128;
        for (int i = 1; i >= 0; i--) {
            array[i] = ffps0 % 2;
            ffps0 = ffps0 / 2;
        }

        int ffps1 = (int) ffps[1] + 128;
        for (int i = 9; i > 1; i--) {
            array[i] = ffps1 % 2;
            ffps1 = ffps1 / 2;
        }

        /*
         * 0 1 2 3 4 5 6 7 8 9
         * 0 - 128 - 256
         * -128 - 0 - 128
         * sumamos 128 para ajustar la escala hasta 256
         */
        int sum = 0;
        for (int i = 9, j = 0; i >= 0; i--, j++) {
            if (i != 2) sum += array[i] * (Math.pow(2, j));
            else j--;
        }
        System.out.println(Arrays.toString(array));
        System.out.println(sum);
        /////////////////////////////////

        String inttoS = Integer.toBinaryString(sum);
        System.out.println(Integer.toBinaryString(sum));
        int[] array2 = {0,0,0,0,0,0,0,0,0,0};
        for (int i = 0, j=inttoS.length()-1, k = 9; i < inttoS.length(); i++, j--, k--) {
            if(k!=2) array2[k] = Integer.parseInt(""+inttoS.charAt(j));
            else {i--; j++;}
        }
        array2[2] = 1;
        System.out.println(Arrays.toString(array2));

        int ffps21 = 0;
        for (int i = 1, j = 0; i >= 0; i--, j++) {
            ffps21 += array2[i] * (Math.pow(2, j));
        }
        //ffps21 -= 128;
        System.out.println(ffps21);

        int ffps22 = 0;
        for (int i = 9, j = 0; i > 2; i--, j++) {
            ffps22 += array2[i] * (Math.pow(2, j));
        }
        //if(ffps22 < 128)ffps22 -= 128;
        System.out.println(ffps22);

        byte[]  ffpsdef = {(byte)ffps21,(byte)ffps22};
        System.out.println(Arrays.toString(ffpsdef));
    }
}
