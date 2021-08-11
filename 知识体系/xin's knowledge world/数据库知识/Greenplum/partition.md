[[DB_TAG]] #db 

自动partition的方法

create table partition_test (
id int, 
date date,
amt int)
DISTRIBUTED BY (id)

只需要distributed by 就行，但是 （id） 的要求是long型数据