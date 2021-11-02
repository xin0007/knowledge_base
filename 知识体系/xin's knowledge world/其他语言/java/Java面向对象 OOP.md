## 面向对象概念
#### 比较面向过程与面向对象
1. 面向过程思想
	- 步骤清晰简单，第一步做什么，第二部做什么
	- 面对过程适合处理一些较为简单的问题

2. 面向对象思想
	- 物以类聚，分类的思维模式，思考问题首先会解决问题需要哪些分类，然后对这些分类进行单独思考。最后，才对某个分类下的细节进行面向过程的思索。
	- 面向对象适合处理复杂的问题，适合处理需要多人协作的问题

3. 对于描述复杂的事物，为了从宏观上把握、从整体上合理分析，我们需要使用面向对象的思路来分析整个系统。
	但是，具体到微观操作，仍然需要面向过程的思路去处理


#### 什么是面向对象
1. 面向对象编程（Object-Oriented Programming, OOP）
2. 面向对象编程的本质就是：以类的方式组织代码，以对象的组织（封装）数据。
3. 抽象
4. 三大特性：
	- 封装
	- 继承
	- 多态

5. 从认识论角度考虑是先有对象后有类。对象，具体的事物。类，是抽象的，是对对象的抽象
6. 从代码运行角度考虑是先有类后有对象。类是对象的模板

#### 类与对象的关系
1. 类是一种抽象的数据类型，它是对某一类事物整体描述/定义，但是并不能代表某一个具体的事物
例如：
- 动物、植物、手机。。。
- Person类、Pet类、Car类等，这些类都是用来描述/定义某一类具体的事物应该具备的特点和行为

2. 对象是抽象概念的具体事例
- 张三就是人的一个具体实例，张三家的旺财就是狗的一个具体实例
- 能够体现出特点，展现出功能的是具体的实例，而不是一个抽象的概念


## 对象的创建分析
1. 使用new关键字创建对象
2. 使用new关键字创建的时候，除了分配内存空间之外，还会对创建好的对象进行默认的初始化及对类中构造器的调用
例如：数字 -> 0；String -> null

3. 类中的构造器也称为构造方法，是在对进行创建对象的时候必须要调用的。并且构造器有以下两个特点：
	- 必须和类的名字相同
	- 必须没有返回类型，也不能写void

举例：
创建Student类：
```java
// 属性 + 方法  
public class Student {  
  
    // 属性  
 int age; // 默认初始 0  
 String name; // 默认初始 null  
  
 public void study(){  
        System.out.println(this.name+"在学习");  
	}  
}

```
在Application中的main方法中调用Student类：
```java
public class Application {  
    public static void main(String[] args) {  
  
        Student student = new Student();  
  
		student.name = "Mingxin.li";  
		student.study();  
 }  
}
```


小结：
1. 所有的类都是：属性 + 方法
2. this的功能，就是调用此类的属性和方法
3. 一个package理论上应该只有一个main方法，其他的class用来写功能，main方法负责主的调用
4. 上述的例子中，小写的student就是一个实例，new的方法就是用Student类

#### 构造器/构造方法 contructor
1. 构造器要求：
	- 和类名相同
	- 没有返回值

2. 作用：
	- new 本质在调用构造方法
	- 初始化对象的值

3. 注意点：
	- 定义有参构造之后，如果想使用无参构造，需要定义一个无参的构造
	- 会自动根据参数 来寻找对应的构造
	- 一个类即使什么都不写，也会有一个默认的什么都没有的构造器（可以通过生成的class来查看），但是当写了有参构造后，默认的无参构造就消失了，如果想要用的话需要再自己写出一个
4. 快捷键：control + enter
![[Pasted image 20211031154741.png]]

可以选择参数进行构造器的创建
![[Pasted image 20211031154757.png]]

#### 创造对象内存分析：
构造一个Pet()类，name，age，和一个shout()方法
赋值，并调用shout方法

