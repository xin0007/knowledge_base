## 线程简介
#### 普通方法调用和多线程
![[Pasted image 20211107212442.png]]

#### Process和Thread
1. 程序：是指令和数据的有序集合，其本身没有任何运行的含义，是一个静态的概念
2. 进程：是执行程序的一次执行过程，它是一个动态的概念。是系统资源分配的单位
3. 通常一个程序中可以包含若干个线程，当然一个进程中至少有一个线程。线程是CPU调度和执行的单位

注意：
很多多线程是模拟出来的，真正的多线程是指有多个CPU，即多核，如服务器。如果是模拟出来的多线程，即在一个CPU的情况下，在同一个时间点，cpu只能执行一个代码，因为切换的很快，所以就有同事执行的错觉。

#### 核心概念
1. 线程就是独立的执行路径
2. 在程序运行时，即使没有自己创建线程，后台也会有多个线程，如主线程，gc线程（清理垃圾的）
3. main()称之为主线程，为程序的入口，用于执行整个程序。
4. 在一个进程中，如果开辟了多个线程，线程的运行由调度器安排调度，调度器是与操作系统紧密相关的，先后顺序是不能人为的干预的
5. 对同一份资源操作时，会存在资源抢夺的问题，需要加入并发控制
6. 线程会带来额外的开销，如cpu调度时间，并发控制开销
7. 每个线程在自己的工作内存交互，内存控制不当会造成数据的不一致

> gc （守护线程）简介
> 对于资源操作，通常分为以下几个步骤：
> 1、为对应的资源分配内存
> 2、初始化内存
> 3、使用资源
> 4、清理资源
> 5、释放内存
> 如果是手动管理会经常忘记释放内存。所以java通过gc线程自动管理释放内存


## 线程实现
#### 创建方法
1. Thread class => 继承Thread类
2. Runnable接口 => 实现Runnable接口
3. Callable接口 => 实现Callable接口

#### 继承Thread类
1. 自定义线程类继承Thread类
2. 重写run()方法，编写线程执行体
3. 创建线程对象，调用start()方法启动线程

例子：
```java
public class TestThread1 extends Thread{  // 继承Thread类
    @Override // 重写run方法
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("这是子线程"+i);
        }
    }

    public static void main(String[] args) {
        TestThread1 testThread1 = new TestThread1(); // 创建线程对象
        testThread1.start();  // 调用start()方法启动线程

        for (int i = 0; i < 2000; i++) {
            System.out.println("这是主线程"+i);
        }
    }
}

```
结果：
在main方法中，穿插运行子线程

结论：
线程开启不一定立即执行，CPU安排调度


> 添加jar包as lib
> 1. 首先从maven Repository中查找 https://mvnrepository.com/
> 2. 下载
> 3. 创建lib package，拷贝jar包到lib里
> 4. 右键lib -> Add as Library

例子：
```java
package thread;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestThread2 extends Thread{
    private String url;
    private String filepath;

    public TestThread2(String url, String filepath){
        this.url = url;
        this.filepath = filepath;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, filepath);
        System.out.println("下载了文件名为："+filepath);
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://i0.hdslb.com/bfs/videoshot/132935220.jpg", "1.jpg");
        TestThread2 t2 = new TestThread2("https://i0.hdslb.com/bfs/videoshot/132935220.jpg", "2.jpg");
        TestThread2 t3 = new TestThread2("https://i0.hdslb.com/bfs/videoshot/132935220.jpg", "3.jpg");

        t1.start();
        t2.start();
        t3.start();

    }
}

// 下载器
class WebDownloader{
    public void downloader(String url, String filepath){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }

}
```


#### 实现Runnable接口
1. 定义MyRunnable类实现Runnable接口
2. 实现run()方法，编写线程执行体
3. 创建线程对象，调用start()方法启动线程
```java
public class TestThread3 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("我在看代码----"+i);
        }
    }

    public static void main(String[] args) {
        // 创建runnable接口的实现类对象
        TestThread3 testThread3 = new TestThread3();
        // 创建线程对象，通过线程对象来开启我们的线程，代理
        Thread thread = new Thread(testThread3);
        thread.start();

        for(int i =0; i<1000;i++){
            System.out.println("我在学习"+i);
        }
    }
}
```


