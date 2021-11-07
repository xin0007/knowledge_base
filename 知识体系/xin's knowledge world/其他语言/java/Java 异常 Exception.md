## 什么是异常？
异常 Exception

#### 异常分类：
1. 检查性异常：
最具代表的检查性异常是用户错误或问题引起的异常，这是程序员无法预见的。例如要打开一个不存在文件时，一个异常就发生了，这些异常在编译时不能被简单地忽略。

2. 运行时异常：
运行时异常是可能被程序员避免的异常。与检查性异常相反，运行时异常可以再编译时被忽略

3. 错误 EEROR：
错误不是异常，而是脱离程序员控制的问题。错误在代码中通常被忽略。例如，当栈溢出时，一个错误就发生了，它们在编译时也检查不到

#### 异常体系结构
1. Java把异常当作对象来处理，并定义一个基类 java.lang.Throwable作为所有异常的超类
2. Java API中已经定义了许多异常类，这些异常类分为两大类，Error和Exception
![[Pasted image 20211103225156.png]]


#### Error
1. Error类对象由Java虚拟机生成并抛出，大多数错误与代码执行者所执行的操作无关
2. Java虚拟机运行错误（Virtual MachineError），当JVM不再有继续执行操作所需的内存资源时，将出现OutOfMemoryError。这些异常发生时，Java虚拟机（JVM）一般会选择线程终止
3. 还有发生在虚拟机试图执行应用时，如类定义错误（NoClassDefFoundError）、链接错误（LinkageError）。这些错误是不可查的，因为它们在应用程序的控制和处理能力之外，而且绝大多数是程序运行时不允许出现的状况。

#### Exception
1. 在Exception分支中有一个重要的子类RuntimException（运行时异常）
	- ArrayIndexOutofBoundsException
	- NullPointerException 空指针异常
	- ArithmeticException 算术异常 例如 12/0
	- MissingResourceException 丢失资源
	- ClassNotFoundException 找不到类

2. 这些异常一般是由程序逻辑错误引起的，程序应该从逻辑角度尽可能避免这些异常的发生

3. Error和Exception的区别
Error通常是灾难性的致命的错误，是程序无法控制和处理的，当出现这些异常时，Java虚拟机一般会选择终止线程
Exception通常情况下是可以被程序处理的，并且在程序中应该尽可能的去处理这些异常


## Java异常处理机制
1. 抛出异常
2. 捕获异常

3. 异常处理五个关键字：
	try, catch, finally, throw, throws
	
4. 快捷键
option + command + T

![[Pasted image 20211104084043.png]]
```java
try {
     System.out.println(a/b);
     } catch (Exception e) {
     e.printStackTrace(); // 打印错误的栈信息
     } finally {
     }
```

例子：
1. try, catch, finally
```java
public static void main(String[] args) {
        int a = 10;
        int b = 0;
        try{
            System.out.println(a/b);
        }catch (ArithmeticException ae){
            System.out.println("不能除以0");
        }finally{
            System.out.println("这是final");
        }
    }
```
结果：
不能除以0
这是final

注意：
- 在catch的时候，后面的Exception要起名字
- try和catch成对出现，finally可以选择。
- finally可以应用在 假设IO，关闭资源等用途

2. catch写多个，catch不同的Exception或error
```java
public static void main(String[] args) {
        int a = 10;
        int b = 0;
        try{
            System.out.println(a/b);
        }catch (ArithmeticException ae){
            System.out.println("不能除以0");
        }catch(Exception e){
            System.out.println("Exception");
        }catch(Error ee){
            System.out.println("Error");
        }catch (Throwable tr){
            System.out.println("Throwable");
        }finally{
            System.out.println("这是final");
        }
    }
```

3. throw 抛出异常 - 主动抛出异常
通常在方法里使用
```java
public static void main(String[] args) {
        new Test().test(1,0);

    }
    public void test(int a, int b){
        if (b == 0) {
            throw new ArithmeticException();
        }
    }
```
结果：
Exception in thread "main" java.lang.ArithmeticException
	at exception.Test.test(Test.java:10)
	at exception.Test.main(Test.java:5)
	

4. throws 用于方法上抛出异常
```java
public void test(int a, int b) throws ArithmeticException{
        if (b == 0) {
            throw new ArithmeticException();
        }
    }
```


## 自定义异常
1. 使用Java内置的异常类可以描述在编程时出现的大部分异常情况。除此之外，用户还可以自定义异常。用户自定义异常类，只需继承Exception类即可。

2. 在程序中使用自定义异常类，大体可以分为以下几个步骤：
	- 创建自定义异常类
	- 在方法中通过throw关键字抛出异常对象
	- 如果在当前抛出异常的方法中处理异常，可以使用try-catch语句捕获并处理；否则在方法的声明处通过throws关键字指明要抛出给方法调用者的异常，继续进行下一步操作
	- 在出现异常方法的调用者中捕获并处理异常

3. 实际应用的经验总结
	- 处理运行时异常，采用逻辑去合理规避同时辅助try-catch 处理
	- 在多重catch块后面，可以加一个catch(Exception)来处理可能会被遗漏的异常
	- 对于不确定的代码，也可以加上try-catch，处理潜在的异常
	- 尽量去处理异常，切忌只是简单地调用printStachTract()去打印输出
	- 具体如何处理异常，要根据不同的业务需求和异常类型去决定
	- 尽量添加finally语句块去释放占用的资源
