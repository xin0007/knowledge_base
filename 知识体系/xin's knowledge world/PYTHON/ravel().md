[[PYTHON_TAG]] #PYTHON 

a = /[[1, 2], [2, 3]/]
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