package array;

public class ArrayDemo02 {
    public static void main(String[] args) {
        // 静态初始化
        int[] a = {1,2,3,4};
        for (int i : a) {
            System.out.println(i);
        }

        // 动态初始化
        int[] b = new int[5];
        b[0] = 1;
        b[2] = 1;
        for (int i : b) {
            System.out.println(i);
        }


        }

    }

