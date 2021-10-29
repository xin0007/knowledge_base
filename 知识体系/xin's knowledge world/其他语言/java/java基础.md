## 注释、标识符、关键字 
1. 注释
java注释有三种
* 单行注释：// 注释
* 多行注释：/ \* 注释 * /
* 文档注释： -JavaDoc
					/** */
					
> Javadoc的作用是针对整个方法或者整个类做一个简要的概述的，使得别人不通过看具体方法代码就能知道某个方法或者某个类的作用和功能
> 写了Javadoc的在别人使用到类时，将鼠标悬停到类上或者方法上，javadoc会以提示信息显示出来

2. 标识符
- 关键字
![[Pasted image 20211021214256.png]]

Java 所有的组成部分都需要名字。类名、变量名以及方法名都被称为标识符
标识符是Java已经占用的名字，有特殊的含义，所以不能再起相同的名字，否则会报错。

注意：
- 所有的标识符都应该以字母A-Z或者a-z，美元符$ 或者下划线 _ 开始
- 首字母之后可以是字母 A-Za-z，美元符$、下划线 _ 或数字的任何字符组合
- 不能使用关键字作为变量名或方法名
- 标识符是大小写敏感的


## 数据类型

- 强类型语言
要求变量的使用要严格符合规定，所有变量都必须先定义后才能使用
例如： java
好处：安全性上升
缺点：速度下降

- 弱类型语言

Java的数据类型分为两大类：
- 基本类型（primitive type）

- 引用类型（reference type）

![[Pasted image 20211021220454.png]]

注意：
- int 整数类型最常用
- long类型要在数字最后面加 L，用来区分出long类型
```java
long num = 20L;
```
- 小数：浮点数
- float类型，要在小数后面加F，用来区分出float类型
```java
float num2 = 1.1F;
```
- 字符，用char，只能定义一个字符，例如"A", "李"，如果多于这个，就会报错
- 字符串 String 这个不是关键字，是类
- 布尔值 boolean，只有true和false 

- 不需要记住这些的大小，因为他们都有对应的类
例如：int -> Integer
![[Pasted image 20211021221425.png]]

>字节：
>位（bit）：是计算机 内部数据 储存的最小单位，11111111是一个八位二进制数字
>字节（byte）：是计算机中 数据处理 的基本单位，习惯上用B表示
>1B（byte，字节） = 8 bit（位）
>字符：是指计算机中使用的字母、数字、字和符号，例如 A，中


#### 整数拓展
进制：
- 二进制 0b
- 十进制
- 八进制 0
- 十六进制 0x
```java 
public class Demo02_01 {  
    public static void main(String[] args) {  
        int i1 = 10; //十进制  
 		int i2 = 010; // 八进制  
		int i3 = 0b10; // 二进制  
		int i4 = 0x10; // 十六进制  
  
		System.out.println(i1);  
		System.out.println(i2);  
		System.out.println(i3);  
 		System.out.println(i4);  
 }  
}
```
结果：
10
8
2
16

#### 浮点数拓展
几个测试：
1. float与double比较：
```java
public class Demo02_02 {  
    public static void main(String[] args) {  
        float f = 0.1f;  
 		double d = 0.1;  
 		System.out.println(f == d);  
 }  
}
```
结果：false

2. float与float比较：
```java
float a = 123123123123123f;  
float b = a+1;  
System.out.println(a == b);
```
结果：true

**结论**：
1. float 有限，离散，舍入误差，大约，接近但不等于
因此，**不要使用浮点数进行比较**
2. float存在的意义是，它很小，运算很快，对于一些巨大的矩阵并且计算精度不需要太高的情况下，可以使用float

**拓展：**
银行业务怎么表示浮点类型：BigDecimal


#### char类型拓展
1. char的本质还是int
```java

char c1 = 'a';  
char c2 = '中';  
System.out.println(c1);  
System.out.println((int)c1);  //强制转换
  
System.out.println(c2);  
System.out.println((int)c2);  //强制转换

```
结果：
a
97
中
20013

> TIPS:
> char的表示要用 单引号 ' '
> String 的标识要用 双引号 " "

2. 使用的是Unicode编码
会有一张编码表，每个int对应一个char，大小为 0-65536 (2^16)
例如：
97 = a
65 = A
20013 = 中

3. 反向输出
```java
char c3 = '\u0061';   // 注意使用单引号
System.out.println(c3);
```
结果： a

#### 转义字符
\t tab制表符
\n 换行


## 类型转换

- 由于Java是强类型语言，所以要进行有些运算的时候，需要用到类型转换
低 --- 高
byte,short,char -> int -> long -> float -> double
- 运算中，不同类型的数据先转化为同一类型，然后进行运算

```java
int a = 129;  // 因为byte最大值是128，所以会出现内存溢出
byte b = (byte)a;  // 这个是强制转换
System.out.println(b);
```
结果：-127 因为内存溢出了，所以”之“字形开始储存，不再正确 

#### 类型：
- 强制转换  高 -> 低
	- 转换的时候，需要加（要转换的类型）
	- 例如： int -> byte ` byte b = (byte)a;`
- 自动转换  低 -> 高
	- 转换的时候，可以直接转换
	- 例如：int -> double ` double b = a;`

注意点：
1. 不能对布尔值进行转换
2. 不能把对象类型转化为不相干的类型
3. 把高容量转换到低容量的时候，强制转换
4. 转换的时候可能存在内存溢出，或者精度问题

TIPS：
1. 大数字输入，jdk7新特性，数据之间可以用下划线分割
int money = 10_0000_0000;

