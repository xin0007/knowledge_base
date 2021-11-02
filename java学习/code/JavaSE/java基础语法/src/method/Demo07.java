package method;

public class Demo07 {
    public static void main(String[] args) {
        int num = 1;
        change(num);
        System.out.println(num);
    }

    public static void change(int a){
        a = 10;
    }
}
