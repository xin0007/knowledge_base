package exception;

public class Test {
    public static void main(String[] args) {
        int a = 10;
        int b = 0;
        try {
            System.out.println(a/b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }
    public void test(int a, int b) throws ArithmeticException{
        if (b == 0) {
            throw new ArithmeticException();
        }
    }
}

