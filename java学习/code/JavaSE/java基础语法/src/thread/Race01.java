package thread;

public class Race01 implements Runnable{

    public static String winner;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {


            System.out.println(Thread.currentThread().getName()+"----->跑了"+i+"步");
        }
    }

    // 判断比赛是否结束
    public boolean result(int steps){
        if(winner != null){
            return true;
        }{
            if (steps == 100){
                winner = Thread.currentThread().getName();
                System.out.println("winner is "+winner);
                return true;
            }
        }
        return false;
    }
}
