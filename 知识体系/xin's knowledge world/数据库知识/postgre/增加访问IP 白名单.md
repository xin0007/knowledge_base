[[DB_TAG]] #db 

greenplum 连接报错!
![[Pasted image 20210811104820.png]]

可以ping通，但是无法登陆



解决方法：

1. 
vi /data/master/gpseg-1/pg_hba.conf

![[Pasted image 20210811104832.png]]

添加下方

![[Pasted image 20210811104839.png]]



tips: 

IP/32 表示您的IP地址子网掩码是32位的子网，也就是255.255.255.255。



2. 更改后：

   用gpadmin用户 执行 gpstop -u 刷新配置

![[Pasted image 20210811104855.png]]
tips: 这种方法不用重启gp


