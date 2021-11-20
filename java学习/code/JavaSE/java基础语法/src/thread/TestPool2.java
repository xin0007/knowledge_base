package thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestPool2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(new MyThread2());
        executorService.execute(new MyThread2());
        executorService.execute(new MyThread2());


        executorService.shutdown();
    }
}

class MyThread2 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}