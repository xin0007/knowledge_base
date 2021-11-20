package thread;

public class Race implements Runnable{


    public static String winner; //只会有一个winner，所以用static
    
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {

            //模拟兔子休息
            if (Thread.currentThread().getName().equals("兔子") && i%10==0){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 判断比赛是否结束
            boolean result = result(i);
            if(result){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"--->跑了第"+i+"步");
        }
    }


    public boolean result(int step){
        if (winner != null) {
            return true;
        }{
            if (step >= 100){
                winner = Thread.currentThread().getName();
                System.out.println("winner is "+winner);
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();

    }
}
