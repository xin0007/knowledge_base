package thread;

import java.util.ArrayList;

public class UnsafeList2 {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> strings = new ArrayList<String>();
        new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                strings.add(Thread.currentThread().getName());
            }
        }).start();
        Thread.sleep(3000);
        System.out.println(strings.size());
    }
}
