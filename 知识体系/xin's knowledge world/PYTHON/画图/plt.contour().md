[[PYTHON_TAG]] #PYTHON 

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



