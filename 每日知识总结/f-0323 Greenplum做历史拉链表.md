![5161616469100_.pic](/Users/mingxinli/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/0ab3bf1fc85b8e8addb59e3ec6bbac51/Message/MessageTemp/9e20f478899dc29eb19741386f9343c8/Image/5161616469100_.pic.jpg)



1. 事实表 vs 临时表，事实表主键为 md5(数据库、类别、schema、表格名称)

2. gp在创建事实表的时候，partitioned by 数据是否close，这样是为了利用gp的exchange partition 这个功能

3. join的功能为了速度，在sql中实现

4. 拉链表更新操作：

   - 第一次join：新数据导入tmp0表，通过主键与fact表full join，对于增加&已有数据（tmp0表非null值），储存为tmp1，等待与fact表的开口partition进行partition exchange；

     再次相同join，选取被删除的数据（tmp0表为null值），这部分数据闭口操作并insert到fact表的闭口数据中。

   - 第二次join：tmp1与tmp0通过主键join，并且用md5（字段）比对内容是否改变，对于产生变化的数据（即两个hash值不相同的数据），insert到fact表中的闭口数据





马老师方案：

![4341616580689_.pic_hd](/Users/mingxinli/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/0ab3bf1fc85b8e8addb59e3ec6bbac51/Message/MessageTemp/2da3c57a4c28ad1405ace9fa8b1832a7/Image/4341616580689_.pic_hd.jpg)











