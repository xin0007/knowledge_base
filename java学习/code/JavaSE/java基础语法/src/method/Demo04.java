package method;

public class Demo04 {
    public static void main(String[] args) {
        double re = compMax(1,2,3,4);
        double relist = compMax(new double[]{1,2,3});
        System.out.println(re);
        System.out.println(relist);
    }

    public static double compMax(double... numbers){
        double result = -1.0;
        if (numbers.length == 0){
            result = 0;
        }
        for (int i = 0; i < numbers.length; i++) {
            if (result < numbers[i]){
                result = numbers[i];
            }
        }
        return result;
    }
}
