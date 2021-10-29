package structure;

// 用while和for循环输出 1-1000之间能被5整除的s数，并且每行输出3个
public class ForDemo02 {
    public static void main(String[] args) {
        for (int i = 1; i < 1000; i++) {
            if (i%5==0){
                if (i%3 == 0){
                    System.out.println(i+"\n");
                }else{
                    System.out.print(i+"\t");
                }
            }
        }
    }
}
