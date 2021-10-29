package base;

public class Demo02_02 {
    public static void main(String[] args) {
        float f = 0.1f;
        double d = 0.1;
        System.out.println(f == d);

        System.out.println("==================");

        float a = 123123123123123f;
        float b = a+1;
        System.out.println(a == b);

        System.out.println("==================");

        char c1 = 'a';
        char c2 = '中';
        System.out.println(c1);
        System.out.println((int)c1);

        System.out.println(c2);
        System.out.println((int)c2);

        System.out.println("==================");

        char c3 = '\u0061';
        System.out.println(c3);

        System.out.println("==================");




    }
}


