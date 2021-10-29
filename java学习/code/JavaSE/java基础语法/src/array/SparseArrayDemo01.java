package array;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SparseArrayDemo01 {
    public static void main(String[] args) {
        int[][] a = new int[11][11];
        a[1][2] = 1;
        a[2][3] = 2;
        for (int[] ints : a) {
            System.out.println(Arrays.toString(ints));
        }

        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if(a[i][j] != 0){
                    count += 1;
                }
            }
        }
        // 对稀疏数组进行压缩
        int[][] b = new int[count+1][3];
        b[0][0] = a.length;
        b[0][1] = a[0].length;
        b[0][2] = count;

        int ini = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length ; j++) {
                if (a[i][j] != 0){
                    ini ++;
                    b[ini][0] = i;
                    b[ini][1] = j;
                    b[ini][2] = a[i][j];
                }
            }
        }
        System.out.println("============");

        for (int i = 0; i < b.length; i++) {
            System.out.println(Arrays.toString(b[i]));
        }

        // 读取压缩记录，重构稀疏数组
        // 1. 先创建一个全部都是0的数组
        int[][] c = new int[b[0][0]][b[0][1]];

        // 2. 将元素回填回去

        for (int i = 1; i < b.length; i++) {
            c[b[i][0]][b[i][1]] = b[i][2];
        }

        System.out.println("============");


        for (int i = 0; i < c.length; i++) {
            System.out.println(Arrays.toString(c[i]));

        }





    }

}
