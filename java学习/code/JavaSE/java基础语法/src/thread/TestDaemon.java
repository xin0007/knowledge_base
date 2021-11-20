package thread;

public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        Human human = new Human();

        Thread thread = new Thread(god);
        thread.setDaemon(true);
        thread.start();

        new Thread(human).start();

    }
}

class God implements Runnable{

    @Override
    public void run() {
        while (true) {
            System.out.println("God bless you");
        }
    }
}

class Human implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 30000; i++) {
            System.out.println("happy");
        }
        System.out.println("========goodbye world===========");
    }
}
