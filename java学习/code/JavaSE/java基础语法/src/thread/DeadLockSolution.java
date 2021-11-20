package thread;

public class DeadLockSolution {
    public static void main(String[] args) {
        MakeUp g1 = new MakeUp(0, "小红");
        MakeUp g2 = new MakeUp(1, "小绿");
        g1.start();
        g2.start();
    }
}


class Mirror2{

}

class Lipstick2{

}

class MakeUp extends Thread {
    static Mirror2 mirror2 = new Mirror2();
    static Lipstick2 lipstick2 = new Lipstick2();

    int choice;
    String name;

    public MakeUp(int choice, String name) {
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
        if (choice == 0) {
            synchronized (lipstick2) {
                System.out.println(this.name + " get lipstick");
                Thread.sleep(1000);

            }
            synchronized (mirror2) {
                System.out.println(this.name + " get mirror");
            }
        } else {
            synchronized (mirror2) {
                System.out.println(this.name + " get mirror");
                Thread.sleep(1000);

            }
            synchronized (lipstick2) {
                System.out.println(this.name + " get lipstick");
            }
        }
    }
}


