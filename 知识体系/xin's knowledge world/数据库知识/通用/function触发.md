[[DB_TAG]] #db 

execute as 就是sql语句触发器问题
execute as 是用来定义模块的执行上下文

execute as {CALLER|SELF|OWNER|'user_name'}

CALLER: 模块调用方
SELF: 创建或更改模块的用户
OWNER: 模块的当前所有者
'user_name': 指定的用户