package structure;

public class ForDemo01 {
    public static void main(String[] args) {
        // 计算0-100之间 奇数和偶数的和
        int odd = 0;
        int even = 0;
        for (int i = 0; i < 100; i++) {
            if (i%2==0){
                even += i;
            }else{
                odd +=i;
            }
        }

        System.out.println("odd是："+ odd);
        System.out.println("even是："+ even);

    }
}
