package thread;

import static java.lang.Thread.sleep;

public class TestYield {

    public static void main(String[] args) {
        MyYield myYield = new MyYield();
        new Thread(myYield,"a").start();
        new Thread(myYield,"b").start();
    }
}

class MyYield implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始了");
        Thread.yield(); // 礼让
        System.out.println(Thread.currentThread().getName()+"结束了");

    }
}