#### 集成Thread类 vs 实现Runnable接口
- 继承Thread类
	1. 子类集成Thread类具备多线程能力
	2. 启动线程：子类对象.start()
	3. `不建议使用`：避免OOP单继承局限性

- 实现Runnable接口
	1. 实现接口Runnable具有多线程能力
	2. 启动线程：传入目标对象+Thread对象.start()
	3. `推荐使用`：避免单继承局限性，灵活方便，方便同一个对象被多个线程使用

例子：
```java
public class TestThread4 implements Runnable{
    private int ticketNum = 10;

    @Override
    public void run() {
        while(true) {
            if (ticketNum <= 0){
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"--->拿到了第"+ticketNum--+"票");
        }
    }

    public static void main(String[] args) {
        TestThread4 testThread4 = new TestThread4();
        new Thread(testThread4, "小明").start();
        new Thread(testThread4, "小红").start();
        new Thread(testThread4, "小子").start();
        
    }
}

```


#### 实现Callable接口
1. 实现Callable接口，需要返回值类型
2. 重写call方法，需要抛出异常
3. 创建目标对象
4. 创建执行服务：ExecutorService ser = Executors.newFixedThreadPool(1); 
5. 提交执行：Future \<Boolean\> result1 = ser.submit(t1)
6. 获取结果：boolean r1 = result1.get()
7. 关闭服务：ser.shutdownNow();


## Lambda 表达式
1. 避免匿名内部类定义过多
2. 其实质属于函数式编程的概念
语法：
```java
a -> Systom.out.println("I like lambda-->"+a);

new Thread (()->Systom.out.println("I like lambda-->")).start();
```

3. 理解Functional Interface（函数式接口）是学习Java8 Lambda表达式的关键所在
4. 函数式接口的定义：
	- 任何接口，如果只包含唯一一个抽象方法，那么它就是一个函数式接口
	```java
	public interface Runnable{
		public abstract void run();
	}
	```
	- 对于函数式接口，我们可以通过Lambda表达式来创建该接口的对象


简化过程：
1. 定义一个函数式接口，然后实现类
```java
public class TestLambda {

    public static void main(String[] args) {
        ILike like = new Like();
        like.lambda();
    }
}

// 1. 定义一个函数式接口
interface ILike{
    void lambda();
}

// 2. 实现类
class Like implements ILike{

    @Override
    public void lambda() {
        System.out.println("111111");
    }
}
```

2. 内部静态类:
把类放到实现类里面，并且要加static修饰符
```java
public class TestLambda {

    // 3. 内部静态类
    static class Like2 implements ILike{

        @Override
        public void lambda() {
            System.out.println("222222");
        }
    }

    public static void main(String[] args) {
        ILike like = new Like2();
        like.lambda();
    }
}

// 1. 定义一个函数式接口
interface ILike{
    void lambda();
}
```

3. 局部内部类:
把类放到方法里面，不需要加static，因为`Inner classes cannot have static declarations`
```java
public class TestLambda {

    public static void main(String[] args) {

        // 4. 局部内部类
        class Like3 implements ILike{

            @Override
            public void lambda() {
                System.out.println("333333");
            }
        }
        
        ILike like = new Like3();
        like.lambda();
    }
}

// 1. 定义一个函数式接口
interface ILike{
    void lambda();
}
```

4. 匿名内部类，不需要class去实现接口
直接在方法里面，new一个interface，override一下方法
```java
public class TestLambda {

    public static void main(String[] args) {

        // 5. 匿名内部类，没有类的名称，必须借助接口或者父类
        ILike like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("44444");
            }
        };

        like.lambda();
    }
}

// 1. 定义一个函数式接口
interface ILike{
    void lambda();
}
```


