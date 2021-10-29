package array;

public class ArrayDemo03 {
    public static void main(String[] args) {

        int[] a = {1,2,3,4,5};
        int[] re = reverse(a);
        for (int i : re) {
            System.out.println(i);
        }
    }

    public static int[] reverse(int[] arrays){
        int[] reversed = new int[arrays.length];

        for (int i = 0; i < arrays.length; i++) {
            reversed[arrays.length - 1 - i] = arrays[i];
        }
        return reversed;
    }


}




