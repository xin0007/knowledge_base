package thread;

public class TestPC3 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();

    }
}

// 演员 --> 生产者
class Player extends Thread{
    TV tv;

    public Player(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if(i%2==0){
                this.tv.play("快乐");
            }else{
                this.tv.play("记录");
            }
        }
    }
}

// 观众 --> 消费者
class Watcher extends Thread{
    TV tv;

    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}


// 节目 --> 产品
class TV {
    // 演员表演，观众等待 T
    // 观众观看，演员等待 F
    String voice; // 表演的节目
    boolean flag = true;

    // 表演
    public synchronized void play(String voice){

        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("actor acted "+voice);

        // 通知观众观看
        this.notifyAll(); // 通知唤醒
        this.voice = voice;
        this.flag = !this.flag;
    }

    // 观看
    public synchronized void watch(){
        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("audience watched "+voice);

        // 通知演员表演
        this.notifyAll();
        this.flag = !this.flag;
    }

}