5. 用Lambda简化
不用new 这个接口了，直接使用ILike，用 `()->{};`代替
```java
public class TestLambda {

    public static void main(String[] args) {
        
        // 6. 用Lambda简化
        ILike like = () -> {
            System.out.println("55555");
        };

        like.lambda();
    }
}

// 1. 定义一个函数式接口
interface ILike{
    void lambda();
}
```

总结：
1. 为什么要使用lambda表达式
	- 避免匿名内部类定义过多
	- 可以让代码看起来简洁
	- 去掉了一堆没有意义的代码，只留下核心的逻辑
2. 前提：
	接口为函数式接口

3. 带参数的话，可以去掉参数类型，多个也可以去掉参数类型，但是去掉就全部都去掉，必须加上括号
```java
public class TestLambda2 {


    public static void main(String[] args) {
        
        ILove iLove = (tt,ss)->{
            System.out.println(tt+ss);
        };
        
        iLove.iLove("aaa", "bbb");
        
    }

}

interface ILove{
    void iLove(String aaa, String bbb);
}
```


## 线程状态
1. 创建状态
2. 就绪状态
3. 阻塞状态
4. 运行状态
5. 死亡状态

关系如下：
![[Pasted image 20211113182929.png]]
详细解释如下：
![[Pasted image 20211113183032.png]]

相关调用方法如下：
![[Pasted image 20211113183135.png]]

#### 停止线程
1. 不推荐使用JDK提供的stop(), destroy()方法  - 本身废弃
2. 推荐线程自己停下来
3. 建议使用一个标志位进行终止变量，当flag = false时，则终止线程运行
```java
public class TestStop implements Runnable{

    private boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while(flag){
            System.out.println("run..."+i++);
        }
    }

    public void stop(){
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();

        for (int i = 0; i < 100; i++) {
            System.out.println("main"+i);
            if(i==50){
                testStop.stop();
                System.out.println("线程停止了");
            }
        }
    }
}
```


#### 线程休眠
1. sleep(时间)指定当前线程阻塞的毫秒数
2. sleep存在异常 InterrupttedException;
所以需要包围`try catch`或者方法`throws InterruptedException`
3. sleep时间到达后线程进入就绪状态
4. sleep可以模拟网络延时，倒计时等
5. 每个对象都有一个锁，sleep不会释放锁

例子：倒计时更新最新时间
```java
public class TestSleep {

    public static void main(String[] args) {

        try {Tes
            tenDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void tenDown() throws InterruptedException {
        int num = 10;
        while(true){
            Thread.sleep(1000);
            System.out.println(num--);
            if(num<=0){
                break;
            }
        }
    }
}
```

#### 线程礼让 yield
1. 礼让线程，让当前正在执行的线程暂停，但不阻塞
2. 将线程从运行状态转为就绪状态
3. 但是礼让不一定成功，与其他线程同时进入就绪状态，哪一个进入运行状态，要看CPU的心情

例子
```java
public class TestYield {

    public static void main(String[] args) {
        MyYield myYield = new MyYield();
        new Thread(myYield,"a").start();
        new Thread(myYield,"b").start();
    }
}

class MyYield implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始了");
        Thread.yield(); // 礼让
        System.out.println(Thread.currentThread().getName()+"结束了");

    }
}
```

#### 合并线程 Join
1. Join合并线程，待此线程执行完成后，再执行其他线程，其他线程阻塞
2. 想象成插队

例子：
```java
public class TestJoin implements Runnable{


    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("vip来了"+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("主线程在跑"+i);
            if (i==20){
                thread.join();
            }
        }
    }
}
```

#### 线程状态观测
Thread.State

线程状态：
-   NEW
    至今尚未启动的线程处于这种状态。
-   RUNNABLE
    正在 Java 虚拟机中执行的线程处于这种状态。
-   BLOCKED
    受阻塞并等待某个监视器锁的线程处于这种状态。
-   WAITING
    无限期地等待另一个线程来执行某一特定操作的线程处于这种状态。
