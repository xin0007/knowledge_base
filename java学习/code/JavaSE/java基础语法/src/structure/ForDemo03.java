package structure;

public class ForDemo03 {
    // 打印九九乘法表
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            for (int i1 = 1; i1 <= 9; i1++) {
                System.out.println(i*i1);
            }
        }
    }
}
