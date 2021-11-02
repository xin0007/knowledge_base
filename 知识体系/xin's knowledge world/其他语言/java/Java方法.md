## 何为方法

1. System.out.println() 是什么？
![[Pasted image 20211028163002.png]]

2. Java方法是语句的集合，它们在一起执行一个功能
	- 方法是解决一类问题的步骤的有序组合
	- 方法包含于类或对象中
	- 方法在程序中被创建，在其他地方被引用

3. 设计方法的原则：
方法的本意是功能块，就是实现某个功能的语句块的集合。
最好保持方法的`原子性`，就是一个方法只完成1个功能，这样利于后期的扩展

4. 方法的命名规则：
首字母小写，驼峰

例子：
```java
public static void main(String[] args) {  
    int sum = add(1, 2);  
	System.out.println(sum);  
}  
// 写一个加法的method  
public static int add(int a, int b){  
    return a+b;  
}

```
解析：
public：方法修饰符 - 访问权限修饰符，代表公共的，大家都可以用
static：方法修饰符，属于类方法，可以在其他地方被调用
int：返回值的类型  ； void 就是没有返回值的意思
add：方法的命名

## 方法的定义及调用
#### 方法的定义
1. Java的方法类似于其他语言的函数
2. 方法包含一个方法头和一个方法体
	- 修饰符：可选，告诉编译器如何调用该方法，定义了改方法的访问类型
	- 返回值类型：方法可能会返回值。有些没有返回值，可以用void
	- 方法名：方法的实际名称。方法名和参数表共同构成方法签名
	- 类型参数：像是一个占位符，当方法被调用时，传递值给参数。
		- 形参：在方法被调用时用于接收外界输入的数据
		- 实参：调用方法时实际传给方法的数据
	- 方法体：方法体包含具体的语句，定义该方法的功能

#### 方法调用
1. 调用方法：对象名.方法名（实参列表）
2. 2种调用方法，根据方法是否有返回值来选择
	- 返回一个值时，方法调用通常被当做一个值
	```java
	int larger = max(30, 40);
	```
	- 方法的返回值是void时，方法调用一定是一条语句
	```java
	System.out.println("Hello World");
	```

3. Java一定是 `值传递`

> 值传递和引用传递
> - 值传递是对基本型变量而言的,传递的是该变量的一个副本,改变副本不影响原变量.  
> - 引用传递一般是对于对象型变量而言的,传递的是该对象地址的一个副本, 并不是原对象本身 。
例子：
![[Pasted image 20211029171615.png]]
结果是 1

## 方法重载
1. 重载就是在一个类中，有相同的函数名称，但形参不同的函数。
例如一个方法，都叫max，但是里面的形参 一个是double，一个是int，那么调用max这个方法的时候，系统会自动筛选哪个类

2. 方法的重载的规则：
	- 方法名称必须相同
	- 参数列表必须不同（个数不同、或类型不同、参数排列顺序不同等）
	- 方法的返回类型可以相同也可以不同
	- 仅仅返回类型不同不足以成为方法的重载

3. 实现理论：
方法名称相同时，编译器会根据调用方法的参数个数、参数类型等去逐个匹配，以选择对应的方法，如果匹配失效，则编译器报错。




## 命令行传参
可以通过commandline来给程序传递消息。
可以靠main() 函数实现
例如
```java
public static void main(String[] args) {  
    for (int i = 0; i < args.length; i++) {  
        System.out.println(args[i]);  
 }  
    }
```
当编译完后，可以使用 java Demo03 this is input来给main方法传入”this is input“

> TIPS:
> javac 编译的时候，可以在最终的文件夹路径中使用
> 但是运行class时候，一定要根据package的路径的最顶端。
> 例如  method.Demo03，那么一定要回到package路径，并写入class的全路径运行：  java method.Demo03



## 可变参数
1. 可变参数，用于传入不知道多少个，但是同类型的参数
2. 方法声明中，在指定参数类型后加一个省略好 ...
3. 一个方法中只能指定一个可变参数，它必须是方法的最后一个参数。任何普通的参数必须在它之前声明
例子：输出最大
```java
public class Demo04 {  
    public static void main(String[] args) {  
        double re = compMax(1,2,3,4);  
 		double relist = compMax(new double[]{1,2,3});  
		System.out.println(re);  
		System.out.println(relist);  
 }  
  
    public static double compMax(double... numbers){  
        double result = -1.0;  
		if (numbers.length == 0){  
            result = 0;  
 }  
        for (int i = 0; i < numbers.length; i++) {  
            if (result < numbers[i]){  
                result = numbers[i];  
 }  
        }  
        return result;  
 }  
}
```




## 递归
1. 递归能够通过简单的程序解决一些复杂的问题 - 通过自己调用自己
2. 但是递归对于层次比较深的情况，并不好，因为java使用栈内存的概念，不断调用自己的时候，会一直堆栈，直到最深层的那个结束，所以可能会占用大量内存，造成性能影响。
3. 递归结构包括两个部分：
	- 递归头：什么时候不调用自身方法。如果没有头，将陷入死循环
	- 递归体：什么时候需要调用自身方法

例子: 
阶乘 ！
```java

public class Demo05 {  
    public static void main(String[] args) {  
        System.out.println(f(5));  
 }  
  
    public static int f(int n){  
        if(n == 1){  
            return 1;  
 		}else {  
            return n*f(n-1);  
 		}  
    }  
}
```


## 静态方法 static 与非静态方法
1. 静态方法：
- 是在类中使用staitc修饰的方法，在类定义的时候已经被装载和分配。
- 静态方法中只能调用静态成员或者方法，不能调用非静态方法或者非静态成员
2. 非静态方法：
- 非静态方法是不加static关键字的方法，在类定义时没有占用内存，只有在类被实例化成对象时，对象调用该方法才被分配内存。
- 非静态方法既可以调用静态成员或者方法又可以调用其他的非静态成员或者方法。

例子：
1. 静态调用非静态 报错：
![[Pasted image 20211029171055.png]]

2. 调用的时候可以需要实例化
![[Pasted image 20211029171353.png]]


## 调用一个package里的class
![[Pasted image 20211030164307.png]]