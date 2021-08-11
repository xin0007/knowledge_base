## shell  教程

本教程关注的是bash，也就是Bourne Again Shell，由于易用和免费，Bash 在日常工作中被广泛使用。同时，Bash 也是大多数Linux 系统默认的 Shell。



### shell脚本

1. 运行shell脚本

   - ```
     **作为可执行程序**
     chmod +x ./test.sh #使脚本具有执行权限
     ./test.sh
     ```

     注意，一定要写成 **./test.sh**，而不是 **test.sh**，运行其它二进制的程序也一样，直接写 test.sh，linux 系统会去 PATH 里寻找有没有叫 test.sh 的，而只有 /bin, /sbin, /usr/bin，/usr/sbin 等在 PATH 里，你的当前目录通常不在 PATH 里，所以写成 test.sh 是会找不到命令的，要用 ./test.sh 告诉系统说，就在当前目录找。

   - ```
     **作为解释器参数**
     /bin/sh test.sh
     /bin/php test.php
     ```

### shell script

1. #！是一个约定的标记，它高旭系统这个脚本需要什么解释器来执行，即使用哪一种shell
2. echo命令：用于向窗口输出文本

```shell
#!/bin/bash
echo "Hello World !"
```

3. shell 变量

   定义变量时，变量名不加美元符号（$，PHP语言中变量需要），如：

   ```shell
   your_name='aaa'
   ```

   注意，变量名和等号之间不能有空格，这可能和你熟悉的所有编程语言都不一样。同时，变量名的命名须遵循如下规则：

   - 命名只能使用英文字母，数字和下划线，首个字符不能以数字开头。
   - 中间不能有空格，可以使用下划线（_）。
   - 不能使用标点符号。
   - 不能使用bash里的关键字（可用help命令查看保留关键字）。

```shell
**将/etc下目录的文件名循环出来**
for file in 'ls /etc'
或者
for file in $(ls /etc)
```

​	

​	使用变量：

​	使用一个定义过的变量，只要在变量名前面加美元符号即可，如

```shell
your_name='aaa'
echo $your_name
echo ${your_name}
```

变量名外面的花括号可选，加不加都行，花括号是为了帮助解释器识别变量的边界，如

```shell
for skill in Ada Coffe Action Java; do
	echo "I am good at ${skill}Scipt"
done
```

注意：变量赋值的使用不加$，只有在使用变量的时候才使用$

```shell
your_name='aaa'
echo ${your_name}
your_name='bbb'
echo ${your_name}
```



​	变量只读：

​	使用readonly命令可以将变量定义为只读变量

```shell
#! /bin/bash
myname='aaa'
readonly myname
```

​	删除变量：

​	使用unset命令，变量被删除后不能再次使用，unset命令不能删除只读变量

```shell
unset myname
```



4. Shell 字符串

   字符串是shell编程中最常用最有用的数据类型（除了数字和字符串，也没啥其它类型好用了），字符串可以用单引号，也可以用双引号，也可以不用引号。

   - 单引号

     ```shell
     str='this is a string'
     ```

     单引号字符串的限制：

     - 单引号里的任何字符都会原样输出，单引号字符串中的变量是无效的；
     - 单引号字串中不能出现单独一个的单引号（对单引号使用转义符后也不行），但可成对出现，作为字符串拼接使用。

   - 双引号

     ```shell
     your_name='aaa'
     str="hello, I know you are \"${your_name}\"!\n"
     echo -e $str
     ```

     双引号有点：

     - 双引号里可以有变量
     - 双引号里可以出现转义字符

   - 拼接字符串

     ```shell
     your_name="runoob"
     # 使用双引号拼接
     greeting="hello, "$your_name" !"
     greeting_1="hello, ${your_name} !"
     echo $greeting  $greeting_1
     # 使用单引号拼接
     greeting_2='hello, '$your_name' !'
     greeting_3='hello, ${your_name} !'
     echo $greeting_2  $greeting_3
     ```

     输出结果：

     ```shell
     hello, runoob ! hello, runoob !
     hello, runoob ! hello, ${your_name} !
     ```

   - 拼接字符串

     从字符串第2个字符开始截取4个字符

     ```shell
     string='he is a good man'
     echo ${string:1:4}
     ```

     输出结果：

     ```shell
      e is
     ```



