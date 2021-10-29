package structure;

public class DoWhileDemo01 {
    public static void main(String[] args) {
        int a = 0;
        int sum = 0;
        do {
            sum += a;
            a += 1;
        } while (a <= 100);

        System.out.println(sum);
    }
}
