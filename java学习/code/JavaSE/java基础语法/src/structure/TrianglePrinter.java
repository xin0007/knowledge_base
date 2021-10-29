package structure;

public class TrianglePrinter {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (int i1 = 5; i1 > i; i1--) {
                System.out.print(" ");
            }
            System.out.print("/");

            for (int i1 = 0; i1 < i*2; i1++) {
                System.out.print(" ");
            }
            System.out.println("\\");

        }
    }
}
