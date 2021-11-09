package thread;

public class TestThread31 implements Runnable{
    @Override
    public void run() {
        for(int i=0; i<100; i++){
            System.out.println("这是多线程--------"+i);
        }
    }

    public static void main(String[] args) {
        TestThread31 testThread31 = new TestThread31();

        new Thread(testThread31).start();

        for (int i = 0; i < 200; i++) {
            System.out.println("这是主线程"+i);
        }
    }
}
