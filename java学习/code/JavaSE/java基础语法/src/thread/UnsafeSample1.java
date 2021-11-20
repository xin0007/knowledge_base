package thread;

public class UnsafeSample1{
    public static void main(String[] args) {
        UnsafeBuyTicket unsafeBuyTicket = new UnsafeBuyTicket();

        new Thread(unsafeBuyTicket,"aaa").start();
        new Thread(unsafeBuyTicket,"bbb").start();
        new Thread(unsafeBuyTicket,"ccc").start();
    }
}

class UnsafeBuyTicket implements Runnable{
    private int ticketNum = 10;
    boolean flag = true;

    @Override
    public synchronized void run() {
        while(flag){
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void buy() throws InterruptedException {
        if(ticketNum <= 0){
            flag = false;
            return;
        }

        Thread.sleep(50);
        System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketNum--+"张票");
    }
}
