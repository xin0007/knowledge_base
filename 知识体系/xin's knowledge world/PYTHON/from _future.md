
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

