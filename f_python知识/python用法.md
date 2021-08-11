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



## 3. np.c_


Translates slice objects to concatenation along the second axis.


```python
np.array([1,2,3])
>> array([1, 2, 3])

np.array([4,5,6])
>> array([4, 5, 6])

np.c_[np.array([1,2,3]), np.array([4,5,6])]
>> array([[1, 4],
       [2, 5],
       [3, 6]])
```

* TIPS: 必须用 [ ]



## 4. Plt.contour()

**ontour([X, Y,] Z, [levels], \** kwargs)**
绘制轮廓。

- 参数：
  - X，Y ： array-like，可选
    值Z的坐标。
    X和Y必须都是2-D，且形状与Z相同，或者它们必须都是1-d，这样len（X）== M是Z中的列数，len（Y）== N是Z中的行数。
  - Z ： array-like（N，M）
    绘制轮廓的高度值。
  - levels： int或类似数组，可选
    确定轮廓线/区域的数量和位置。
    如果int Ñ，使用Ñ数据间隔; 即绘制n + 1个等高线。水平高度自动选择。
    如果是数组，则在指定的级别绘制轮廓线。值必须按递增顺序排列。



## 5. Meshgrid

```python
nx, ny = 200, 100
x_min, x_max = plt.xlim()
y_min, y_max = plt.ylim()
x_grid, y_grid= np.meshgrid(np.linspace(x_min, x_max, nx), np.linspace(y_min, y_max, ny))
```

这里的x_grid，形状是[200, 100]

len(x_grid) = 100

len(x_grid[0]) = 200

他会分成200份，然后每一个y有一行数据，所以一共有100行












