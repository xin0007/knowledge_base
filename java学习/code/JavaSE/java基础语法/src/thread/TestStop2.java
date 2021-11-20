package thread;

public class TestStop2 implements Runnable{

    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while(flag){
            System.out.println("running---->"+i++);
        }
    }

    public void stop(){
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop2 testStop2 = new TestStop2();
        new Thread(testStop2).start();

        for (int i = 0; i < 100; i++) {
            System.out.println("main is running"+i);
            if(i==50){
                testStop2.stop();
                System.out.println("thread finished");
        }

    }
    }
}
