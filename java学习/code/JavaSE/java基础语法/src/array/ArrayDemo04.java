package array;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayDemo04 {
    public static void main(String[] args) {
        int[] a = {3,4,2,1,9,0};
        sort(a);
    }
    public static void sort(int[] array){
        for (int j = 0; j < array.length-1; j++) {
            for (int i = 0; i < array.length-j-1; i++) {
                if (array[i] > array[i+1]) {
                    int tmp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
