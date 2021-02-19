1. 

select into:

表格本身不需要存在，select会自动插入

insert into：

要求表格要实现存在



2. timezone

postgres=# show time zone;   

TimeZone    

---------------

Asia/Shanghai (**1** 行记录)



postgres=# set time zone "Asia/Shanghai"; 

SET











