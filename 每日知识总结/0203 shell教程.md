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



5. 

