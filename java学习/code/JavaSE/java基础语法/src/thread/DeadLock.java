package thread;

public class DeadLock {
    public static void main(String[] args) {
        Makeup g1 = new Makeup(0, "灰灰");
        Makeup g2 = new Makeup(1, "白白");

        g1.start();
        g2.start();
    }
}

class Mirror{

}

class Lipstick{

}

class Makeup extends Thread{

    // 需要的资源只有一份，用static来保证只有一份
    static Mirror mirror = new Mirror();
    static Lipstick lipstick = new Lipstick();

    int choice;
    String name;

    public Makeup(int choice, String name) {
        this.choice = choice;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            makeup(choice, name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void makeup(int choice, String name) throws InterruptedException {
        if (choice == 0){
            synchronized (lipstick){ // 获得lipstick的lock
                System.out.println(this.name + " get lipstick");
                Thread.sleep(1000);
                synchronized (mirror){ // 没有释放lipstick的lock，就去请求mirro的lock
                    System.out.println(this.name+" get mirror");
                }
            }
        }else{
            synchronized (mirror){
                System.out.println(this.name + " get mirror");
                Thread.sleep(1000);
                synchronized (lipstick){
                    System.out.println(this.name + " get lipstick");
                }
            }
        }
    }
}