-   TIME_WAITING 
    等待另一个线程来执行取决于指定等待时间的操作的线程处于这种状态。
-   TERMINATED 
    已退出的线程处于这种状态
	
一个线程可以在给定时间点处于一个状态。这些状态是不反映任何操作系统线程状态的虚拟机状态。

TIPS:
死亡后的线程就不能再启动了

例子：
```java
public class TestStatus {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("finished");
        });


        // 观察状态
        Thread.State state = thread.getState();
        System.out.println(state); // NEW

        // 观察启动后
        thread.start();
        state = thread.getState();
        System.out.println(state); //RUN

        while(state != Thread.State.TERMINATED){ // 只要线程不终止，就一直输出状态
            Thread.sleep(100);
            state = thread.getState(); // 更新线程状态
            System.out.println(state);
        }

    }
}
```


#### 线程优先级 Priority
1. Java提供一个线程调度器来监控程序中启动后进入就绪状态的所有线程，线程调度器按照优先级决定应该调度哪个线程来执行
2. 线程的优先级用数字表示，范围从1~10
	- Thread.MIN_PRIORITY = 1;
	- Thread.MAX_PRIORITY = 10;
	- Thread.NORM_PRIORITY = 5;
3. 使用以下方法改变或获取优先级
	- getPriority(), setPriority(int xxx)

例子：
```java
public class TestPriority {
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName()+"'s priority is "+ Thread.currentThread().getPriority());

        MyPriority myPriority = new MyPriority();
        Thread t1 = new Thread(myPriority);
        Thread t2 = new Thread(myPriority);
        Thread t3 = new Thread(myPriority);
        Thread t4 = new Thread(myPriority);

        t1.setPriority(1);
        t1.start();

        t2.setPriority(10);
        t2.start();

//        t3.setPriority(1);
//        t3.start();
//
//        t4.setPriority(9);
//        t4.start();


    }
}

class MyPriority implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"'s priority is "+Thread.currentThread().getPriority());
        }

    }
}
```

结论：
1.  main方法的优先级为 5
2. 优先级低只是意味着获得调度的概率低，并不是优先级低就不会别调用了，这都是看CPU的调度

#### 守护线程 （Daemon）
1. 线程分为用户线程和守护线程
2. 虚拟机必须确保用户线程执行完毕
3. 虚拟机不用等待守护线程执行完毕
4. 如：后台记录操作日志，监控内存，垃圾回收等

例子：
```java
public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        Human human = new Human();

        Thread thread = new Thread(god);
        thread.setDaemon(true); // defaulted false, false -> User Thread; true -> deamon thread
        thread.start(); // daemon thread start


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
```

结果：
在用户线程结束后，守护线程会稍微晚一点等待用户线程关闭的时候，还会运行一会

## 线程同步

#### 概念
1. 并发：同一个对象被多个线程同时操作
2. 线程同步：
	- 处理多线程问题时，多个线程访问同一个对象，并且某些线程还想修改这个对象，这是需要线程同步。
	- 线程同步其实是一种`等待机制`，多个需要同时访问此对象的线程进入这个`对象的等待池`形成队列，等待前面线程使用完毕，下一个线程再使用
	- 线程同步形成条件：队列 + 锁
	- 由于同一进程的多个线程共享同一块储存空间，在带来方便的同时，也带来了访问冲突问题，为了保证数据在方法中被访问时的正确性，在访问时加入锁机制 `synchronized`，当一个线程获得对象的排它锁，独占资源，其他线程必须等待，使用后释放锁即可。存在以下问题：
		- 一个线程持有锁会导致其他所有需要此锁的线程挂起
		- 在多线程竞争加，加锁、释放锁会导致比较多的上下文切换和调度延时，引起性能问题
		- 如果一个优先级高的线程等待一个优先级低的线程释放锁，会导致优先级倒置，引起性能问题

