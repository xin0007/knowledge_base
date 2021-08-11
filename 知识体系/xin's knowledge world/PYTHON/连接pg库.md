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