2. 两个 int相乘，内存溢出，可以将其中一个转换为long类型，再进行计算，就可以解决

3. long类型，最后的l，最好用大写L表示 300L，否则容易看成数字1

## 变量、常量
#### 变量：
1. 本质是 在内存中确定一块空间，放什么还不知道，但是把位置先定死
2. Java是一种强类型语言，每个变量都必须声明其类型
3. Java变量是程序中最基本的存储单元，其要素包括变量名，变量类型和作用域

```java
type varName [=value]
```

注意事项：
- 每个变量都有类型，类型可以是基本类型，也可以是引用类型
- 变量名必须是合法的标识符
- 变量声明是一条完整的语句，因此每一个声明都必须以分号结束

#### 变量作用域
1. 局部变量
- 在方法内可用，不可以跨方法
```java
public static void main(String[] args) {  
 
    //局部变量，必须声明和初始化值  
	int i = 10;  
	System.out.println(i);
```
注意：必须要声明和初始化值

2. 实例变量
- 在类中定义，可以在不同的方法内直接调用
```java
String name;  
int age;  

public static void main(String[] args) {  
 
	// 实例变量  
	Demo08 demo08 = new Demo08();  
	System.out.println(demo08.age);  
	System.out.println(demo08.name);
```

注意：
- 可以不用初始化
- 如果不自行初始化，不同类型会有不同的默认值： 0（整数）, 0.0（浮点）, false（布尔值）， null（其他）

3. 类变量
- 在类中定义，变量前加 static，可以在不同的方法内直接调用
```java

static double salary = 2000;  

public static void main(String[] args) {  
  
    // 类变量  
	System.out.println(salary);
```
注意：
- 可以不用初始化
- 也会有默认值，如上

#### 常量
初始化后不能再改变值

final 常量名= 值；
`final double PI = 3.14;`

注意：
1. 常量名一般用大写英文
2. final和static是修饰符，不在乎先后顺序
static final double PI = 3.14;
final static double PI = 3.14;

#### 变量的命名规范
- 所有变量、方法、类名：见名知意
- 类成员变量：首字母小写和驼峰原则：monthSalary
- 局部变量：首字母小写和驼峰原则
- 常量：大写字母和下划线：MAX_VALUE
- 类名：首字母大写和驼峰原则 Man， GoodMan
- 方法名：首字母小写和驼峰原则：run(), runRun()


## 运算符
![[Pasted image 20211022163748.png]]

注意：
1. 不等于 !=
2. 与或非 && || !
3. 模运算：%  -> 余
4. 计算加减乘除的时候，要注意数值类型  -- double
5. 计算时，向上更改  
byte + short = short
short + int = int
int + long = long
long + double = double

6. ++ 自增；-- 自减法
a = 3
b = a++的话，b = 3，因为先赋值，再a = a+1
b = ++a 的话，b = 4, 因为先 a = a + 1, 再赋值

7. 幂运算
double pow = Math.pow(2,3); ==> 2*2*2

8. 短路运算
```java
int c = 5;
boolean d = (c<4) && (c++<4);

sout (c)
```
结果：5
结论：当 &&时，如果第一个为false，则直接停止运算，给出结果


> TIPS：
> ctrl + D -> 把当前行复制到下一行


#### 位运算
A = 0011 1100
B = 0000 1101

---
A&B = 0000 1100
A|B = 0011 1101
A^B = 0011 0010	(异或)
~B = 1111 0010 (取反)


**左移右移**

\>> 相当于除以2
<< 相当于乘以2

0000 0000 0
0000 0001 1
0000 0010 2
0000 0100 4
0000 1000 8
0001 0000 16
结论：
1. 发现二进制中每左移一个，就相当于十进制中乘以2的操作，右移相当于除以2。
2. 计算效率很高，因为直接跟底层打交道

```java
sout(2<<3)
```
结果：16！
因为：
2<<3 : 0000 0010左移三位，变成0001 0000 是 16，相当于 2\*2\*2\*2


9. 扩展赋值运算符
a+=b // a = a + b
a -=b // a = a - b

10. 字符串连接符号 + ,String
```java
int a = 10;  
int b = 20;  
System.out.println(""+a+b);  
System.out.println(a+b+"");
```
结果：
1020
30
结论：java的运算是从左向右的

11. 三元运算符 ?
x ? y : z
含义： if x == true, 则结果为y，否则为z

例子
```java
int score = 50;  
String re = score > 60 ? "及格" : "不及格";  
System.out.println(re);
```
结果：不及格

## 包机制(Package)

1. 包的本质：就是文件夹
2. 能够更好地组织类，用于区别类名的命名空间 -- 相当于一个文件夹里不能有重复命名的文件
3. 语法
```java
package pkg1[. pkg2[. pkg3...]];
```

4. 一般利用公司域名倒置作为包名；
例如 www.baidu.com -> com.baidu.www

5. 导入包 import
```java
import package1[.package2...].(classname|*);
```

导入包下所有的类：

```java
import package1.*;
```


> TIPS:
> IDEA Option+Return 可以帮助纠错

参考：
阿里巴巴Java开发手册

## JavaDoc
1. javadoc命令是用来生成自己API文档的
2. 参数信息
	- @author 作者名
	- @version 版本号
	- @ since 指明需要最早使用的jdk版本
	- @param 参数名
	- @return 返回值情况
	- @throws 异常抛出情况

javadoc使用：
```shell
javadoc -encoding UTF-8 -charset UTF-8 Doc.java 
```

会根据java doc来生成index.html文件：
![[Pasted image 20211026085528.png]]
![[Pasted image 20211026085541.png]]