package thread;

public class TestJoin implements Runnable{


    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("vip来了"+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("主线程在跑"+i);
            if (i==20){
                thread.join();
            }
        }
    }
}
