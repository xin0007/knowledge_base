greenplum 连接报错![3871615790789_.pic](/Users/mingxinli/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/0ab3bf1fc85b8e8addb59e3ec6bbac51/Message/MessageTemp/2da3c57a4c28ad1405ace9fa8b1832a7/Image/3871615790789_.pic.jpg)

可以ping通，但是无法登陆



解决方法：

1. 

![3951615792058_.pic_hd](/Users/mingxinli/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/0ab3bf1fc85b8e8addb59e3ec6bbac51/Message/MessageTemp/2da3c57a4c28ad1405ace9fa8b1832a7/Image/3951615792058_.pic_hd.jpg)

添加下方

![3961615792067_.pic_hd](/Users/mingxinli/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/0ab3bf1fc85b8e8addb59e3ec6bbac51/Message/MessageTemp/2da3c57a4c28ad1405ace9fa8b1832a7/Image/3961615792067_.pic_hd.jpg)



tips: 

IP/32 表示您的IP地址子网掩码是32位的子网，也就是255.255.255.255。



2. 更改后：

   用gpadmin用户 执行 gpstop -u 刷新配置

![4021615792173_.pic_hd](/Users/mingxinli/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/0ab3bf1fc85b8e8addb59e3ec6bbac51/Message/MessageTemp/2da3c57a4c28ad1405ace9fa8b1832a7/Image/4021615792173_.pic_hd.jpg)

tips: 这种方法不用重启gp

