package structure;

public class ForDemo04 {
    public static void main(String[] args) {
        int[] numbers = {10,20,30,40,50};
        for (int x:numbers){
            System.out.println(x);
        }
        System.out.println("============");
        for (int i = 0; i < 5; i++) {
            System.out.println(numbers[i]);
        }
    }
}
