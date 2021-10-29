package array;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayClassDemo01 {
    public static void main(String[] args) {
//        int[] a = {1,2,3,4};
//        System.out.println(Arrays.toString(a));

        int[] b = {2,7,5,1};
//        System.out.println(Arrays.toString(b));
//
//        Arrays.sort(b);
//        System.out.println(Arrays.toString(b));

        System.out.println(Arrays.binarySearch(b,27));
    }
}
