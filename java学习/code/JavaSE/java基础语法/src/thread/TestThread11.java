package thread;

public class TestThread11 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("这是子线程-----"+i);
        }
    }

    public static void main(String[] args) {
        TestThread11 testThread11 = new TestThread11();
        testThread11.start();

        for (int i = 0; i < 200; i++) {
            System.out.println("这是主线程"+i);
        }
    }
}