1. 用Pet()类new一个对象，命名为dog
![[Pasted image 20211031160224.png]]

2. 赋值
![[Pasted image 20211031170928.png]]

3. 再new一个对象，命名为cat
![[Pasted image 20211031171030.png]]



## 面向对象的三大特性
### 封装
1. 该露的露，该藏的藏
程序设计要求“高内聚、低耦合”
高内聚：类的内部数据操作细节自己完成，不允许外部干涉
低耦合：仅暴露少量的方法给外部使用

2. 封装（数据的隐藏）
通常，应禁止直接访问一个对象中的数据的实际表示，而应通过操作接口来访问，这称之为信息隐藏

3. 关键点：
属性私有 + get/set

例子：

在Student类中
```java
public class Student {  
    private String name;  
	private int age;  
	private char gender;  
  
	public void setName(String name){  
        this.name = name;  
 }  
  
    public String getName(){  
        return this.name;  
 }  
  
    public int getAge() {  
        return age;  
 }  
  
    public void setAge(int age) {  
        if(age>120){  
            this.age = 3;  
		}else{  
            this.age = age;  
 }  
    }  
  
    public char getGender() {  
        return gender;  
 }  
  
    public void setGender(char gender) {  
        this.gender = gender;  
 }  
}
```
在main方法中调用:
```java
import oop.Demo04.Student;  
  
public class Application {  
    public static void main(String[] args) {  
        Student s1 = new Student();  
  
 s1.setName("ming");  
 System.out.println(s1.getName());  
  
 s1.setAge(4);  
 System.out.println(s1.getAge());  
 }  
}
```

1. 通过get和set来对数据进行赋值和获取
2. 类中的属性 用 private私有化
3. 快捷键 control + enter
![[Pasted image 20211031174358.png]]

封装的意义：
1. 提高程序的安全性，保护数据
2. 隐藏代码的实现细节
3. 统一接口 -- 都用 get set
4. 系统可维护性增加了

### 继承
1. 继承的本质是对某一批类的抽象，从而实现对现实世界更好的建模
2. extends的意思是：扩展，子类是父类的扩展。
语法
```java
public class SonClass extends FatherClass{} 
```

3. Java中类只有单继承，没有多继承。
-- 一个儿子只能有一个爸爸，而一个爸爸可以有多个儿子

4. 继承是类和类之间的一种关系。除此之外，类和类之间的关系还有依赖、组合、聚合等
5. 继承关系的两个类，一个为子类（派生类），一个为父类（基类），意义上讲具有is a 的关系

6. Object类：
在Java中，所有的类，都默认直接或者间接继承Object - 一些默认的功能

7. 快捷键 control + h
打开集成列表：Hierarchy
![[Pasted image 20211031221606.png]]
能看到Student继承了Person类

8. 但是如果是private的属性或者方法，子类对这些是无法被继承


例子：
创建一个Person类，属性：名字
```java
public class Person {  
    private String name;  
  
	public String getName() {  
       return name;  
 }  
  
	public void setName(String name) {  
       this.name = name;  
 }  
}
```
创建一个Student类，继承Person类：
```java
public class Student  extends Person{  
}
```
在main方法中，直接new一个Student类并调用，发现Person类的属性，Student也可以用，及时Student类什么都没写
```java
public class Application {  
    public static void main(String[] args) {  
        Student student = new Student();  
		student.setName("Mingxin");  
		System.out.println(student.getName());  
 }  
}
```
结果：Mingxin

#### super vs this
super注意点:
1. super调用父类的构造方法，必须要构造方法的第一个
2. super必须只能出现在子类或者构造方法中
3. super和this不能同时调用构造方法

vs this：
1. 代表的对象不同
	- this：代表调用者这个对象
	- super：代表父类对象的引用
2. 前提：
	- this：没有继承也可以使用
	- super：只能在继承条件才可以使用
3. 构造方法；
	- this()：本类的构造
	- super(): 父类的构造

