[[PYTHON_TAG]] #PYTHON 

python连PG sql

```python
import psycopg2
# database，user，password，host，port分别对应要连接的PostgreSQL数据库的数据库名、数据库用户名、用户密码、主机、端口信息，请根据具体情况自行修改
conn = psycopg2.connect(database="operation",user="gpadmin",
	password="gpadmin",host="172.16.111.31",port="5432")
cur = conn.cursor()

#查询指令
query = """
select name, email, department
FROM itssc_operation.sat_users_redmine
"""

# 执行
cur.execute(query)

# 读取数据
rows = cur.fetchall()

import pandas as pd
email_pd = pd.DataFrame(rows)

# 关闭数据库连接
conn.close()
```


使用 sqlalchemy
```python
from sqlalchemy import create_engine
SQLALCHEMY_DATABASE_URI = 'postgresql://itssc_operation:@172.16.149.11:5432/operation'
engine = create_engine(SQLALCHEMY_DATABASE_URI)

df = pd.read_sql('''select * from  ods_251_ticket.t_hr_datamodify_apply 
                 where apply_date >= '2021-05-01'
                 ''', engine)

```

df写入pg库
```python

# create connection
SQLALCHEMY_DATABASE_URI = 'postgresql://itssc_operation:abc123@172.16.149.11:5432/exploration'
engine = create_engine(SQLALCHEMY_DATABASE_URI)
	
# 写入pg库
df.to_sql(schema='dnspod', con=engine,name = 'dnspod_record', if_exists='replace', index=False)

```


## 保持长链接
用sqlalchemy

```python

engine = create_engine(SQLALCHEMY_DATABASE_URI,pool_size=10,pool_pre_ping=True)

```

> pool_pre_ping=True 
> 表示每次连接从池中检查，如果有错误，监测为断开的状态，连接将被立即回收
> pool_size = 10 连接池数量，一般5-10个就可以，如果5-10个都不够用，那可能需要思考一下脚本的实现是否存在问题


## 执行DDL
使用sqlalchemy，上下文
```python

# create connection
SQLALCHEMY_DATABASE_URI = 'postgresql://itssc_operation:abc123@172.16.149.11:5432/exploration'
engine = create_engine(SQLALCHEMY_DATABASE_URI,pool_size=10,pool_pre_ping=True)


with engine.connect() as conn:
	conn.execute('TRUNCATE dnspod.dnspod_record')
```
