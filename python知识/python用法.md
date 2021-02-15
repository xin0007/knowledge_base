## 1. from __future

我们在读代码的时候，总是会看到代码开头会加上from __future__ import *这样的语句。这样的做法的作用就是将新版本的特性引进当前版本中，也就是说我们可以在当前版本使用新版本的一些特性。



```python
# python 2.x
print "Hello World"
 
# python 3.x
print("Hello World")
```

如果你想用python2.x体验python3.x的写法，就可以使用from __future__ import print_function来实现，

```python
# python 2.x
from __future__ import print_function
print("Hello World")
```



## 2. Ravel()

a = [[1, 2], [2, 3]]
a.ravel() #ravel()方法将数组维度拉成一维数组

```python
import numpy as np #导入numpy模块
a, b = np.mgrid[1:4:1, 2:3:1] #用mgrid()方法生成等差数组a,b
print("a:",a,"\n","b:",b) #打印a,b
Aftera = a.ravel() #用ravel()方法将数组a拉成一维数组
print("Aftera:",Aftera) #打印a
```

结果

```python
a: [[1]
 [2]
 [3]] 
 b: [[2]
 [2]
 [2]]
Aftera: [1 2 3]
```