5. Shell 数组

   bash支持一维数组（不支持多维数组），并且没有限定数组的大小

   数组元素的下标由0开始编号，获取数组中的元素要利用下标，下标可以是整数或算术表达式，其值应大于或等于0



​		定义数组

​		在shell中，用括号来表示数组，数组元素用”空格“符号分割，定义数组的一般形式

```shell
数组名=(v1 v2 ... vn)
```

​		例如：

```shell
array_name=(value0 value1 value2 value3)
```

或者

```shell
array_name=(
value0
value1
value2
value3
)
```

还可以单独定义数组的各个分量：

```shell
array_name[0]=value0
array_name[1]=value1
array_name[n]=valuen
```



​		读取数组

```
${数组名[下标]}
```

例如：

```
valuen=${array_name[n]}
```

使用 **@** 符号可以获取数组中的所有元素，例如：

```shell
echo ${array_name[@]}
```



6. shell 传递参数

   可以在执行shell脚本时，向脚本传递参数

   格式 $n， n代表一个数字，1位执行脚本的第一个参数，2位执行脚本的第二个参数，以此类推.....

   实例：

   以下实例我们向脚本传递三个参数，并分别输出，其中 **$0** 为执行的文件名（包含文件路径）：

   ```shell
   #!/bin/bash
   # author:菜鸟教程
   # url:www.runoob.com
   
   echo "Shell 传递参数实例！";
   echo "执行的文件名：$0";
   echo "第一个参数为：$1";
   echo "第二个参数为：$2";
   echo "第三个参数为：$3";
   ```

   为脚本设置可执行权限，并执行脚本，输出结果如下所示：

   ```shell
   $ chmod +x test.sh 
   $ ./test.sh 1 2 3
   Shell 传递参数实例！
   执行的文件名：./test.sh
   第一个参数为：1
   第二个参数为：2
   第三个参数为：3
   ```

另外，还有几个特殊字符用来处理参数：

| 参数处理 | 说明                                                         |
| :------- | :----------------------------------------------------------- |
| $#       | 传递到脚本的参数个数                                         |
| $*       | 以一个单字符串显示所有向脚本传递的参数。 如"$*"用「"」括起来的情况、以"$1 $2 … $n"的形式输出所有参数。 |
| $$       | 脚本运行的当前进程ID号                                       |
| $!       | 后台运行的最后一个进程的ID号                                 |
| $@       | 与$*相同，但是使用时加引号，并在引号中返回每个参数。 如"$@"用「"」括起来的情况、以"$1" "$2" … "$n" 的形式输出所有参数。 |
| $-       | 显示Shell使用的当前选项，与[set命令](https://www.runoob.com/linux/linux-comm-set.html)功能相同。 |
| $?       | 显示最后命令的退出状态。0表示没有错误，其他任何值表明有错误。 |

```shell
#!/bin/bash
# author:菜鸟教程
# url:www.runoob.com

echo "Shell 传递参数实例！";
echo "第一个参数为：$1";

echo "参数个数为：$#";
echo "传递的参数作为一个字符串显示：$*";
```

执行脚本，输出结果如下所示：

```shell
$ chmod +x test.sh 
$ ./test.sh 1 2 3
Shell 传递参数实例！
第一个参数为：1
参数个数为：3
传递的参数作为一个字符串显示：1 2 3
```

- 相同点：都是引用所有参数。
- 不同点：只有在双引号中体现出来。假设在脚本运行时写了三个参数 1、2、3，，则 " * " 等价于 "1 2 3"（传递了一个参数），而 "@" 等价于 "1" "2" "3"（传递了三个参数）。



7. shell 循环

```shell
echo "-- \$* 演示 ---"
for i in "$*"; do
	echo $i
done

echo "-- \$@ 演示 ---"
for i in "$@"; do
	echo $i
done
```



8. if then 

```shell
a=10
b=20

if [ $a -eq $b ]
then
	echo "$a -eq $b : a 等于 b"
else
	echo "$a -eq $b : a 不等于 b"
fi
```







