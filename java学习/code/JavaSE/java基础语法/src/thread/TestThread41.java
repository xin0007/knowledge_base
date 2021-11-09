package thread;

public class TestThread41 implements Runnable{

    private int ticketNum = 20;

    @Override
    public void run() {
        while (true){
            if (ticketNum <= 0){
                break;
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ticketNum--+"票");
        }
    }

    public static void main(String[] args) {
        TestThread41 testThread41 = new TestThread41();
        new Thread(testThread41, "bill").start();
        new Thread(testThread41, "mike").start();
        new Thread(testThread41, "silk").start();

    }
}
