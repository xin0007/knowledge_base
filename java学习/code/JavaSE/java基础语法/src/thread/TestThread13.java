package thread;

import sun.awt.windows.ThemeReader;

public class TestThread13 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("这是多线程-----"+i);
        }
    }

    public static void main(String[] args) {
        TestThread13 testThread13 = new TestThread13();
        testThread13.start();

    }
}
