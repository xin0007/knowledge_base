package thread;


public class TestThread42 implements Runnable{

    private int ticketNum = 20;

    @Override
    public void run() {
        while (true){
            if (ticketNum==1){
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
        TestThread41 testThread41 = new TestThread41();
        new Thread(testThread41,"mm").start();
        new Thread(testThread41,"gg").start();
        new Thread(testThread41,"ss").start();

    }
}
