package src;

import java.util.Arrays;

/**
 * Created by blayhem on 13/5/15.
 */
public class Test {
    public static void main(String[] args) {
        byte[] ffps = {1, -127}; // 128, 255 = 384
        int[] array = new int[10];

        int ffps0 = (int) ffps[0] + 128;
        for (int i = 9; i > 1; i--) {
            array[i] = ffps0 % 2;
            ffps0 = ffps0 / 2;
        }

        int ffps1 = (int) ffps[1] + 128;
        for (int i = 1; i >= 0; i--) {
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
    }
}
