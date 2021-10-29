package structure;

public class WhileDemo01 {
    public static void main(String[] args) {
        // 0+1+2+...+100
        int a = 0;
        int sum = 0;
        while (a <= 100){
            sum += a;
            a ++;
        }
        System.out.println(sum);
    }
}
