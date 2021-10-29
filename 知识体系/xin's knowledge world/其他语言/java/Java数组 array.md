## 数组概述
#### 数组的定义
1. 数组是相同类型数据的有序集合
2. 数组描述的是相同类型的若干个数据，按照一定的先后次序排列组合而成
3. 其中，每一个数据称作为一个数组元素，每个数组元素可以通过一个下标来访问它们。

#### 数组声明创建
1. 首先必须声明数组变量，才能再程序中使用数组
```java
dataType[] arrayRefVar;
```
例如
```java
int[] numbers;
```

2. Java语言使用new操作符来创建数组
```java
dataType[] arrayRefVar = new dataType[arraySize];
```
例如
```java
int[] numbers = new int[10]
```

例子：
```java
int[] nums; // 1. 声明一个数组  
nums = new int[10]; // 2. 创建一个数组  
  
// 3. 给数组元素赋值  
for (int i = 0; i < nums.length; i++) {  
    nums[i] = i;  
}
```

3. 数组的元素是通过索引访问的，数组索引从 `0`开始
4. 获取数组的长度
```arrays.length```

#### 数组的四个基本特点
1. 其长度是确定的。数组一旦被创建，它的大小就是不可以改变的
2. 其元素必须是相同类型，不允许出现混合类型
3. 数组中的元素可以是任何数据类型，包括基本类型和引用类型
4. 数组变量属于引用类型，数组也可以看成是对象，数组中的每个元素相当于该对象的成员变量。数组本身就是对象，Java中对象是在堆中的，因此数组无论保存原始数据类型还是其他对象类型，`数组对象本身是在堆中的`。


## 数组声明创建
内存分析 [[Java 堆和栈的讲解]]

#### 三种初始化
1. 静态初始化
```java
in[] a = {1,2,3};
Man[] mans = {new Man(1,1), new Man(2,2)};
```
数组一经创建，就不再改变

2. 动态初始化
```java
int[] a = new int[2];
a[0] = 1;
a[1] = 2;
```
先声明 再创建

3. 数组的默认初始化
数组是引用类型，它的元素相当于类的实例变量。因此数组一经分配空间，其中的每个元素也被按照实例变量同样的方式被隐式初始化。

```java
int a = new int[10];
sout(a[0]..)
```
结果：
0

#### 小结：
1. 数组是相同数据类型（数据类型可以为任意类型）的有序集合
2. 数组也是对象（所以可以使用 array.length这种 "."的操作），数据元素相当于对象的成员变量
3. 数组长度是确定的，不可变的，如果越界，则报：`ArrayIndexOutofBounds` 错误


## 数组使用
增强for循环 [[java流程控制]]
```java
int[] arrays = {1,2,3,4};

arrays.for

->

for (int i:arrays){

}
```


## 多维数组
1. 多维数组可以看成是数组的数组，比如二维数组就是一个特殊的一维数组，其每一个元素都是一个一维数组。
2. 二维数组
```java
// 定义一个 2行5列的数组
int a[][] = new int[2][5];

int a[][] = {{1,2}, {2,3}};
```


## Arrays类使用
1. 数组的工具类 java.util.Arrays
2. Arrays类中的方法都是static修饰的静态方法，在使用的时候可以直接使用类名进行调用，而“不用”使用对象类调用

3. 功能举例；
	- fill
	- sort
	- equals
	- binarySearch

```java
int[] b = {2,7,5,1};  
Arrays.sort(b);  
System.out.println(Arrays.toString(b));
```
结果：
[1, 2, 5, 7]


## 冒泡排序
1. 比较数组中，两个相邻的元素，如果第一个数比第二个数大，我们就交换他们的位置
2. 每一次比较，都会产生出一个最大，或者最小的数字
3. 下一轮则可以少一次排序
4. 依次循环，直到结束
```java
public static void main(String[] args) {  
    int[] a = {3,4,2,1,9,0};  
 	sort(a);  
}  
public static void sort(int[] array){  
    for (int j = 0; j < array.length-1; j++) {  
        for (int i = 0; i < array.length-j-1; i++) {  
            if (array[i] > array[i+1]) {  
                int tmp = array[i];  
				array[i] = array[i+1];  
				array[i+1] = tmp;  
 			}  
        }  
    }  
    System.out.println(Arrays.toString(array));  
}
```


## 稀疏数组
1. 当一个数组中大部分元素为0，或者为同一值的数组时，可以使用稀疏数组来保存该数组。
2. 处理方式：
	- 记录数组一共有几行几列，有多少个不同值
	- 把具有不同值的元素和行列及值记录在一个小规模的数组中，从而缩小程序的规模

![[Pasted image 20211029111633.png]]