不安全的例子：
1. 买票，出现负数和零以及重复拿的问题
```java
public class UnsafeSample1{
    public static void main(String[] args) {
        UnsafeBuyTicket unsafeBuyTicket = new UnsafeBuyTicket();

        new Thread(unsafeBuyTicket,"aaa").start();
        new Thread(unsafeBuyTicket,"bbb").start();
        new Thread(unsafeBuyTicket,"ccc").start();
    }
}

class UnsafeBuyTicket implements Runnable{
    private int ticketNum = 10;
    boolean flag = true;

    @Override
    public void run() {
        while(flag){
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void buy() throws InterruptedException {
        if(ticketNum <= 0){
            flag = false;
            return;
        }

        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketNum--+"张票");
    }
}
```
结果：
aaa拿到了第10张票
ccc拿到了第9张票
bbb拿到了第8张票
aaa拿到了第7张票
ccc拿到了第6张票
bbb拿到了第5张票
aaa拿到了第4张票
ccc拿到了第3张票
bbb拿到了第2张票
ccc拿到了第1张票
aaa拿到了第0张票
bbb拿到了第-1张票


2. 提款
```java
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

```
结果：
ok money's balance is -50
ok money's balance is -50
bbnow money is 100
aanow money is 50

3. 对于list的操作
```java
import java.util.ArrayList;
import java.util.List;

public class UnsafeList {
    public static void main(String[] args) throws InterruptedException {
        List<String> strings = new ArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                strings.add(Thread.currentThread().getName());
            }).start();
            Thread.sleep(200);
        }
        System.out.println(strings.size());
    }
}
```
结果：
strings的size并不是10000，而是少于10000

#### Synchronize 线程同步
1. 由于我们可以通过private关键字来保证数据对象只能被方法访问，所以我们只需要针对方法提出一套机制，这套机制就是synchronized关键字，它包括两种用法：
synchronized方法和synchronized块
`同步方法：public synchronized void method(int args){}`

2. synchronized 方法控制对“对象”的访问，每个对象对应一把锁，每个synchronized方法都必须获得调用该方法的对象的锁才能执行，否则线程会阻塞，方法一旦执行，就独占该锁，知道该方法返回才释放锁，后面被阻塞的线程才能获得这个锁，继续执行
	`缺点：若将一个大的方法申明为synchronized 将会影响效率`
	
3. 同步块：synchronized(Obj){}
	- Obj 称之为同步监视器
		- Obj可以是任何对象，但是推荐使用共享资源作为同步监视器
		- 同步方法中无需指定同步监视器，因为同步方法的同步监视器就是this，就是这个对象本身，或者class
	- 同步执行器的执行过程
		1. 第一个线程访问，锁定同步监视器，执行其中代码
		2. 第二个线程访问，发现同步监视器被锁定，无法访问
		3. 第一个线程访问完毕，解锁同步监视器
		4. 第二个线程访问，发现同步监视器没有锁，然后锁定并访问

例子：
1. 解决第一个例子
```java
public class UnsafeSample1{
    public static void main(String[] args) {
        UnsafeBuyTicket unsafeBuyTicket = new UnsafeBuyTicket();

        new Thread(unsafeBuyTicket,"aaa").start();
        new Thread(unsafeBuyTicket,"bbb").start();
        new Thread(unsafeBuyTicket,"ccc").start();
    }
}

class UnsafeBuyTicket implements Runnable{
    private int ticketNum = 10;
    boolean flag = true;

    @Override
    public synchronized void run() {
        while(flag){
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void buy() throws InterruptedException {
        if(ticketNum <= 0){
            flag = false;
            return;
        }

        Thread.sleep(50);
        System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketNum--+"张票");
    }
}

```
synchronized放在buy()和run()都能成功解决，因为他们解决的都是this.ticketNum的同步问题，而run()就是调用buy

2. 解决第二个例子：同步块
```java
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

```

3. 解决第三个例子：同步块
```java
import java.util.ArrayList;
import java.util.List;

public class UnsafeList {
    public static void main(String[] args) throws InterruptedException {
        List<String> strings = new ArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                synchronized (strings){
                    strings.add(Thread.currentThread().getName());
                }
            }).start();

        }
        Thread.sleep(2000);
        System.out.println(strings.size());
    }
}
```