> 权限控制表
> ![[Pasted image 20211031224153.png]]

例子：
1. 调用属性
父类: 
定义一个name属性
```java
public class Person {  
    protected String name = "是个人";  
}
```
子类：
定义一个子类属性，并且创建方法，分别打印形参，this.name和super.name
```java
public class Student  extends Person{  
    protected String name = "是个学生";  
  
 public void test(String name){  
        System.out.println(name);  
		System.out.println(this.name);  
		System.out.println(super.name);  
 }  
}
```
使用main方法调用:
```java
ublic class Application {  
    public static void main(String[] args) {  
        Student student = new Student();  
 		student.test("传进去的");  
 }  
}
```
结果：
调用test方法，分别打印为：
传进去的
是个学生
是个人

2. 调用方法
父类：
定义一个方法：print()
```java
public class Person {  
    public void print(){  
        System.out.println("这是个人类");  
 }  
}
```
子类：
同样定义一个print()的方法，并且调用this.print()和super.print()
```java
public class Student  extends Person{  
    protected String name = "是个学生";  
  
	public void print() {  
        System.out.println("这是个学生类");  
 }  
	public void test1(){  
        print();  
		this.print();  
		super.print();  
	}  
}
```
注意：这里的print会覆盖继承的print()，但是并不会影响super.print()

在main方法中调用：
```java
public class Application {  
    public static void main(String[] args) {  
        Student student = new Student();  
		student.test1();  
	}  
}
```
结果：
这是个学生类
这是个学生类
这是个人类

3. 调用构造器：
父类：
定义一个构造器，打印“父类无参执行了”
```java
public class Person {  
    public Person() {  
        System.out.println("父类无参执行了");  
 }  
}
```

子类：
定义了一个构造器，打印“子类无参执行了”
```java
public class Person {  
    public Person() {  
        System.out.println("父类无参执行了");  
 }  
}
```

main方法调用：
```java
public class Application {  
    public static void main(String[] args) {  
        Student student = new Student();  
 }  
}
```
结果：
父类无参执行了
子类无参执行了

因此说明了 -> 父类构造器也被调用了，其中调用的代码是隐藏的`super();`

注意：
1. 调用父类的构造器，必须要在子类构造器的第一行
2. 如果父类创建了有参构造，意味着无参构造没有了，会发现子类也无法写无参构造。但是可以调用父类的有参。
因此，最好要保证父类有`无参构造`！

#### 重写
1. 重写：需要有继承关系，子类重写父类的方法
2. 重写注意的点：
- 方法名必须相同
- 参数列表必须相同
- 修饰符：范围可以扩大但不能缩小：public > protected > default > private
- 修饰符：使用static的，不会被重写，会被继承
- 抛出的异常：范围，可以被缩小，但不能扩大   ClassNotFoundException < Exception



3. 重写，子类的方法和父类必须一致，但是方法体不同

4. 重写的意义：
- 父类的功能，子类不一定需要，或者不一定满足

5. 快捷键：
control + enter
![[Pasted image 20211101091942.png]]


例子：
1. 使用static
父类：
```java
public class A {  
    public static void test(){  
        System.out.println("A=>...");  
	}  
}
```
子类：
```java
public class B extends A{  
    public static void test(){  
        System.out.println("B=>...");  
	}  
}
```
父类和子类都有一个test()方法，修饰符为static
在main方法中调用：
```java
public class Application {  
    public static void main(String[] args) {  
        B b = new B();  
		b.test();  
  
  		// 父类的引用指向了子类
		A a = new B();  
		a.test();  
	}  
}
```
结果：
B=>...
A=>...
结论：
- 使用static的话不会被覆盖，而是继承
- 调用的方法只与左边定义的数据类型有关

