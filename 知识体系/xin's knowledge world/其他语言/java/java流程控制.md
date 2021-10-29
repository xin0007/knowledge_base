## 用户交互Scanner

#### 语法：

```java
// 创建一个扫描器对象，用于接收键盘数据  
Scanner scanner = new Scanner(System.in);  
  
System.out.println("开始输入:");  
// 判断用户有没有输入String  
if(scanner.hasNext()){  
    //用next的方式去接收  
	String str = scanner.next();  
	System.out.println(str);  
}  
// 凡是IO流的类，不关闭都会一直占用资源  
scanner.close();
```
注意：
1. 通过Scanner类的next()与nextLine()方法获取输入的字符串
在读取前，一般需要使用hasNext() 与 hasNextLine() 判断是否还有输入的数据

2. 记得最后要关闭scanner `scanner.close()`


#### next()与nextLine()的区别：
next():
1. 一定要读取到有效字符后才可以结束输入
2. 对输入有效字符前面遇到的空白，next()方法会自动将其去掉
3. 只有输入有效字符后才将其后面输入的空白作为分隔符或者结束符
4. `next()不能得到带有空格的字符串` - 否则只会接受空格前的第一段有效字符串

nextLine():
1. 以Enter为结束符，也就是说nextLine()方法返回的是输入回车之前的所有字符。
2. 可以获得空白

####  其他不同的数据类型 
以int为例
```java
Scanner scanner = new Scanner(System.in);  
  
System.out.println("输入一个整数");  
  
if(scanner.hasNextInt()){  
    int a = scanner.nextInt();  
	System.out.println(a);  
} else {  
    System.out.println("不是一个整数");  
}  
  
scanner.close();
```

> IDEA TIPS:
> 自动补全变量类型及名称
> optinal + command + v

## 顺序结构
1. Java的基本机构就是顺序结构，除非特别指明，否则就按照顺序一句一句执行
2. 顺序结构是最简单的算法结构
3. 语句与语句之间，框与框之间是按从上到下的顺序进行的，它是由若干个一次执行的处理步骤组成的，`任何一个算法都离不开的一种基本算法结构`
![[Pasted image 20211027112106.png]]


## 选择结构
#### 类型：
- if单选择 if 
```java
int score = 60;
if (score >= 60 ){
	sout("及格")
} else {
	sout("不及格")
}
```
- if双选择 if else
- if多选择 if else if 
- 嵌套的if结构 if (if (...))

> equals 用法
```
String a = "Hello"

s.equals("Hello")
```
结果：true

#### switch功能
```java
public class SwitchDemo01 {  
    public static void main(String[] args) {  
        char a = 'C';  
		switch (a) {  
			case 'A':  
				System.out.println("优秀");  
				break; 
			case 'B':  
				System.out.println("良好");  
				break; 
			case 'C':  
				System.out.println("合格");  
				break; 
			default:  
				System.out.println("未知");  
		 }  
    }  
}
```

增加`break`是为了避免穿透，意味着当执行完这一行后，break，否则会一直往下sout

> 反编译
> 首先通过structure找到output的文件夹，然后再右键找到当前class保存的文件夹地址；
> 将output的.java文件拷贝到保存class的文件夹中，再打开
> 就可以将二进制的文件反编译为java语言

## 循环结构

#### while 循环
结构：
```java
while (boolean表达式){
	//循环内容
}
```
注意：
1. 只要是true，循环就会一直执行下去
2. 大部分情况 是会让循环停止下来的，我们需要一个让表达式失效的方式来结束循环
3. 少部分循环一直执行，比如服务器的请求监听等
4. 循环条件一直为true就会造成无限循环「死循环」，我们正常的业务变成中应该尽量避免死循环，会影响程序性能甚至造成程序卡死

例子：
```java
// 0+1+2+...+100  
int a = 0;  
int sum = 0;  
while (a <= 100){  
	sum += a;  
	a ++;  
}
```

#### do...while 循环
结构：
```java
do {
	// 循环代码
} while(boolean表达式)；
```

注意：
1. 对于while语句而言，如果不满足条件，则不能进入循环。但有时候我们需要即使不满足条件，也至少执行一次。
2. while 和 do...while的区别：
	- while先判断后执行。do while 是先执行后判断
	- Do...while总是保证循环体会至少执行一次

例子：
```java
int a = 0;  
int sum = 0;  
do {  
    sum += a;  
	a += 1;  
} while (a <= 100);

```


#### for 循环
格式
```
for (初始化；布尔表达式；更新) {
	// 代码语句
}
```

注意：
1. for循环语句是支持迭代的一种通用结构，是最有效、最灵活的循环结构
2. for循环执行的次数是在执行前就确定的
3. 最先执行初始化步骤；可以声明一种类型，但可初始化一个或多个循环控制变量，也可以是空语句
4. 然后，检测布尔值表达式的值。如果为true，循环体被执行。如果为false，循环终止，开始执行循环体后面的语句。也可以为空语句
5. 执行一次循环后，更新循环控制变量（迭代因子控制循环变量的增减）
6. 再次执行布尔表达式。循环执行上面的过程
7. 死循环
```java
for (;;){

}
```


快捷键 
> 100.for
```
for (int i = 0; i < 100; i++) {  
      
}
```
> fori
```java
for (int i = 0; i < ; i++) {

}
```

#### 增强for循环
相当于遍历数组

for(声明语句：表达式){
	// 代码语句
}

```java
int[] numbers = {10,20,30,40,50}; // 定义一个数组

for (int x:numbers) {
	//作用：遍历数组中的元素
	sout(x);
}

```

相当于：
```java
int[] numbers = {10,20,30,40,50}; // 定义一个数组
for (int i = 0; i<5; i++){
	sout(numbers[i]);
}
```

注意：
1. 声明语句：
声明新的局部变量，该变量的类型必须和数组元素的类型匹配。其作用域限定在循环语句块，其值与此时数组元素的值相等
2. 表达式：
表达式是要访问的数字明，或者范围值为数组的方法


## break & continue
1. break在任何循环语句的主体部分，均可用break控制循环的流程。break用于强行退出循环，不执行循环中剩余的语句
（break语句也可以用在switch中）
2. continue语句用在循环语句中，用于终止某次循环过程，即跳过循环体中尚未执行的语句，接着进行下一次是否执行循环的判定。