结论：
只需要synchronized那些 有变动的 - 有增删改功能的 Obj


#### 死锁 deadlock
1. 多个线程各自占有一些共享资源，并且互相等待其他线程占有的资源才能运行，而导致两个或者多个线程都在等待`对方释放资源`，都停止执行的情况，某一个同步块同时拥有”两个以上对象的锁“时，就可能会发生死锁问题
2. 通俗理解就是，各自那这对方需要的锁，但是都在等对方的锁，所以停滞了。
3. 产生死锁的四个必要条件：
	- 互斥条件：一个资源每次只能被一个进程使用
	- 请求与保持条件：一个进程因请求资源而阻塞时，对己获得的资源保持不放
	- 不剥夺条件：进程已获得的资源，在未使用完之前，不能强行剥夺
	- 循环等待条件：若干进程之间形成一种头尾相连的循坏等资源关系
	
例子：
形成死锁：
```java
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
```

解决死锁：
```java
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
```
解决方法：
当使用完synchronized之后，释放lock，再去等待，而不是一直拿着lock

#### Lock 锁
1. 从JDK5.0开始，Java提供了更强大的线程同步机制 -- 通过显式定义同步锁对象来实现同步。同步锁使用Lock对象充当
2. java.util.concurrent.locks.Lock接口是控制多个线程对共享资源进行访问的工具。锁提供了对共享资源的独占访问，每次只能有一个线程对Lock对象加锁，线程开始访问共享资源之前应先获得Lock对象
3. ReentrantLock类实现了Lock，它拥有与synchronized相同的并发性和内存语义，在实现线程安全的控制中，比较常用的是ReentrantLock，可以显式加锁、释放锁

4. synchronized与Lock的对比
	- Lock是显式锁（手动快起和关闭锁，别忘记关闭锁）synchronized是隐式锁，除了作用域自动释放
	- Lock只有代码块锁，synchronized有代码块锁和方发锁
	- 使用Lock锁，JVM将花费较少的时间来调度线程，性能更好。并且具有更好的扩展性（提供更多的子类）
	- 优先使用顺序：
			Lock > 同步代码块（已经进入了方法体，分配了相应资源） > 同步方法（在方法体之外）

例子：
```java
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

    public static void main(String[] args) {
        BuyTicket b1 = new BuyTicket();

        new Thread(b1).start();
        new Thread(b1).start();
        new Thread(b1).start();
    }
}

class BuyTicket implements Runnable{

    int ticketNum = 10;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){

            try{
                lock.lock();
                if (ticketNum > 0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ticketNum--);
                }else{
                    break;
                }
            }finally {
                lock.unlock();
            }
        }
    }
}
```

## 线程通信问题 -- 生产者与消费者
1. 这是一个线程同步问题，生产者和消费者共享同一个资源，并且生产者和消费者之间相互依赖，互为条件
	- 对于生产者，没有生产产品之前，要通知消费者等待，而生产了产品之后，又需要马上通知消费者消费
	- 对于消费者，在消费之后，要通知生产者已经结束消费，需要生产新的产品以供消费
	- 在生产者消费者问题中，仅有synchronized是不够的
		- synchronized可阻止并发更新同一个共享资源，实现了同步
		- synchronized不能用来实现不同线程之间的消息传递（通信）

2. Java提供了几个方法解决线程之间的通信问题
![[Pasted image 20211116223638.png]]
注意：均是Object类的方法，都只能在同步方法或者同步代码块中使用，否则会抛出异常IllegalMonitorStateException

3. 解决方法：
解决方法一：
并发协作模型“生产者/消费者模式” --> 管程法
- 生产者：负责生产数据的模块（可能是方法，对象，线程，进程）
- 消费者：负责处理数据的模块（可能是方法，对象，线程，进程）
- 缓冲区：消费者不能直接使用生产者的数据，他们之间有个“缓冲区”
**生产者将生产号的数据放入缓冲区，消费者从缓冲区拿出数据**
![[Pasted image 20211116224031.png]]

