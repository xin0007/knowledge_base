package thread;

public class TestThread32 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("这是多线程-----"+i);
        }
    }

    public static void main(String[] args) {
        TestThread32 testThread32 = new TestThread32();
        new Thread(testThread32).start();

        for (int i = 0; i < 200; i++) {
            System.out.println("这是主线程"+i);
        }

    }
}
