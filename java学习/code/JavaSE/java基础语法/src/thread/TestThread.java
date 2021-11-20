package thread;

public class TestThread {
    public static void main(String[] args) {
        new Thread(()-> System.out.println("sss")).start();
    }
}
