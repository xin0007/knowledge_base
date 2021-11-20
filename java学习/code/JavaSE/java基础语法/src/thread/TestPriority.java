package thread;

public class TestPriority {
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName()+"'s priority is "+ Thread.currentThread().getPriority());

        MyPriority myPriority = new MyPriority();
        Thread t1 = new Thread(myPriority);
        Thread t2 = new Thread(myPriority);
        Thread t3 = new Thread(myPriority);
        Thread t4 = new Thread(myPriority);

        t1.setPriority(1);
        t1.start();

        t2.setPriority(10);
        t2.start();

//        t3.setPriority(1);
//        t3.start();
//
//        t4.setPriority(9);
//        t4.start();


    }
}

class MyPriority implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"'s priority is "+Thread.currentThread().getPriority());
        }

    }
}
