package method;

public class Demo02 {
    public static void main(String[] args) {
        int max = max(10, 20);
        System.out.println(max);

        double max1 = max(10.0, 10.2);
        System.out.println(max1);
    }

    public static int max(int a, int b){
        int result = -1;
        if (a>b){
            result = a;
        } else if(a <b){
            result = b;
        } else {
            result = 0;
        }
        return result;
    }

    public static double max(double a, double b){
        double result = -1;
        if (a>b){
            result = a;
        } else if(a <b){
            result = b;
        } else {
            result = 0;
        }
        return result;
    }

}
