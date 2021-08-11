[[DB_TAG]] #db 

- 登录
psql -U username -d database_name -h host -W
参数含义: -U指定用户 -d要连接的数据库 -h要连接的主机 -W提示输入密码。
例子：psql -U gpadmin -d testDB -h 172.16.111.31


- 设置访问权限
GRANT ALL ON SCHEMA demo TO demo_role; --赋给用户所有权限

例子：GRANT ALL ON SCHEMA "itssc" TO itssc_operation;
or: GRANT ALL ON "itssc"."ods_talefull_attendance" TO itssc_operation;

