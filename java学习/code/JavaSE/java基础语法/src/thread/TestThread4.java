package thread;

public class TestThread4 implements Runnable{
    private int ticketNum = 10;

    @Override
    public void run() {
        while(true) {
            if (ticketNum <= 0){
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"--->拿到了第"+ticketNum--+"票");
        }
    }

    public static void main(String[] args) {
        TestThread4 testThread4 = new TestThread4();
        new Thread(testThread4, "小明").start();
        new Thread(testThread4, "小红").start();
        new Thread(testThread4, "小子").start();

    }
}
