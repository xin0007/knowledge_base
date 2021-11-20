package thread;

public class UnsafeBuyTicket1 {

    public static void main(String[] args) {
        UnsafeBuy unsafeBuy = new UnsafeBuy();
        new Thread(unsafeBuy, "aaa").start();
        new Thread(unsafeBuy, "bbb").start();
        new Thread(unsafeBuy, "ccc").start();

    }
}

class UnsafeBuy implements Runnable {

    private int ticketNum = 10;

    @Override
    public void run() {
        if (ticketNum >= 0) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNum-- + "张票");
        }
    }
}

