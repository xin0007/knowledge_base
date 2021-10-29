package structure;

public class BreakDemo01 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if(i==3){
                System.out.println("打印1");
                continue;

            }else{
                System.out.println(i);
            }
        }
    }
}
