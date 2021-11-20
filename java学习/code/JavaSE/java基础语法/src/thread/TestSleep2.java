package thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class TestSleep2 {

    public static void main(String[] args) throws InterruptedException {
        tenDown();
    }

    public static void tenDown() throws InterruptedException {
        int num = 10;
        Date date = new Date(System.currentTimeMillis());


        while(true){
            Thread.sleep(1000);
            System.out.println(num--+": "+new SimpleDateFormat("HH:mm:ss").format(date));
            date = new Date(System.currentTimeMillis());

            if(num <=0){
                break;
            }
        }

    }
}
