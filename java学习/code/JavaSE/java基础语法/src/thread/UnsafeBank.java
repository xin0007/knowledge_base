package thread;

public class UnsafeBank {
    public static void main(String[] args) {
        Account fuck_money = new Account(100, "ok money");

        WithDraw aa = new WithDraw(fuck_money, 50, "aa");
        WithDraw bb = new WithDraw(fuck_money, 100, "bb");

        aa.start();
        bb.start();


    }
}

class Account{
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

class WithDraw extends Thread{
    Account account;
    int drawingMoney;
    int nowMoney;

    public WithDraw(Account account, int drawingMoney, String name){
        this.account = account;
        this.drawingMoney = drawingMoney;
        super.setName(name);

    }

    @Override
    public void run() {
        synchronized (account){
            if (account.money - drawingMoney <0){
                System.out.println(Thread.currentThread().getName()+"钱不够，取不了");
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            account.money = account.money - drawingMoney;
            nowMoney = nowMoney + drawingMoney;

            System.out.println(account.name+"'s balance is "+account.money);
            //这里 Thread.currentThread().getName() = this.getName()
            System.out.println(this.getName()+"now money is "+nowMoney);
        }


    }
}
