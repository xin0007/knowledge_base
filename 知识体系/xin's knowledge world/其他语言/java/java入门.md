## java帝国
1. 三高问题：
高可用
高性能
高并发


## Java特性和优势
简单性
面向对象
可移植性 -- write once run anywhere
高性能 -- 即时编译
分布式
动态性 -- 反射机制
多线程 -- 更好的交互行为
安全性
健壮性


## Java三大版本
最大的特点：`Write Once, Run Anywhere`  （因为JVM，所以可以跨平台）

JavaSE：标准版 （桌面程序，控制台开发）
JavaME：嵌入式开发（小家电） -- 基本已经死掉了
JavaEE：E企业级开发（web端，服务器开发）


## JDK、JRE、JVM
JDK：Java Develop Kit
JRE：Java Runtime Environment
JVM：Java Virtual Machine
![[Pasted image 20211021110109.png]]
之间的包含关系如上图所示，下载一个JDK就可以包含所有的
JVM的存在，就是避免了底层语言的不同，实现了他的特点：`Write Once, Run Anywhere`  


## Java开发环境搭建

1. 下载
官网： https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html

2. 自动安装
mac默认地址: /Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk

3. 配置环境变量
### **windows操作**
1）系统变量： 我的电脑 - 右键 属性 - 高级系统设置 - 环境变量 - 系统变量 新建
	变量名：JAVA_HOME
	变量值：F:/aa/bb/java/jdk1.8
2）配置PATH变量：同样位置的系统变量
	Path 双击 新建：
	```bash
	%JAVA_HOME%\bin     -- %% 表示引用
	%JAVA_HOME%\jre/bin
	```
3）测试：
	```bash
	java -version
	```

### mac安装jdk

> 查看java路径
> 1. 通过terminal
> ```shell
> which java
> /Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/bin/java
> ```
> 2. 通过.bash_profile (这里也是需要设置JAVA_HOME的地方)
> ```shell
> vim ~/.bash_profile
> ```

安装完jdk后的路径配置：

1. 打开.bash_profile文件
```shell
vim ~/.bash_profile
```

2. 添加下列路径
``` shell
export JAVA_HOME=$(/usr/libexec/java_home)

export PATH=$JAVA_HOME/bin:$PATH

export CLASS_PATH=$JAVA_HOME/lib

e.g. mac路径
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH

```
3. 重新加载
```shell
source ~/.bash_profile
```
4. 测试
```shell
java -version
```

TIPS：
有些功能可能需要重启电脑，
例如：kettle在java home修改后，需要重启电脑，仅仅source不管用


### jdk里文件解释：
![[Pasted image 20211021143545.png]]

bin：里面放置了一下可执行的程序，例如 javac编译程序
include：jdk引入c语言的头文件
jre：java运行环境，如果只是运行java程序的话，有这个jre就可以了
lib：类库文件
src.zip：资源文件 类文件 - 基础类的源代码

## Helloworld - 例子

1. 编写代码 Hello.java
```java
public class Hello { 
	public static void main(String[] args) {
		System.out.print("Hello, World!");
	}
}
```

解释：
public class Hello: 类名为 Hello
public static void：修饰符 关键字 
main：方法，java核心的main方法
String[] args：参数
println: println和print基本没什么差别，就是最后会换行; println("test")相当于print("test\n")


2. 编译
```shell
javac Hello.java
```
编译成功后会生成文件：Hello.class

3. 运行
```shell
# 直接java Hello就行，不用加后缀
java Hello
```

注意：
1. Java是大小写敏感的
2. 尽量使用英文
3. 文件名和类名 必须保证一致！并且首字母大写


## 编译型和解释型
java都属于这两个，只是时机不同

1. 编译型：整本翻译  compile
缺点：如果有改变，就需要重新编译
例子：C C++
3. 解释性：翻译官  interpreter
好处：有改变，可以随时解释
缺点：性能有所损失
例如：可以在web上应用，对于速度上的要求没有这么高
例子：java

随着发展，两者的边际越来越模糊

![[Pasted image 20211021154715.png]]

javac -- java compile


## IDEA
[[jetbrain安装]]

1. 什么是IDE
集成开发环境 integrated development environment
一般包括：代码编译器、编译器、调试器和图形用户界面等工具

同样是上面的代码，但是会自动生成，
快捷键：
psvm: public static void main(String[], args)
sout: System.out.println("");

