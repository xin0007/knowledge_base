[[DB_TAG]] #db 

dblink:
1. 简介
dblink(Database Link)数据库链接顾名思义就是数据库的链接，就像电话线一样，是一个通道，当我们要跨本地数据库，访问另外一个数据库表中的数据时，本地数据库中就必须要创建远程数据库的dblink,通过dblink本地数据库可以像访问本地数据库一样访问远程数据库表中的数据。

2. 例子
from SFSC_SSC.FS_ENTRANT_INFO@qgerp