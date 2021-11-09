package thread;

public class TestThread12 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("这是多线程-----"+i);
        }
    }

    public static void main(String[] args) {
        TestThread12 testThread12 = new TestThread12();
        testThread12.start();

        for (int i = 0; i < 200; i++) {
            System.out.println("这是主线程"+i);
        }
    }
}