2. 非静态
父类：
```java
public class A {  
    public void test(){  
        System.out.println("A=>...");  
	}  
}
```
子类：
```java
public class B extends A{  
    public void test(){  
        System.out.println("B=>...");  
	}  
}
```
父类和子类都有一个test()方法，修饰符为static
在main方法中调用：
```java
public class Application {  
    public static void main(String[] args) {  
        B b = new B();  
		b.test();  
  
  		// 父类的引用指向了子类
		A a = new B();  
		a.test();  
	}  
}
```
结果：
B=>...
B=>...
结论：
- 方法被覆盖了

### 多态
1. 即同一方法可以根据发送对象的不同而采用多种不同的行为方式
2. 一个对象的实际类型是确定的，但可以指向对象的引用的类型有很多

3. 多态存在的条件：
	- 有继承关系
	- 子类重写父类方法
	- 父类引用指向子类对象

4. 注意：多态是方法的多态，属性没有多态性

例子：
1. 子类重写父类，main调用这个方法
父类：
```java
public class Person {  
    public void run(){  
        System.out.println("this is father");  
	}  
}
```

子类：
```java
public class Student extends Person{  
    public void run() {  
        System.out.println("this is son");  
	}  
}
```

main方法调用，可以方法的类型不同：
```java
import oop.Demo06.Person;  
import oop.Demo06.Student;  
  
public class Application {  
    public static void main(String[] args) {  
        Student s1 = new Student();  
		Person s2 = new Student();  
		s2.run();  
		s1.run();  
	}  
}
```

2. 子类新建方法，父类不存在此方法，main方法调用：需要引用类型转换
子类：有eat方法，但是父类没有
```java
public class Student extends Person{  
    public void run() {  
        System.out.println("this is son");  
 }  
    public void eat(){  
        System.out.println("eat");  
	}  
}
```
main：
```java
public class Application {  
    public static void main(String[] args) {  
        Student s1 = new Student();  
		Person s2 = new Student();  

		s1.eat();  
		((Student) s2).eat();  // 引用类型转换了
	}  
}
```

TIPS：
哪些方法不能被重写 override：
1. static方法，属于类，它不属于实例
2. final常量
3. private 方法 

#### instanceof 
用来测试一个对象是否为一个类的实例

例子：
继承关系：
Object -> Person -> Student
Object -> Person -> Teacher
Object -> String
```java
public class Application {
    public static void main(String[] args) {
        Object obj = new Student();

        System.out.println(obj instanceof Student); //true
        System.out.println(obj instanceof Person); //true
        System.out.println(obj instanceof Object); //true
        System.out.println(obj instanceof Teacher); //false
        System.out.println(obj instanceof String); //false

        System.out.println("==========");

        Person s2 = new Student();
        System.out.println(s2 instanceof Student); //true
        System.out.println(s2 instanceof Person); //true
        System.out.println(s2 instanceof Object); //true
        System.out.println(s2 instanceof Teacher); //false
        // System.out.println(s2 instanceof String); 编译报错

        System.out.println("==========");
        Student s3 = new Student();
        System.out.println(s3 instanceof Student); //true
        System.out.println(s3 instanceof Person); //true
        System.out.println(s3 instanceof Object); //true
        // System.out.println(s3 instanceof Teacher); 编译报错
        // System.out.println(s3 instanceof String); 编译报错
    }
}
```

#### 类型转换 引用类型
类似基础类型的转换，引用类型也可以转换。
转换规则：
1. 低往高转换，可以自动转换
```java
// Person为父类，Student为子类
Person p1 = new Student();
```

2. 高往低转换，需要强制转换`（子类型）name`
```java
//父类类型 使用子类的go()方法
Person obj = new Student();
((Student) obj).go()
```
注意：子类转换为父类，可能丢失自己的本来的一些方法

类型转换概念：
1. 父类引用指向子类的对象
2. 把子类转换为父类，向上转型，不需要操作
3. 把父类转换为子类，向下转型，强制转换
4. 方便方法的调用，减少重复的代码，简洁


## 抽象类和接口

## 内部类及OOP实战