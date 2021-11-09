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


## 线程状态

## 线程同步

## 线程通信问题

## 高级主题