package thread;

public class TestStop implements Runnable{

    private boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while(flag){
            System.out.println("run..."+i++);
        }
    }

    public void stop(){
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();

        for (int i = 0; i < 100; i++) {
            System.out.println("main"+i);
            if(i==50){
                testStop.stop();
                System.out.println("线程停止了");
            }
        }
    }
}