解决方法二：
并发协作模型“生产者/消费者模式” --> 信号灯法

4. 例子
管程法例子：
```java
public class TestPC {
    public static void main(String[] args) {
        SynContainer synContainer = new SynContainer();
        new Producer(synContainer).start();
        new Consumer(synContainer).start();
    }
}


// 生产者
class Producer extends Thread{

    //
    SynContainer container;

    public Producer (SynContainer container){
        this.container = container;
    }

    // 生产
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("生产了"+i+"只鸡");
            container.push(new Chicken(i));
        }
    }
}

// 消费者
class Consumer extends Thread{

    SynContainer container;

    public Consumer (SynContainer container){
        this.container = container;
    }

    // 消费
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了--->"+container.pop().id+"只鸡");
        }
    }
}

// 产品
class  Chicken {
    int id; // 产品编号

    public Chicken(int id) {
        this.id = id;
    }
}

// 缓冲区
class SynContainer{

    // 需要一个容器大小
    Chicken[] chickens = new Chicken[10]; // 这里的10是 array的size

    // 容器计数器
    int count = 0;

    // 生产者放入产品
    public synchronized void push(Chicken chicken){

        // 如果容器满了，就需要等待消费者消费
        if (count == chickens.length){
            try {
                // 通知消费者消费，生产等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 如果没有满，我们就需要丢入产品
        chickens[count] = chicken;
        count++;

        // 可以通知消费者消费了
        this.notifyAll();
    }

    // 消费者消费产品
    public synchronized Chicken pop(){
        // 判断能否消费
        if(count == 0){
            try {
                // 等待生产者生产，消费者等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 如果可以消费
        count--;
        Chicken chicken = chickens[count];

        // 吃完了，通知生产者生产
        this.notifyAll();

        return chicken;
    }



}
```


信号灯法
```java
package thread;

public class TestPC2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}

// 生产者
class Player extends Thread{
    TV tv;

    public Player(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i%2 == 0){
                this.tv.play("快乐 "+i);
            }else{
                this.tv.play("记录 "+i);
            }
        }
    }
}

//消费者
class Watcher extends Thread{

    TV tv;

    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch(tv.voice);
        }
    }
}


// 产品
class TV {

    // 演员演，观众等
    // 观众看，演员等
    boolean flag = true;
    String voice; // 节目的属性：节目名称

    //表演
    public synchronized void play(String voice){

        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Played "+voice);
        this.notifyAll();
        this.voice = voice;
        this.flag = !this.flag;
    }


    // 观看
    public synchronized void watch(String voice){
        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("watched"+voice);

        // 通知演员可以演了
        this.notifyAll();
        this.flag = !this.flag;
    }

}
```


#### 线程池
1. 背景：经常创建和销毁、使用量特别大的资源，比如并发情况下的线程，对性能影响很大
2. 思路：提前创建好多个线程，放入线程池中，使用时直接获取，使用完放回池中。可以避免频繁创建销毁、实现重复利用。类似生活中的公共交通工具
3. 好处：
	- 提高响应速度（减少了创建线程的时间）
	- 降低资源消耗（重复利用线程池中的线程，不需要每次都创建）
	- 便于线程管理（...)
		- corePoolSIze: 核心池大小
		- maximumPoolSize: 最大线程数
		- keepAliveTimeL 线程没有任务时最多保持多长时间后会终止

4. JDK5.0起，提供了线程池相关API：ExecutorService和Executors
5. ExecutorService：真正的线程池接口。常见子类ThreadPoolExecutor

例子：
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestPool {

    public static void main(String[] args) {
        // 1. 创建服务，创建线程池
        // newFixedThreadPool 参数为：线程池大小
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 执行
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());


        // 2. 关闭连接
        executorService.shutdown();
    }
}


class MyThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
```

这里设置最高线程为2，所以打印出来的Thread的名字只有1和2

## 高级主题