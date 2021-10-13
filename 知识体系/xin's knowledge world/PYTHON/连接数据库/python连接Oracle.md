https://blog.csdn.net/zhu940923/article/details/81172968

```python
import cx_Oracle
import pandas as pd
#连接数据库，下面括号里内容根据自己实际情况填写

## 以上海速创为例
conn = cx_Oracle.connect('ITSSC_YUNWEI/itsscYUN_wei852@db-shsc.fsg.inner:1521/ehr2')

sql = '''
select * from all_tab_columns where table_name='FS_ACCFUND_PAYABLE'
'''

cursor = conn.cursor()

result=cursor.execute(sql)

all_data=cursor.fetchall()

pd.DataFrame(all_data)
```
这会有个问题：pandas读取后 没有column name

### pandas读取
```python
#from sqlalchemy import create_engine
import pandas as pd
import cx_Oracle
import os


os.environ['NLS_LANG'] = 'SIMPLIFIED CHINESE_CHINA.UTF8'
db=cx_Oracle.connect('ITSSC_YUNWEI','itsscYUN_wei852','db-shsc.fsg.inner:1521/ehr2')

#查询获取数据用sql语句
sql = '''
select * from all_tab_columns where table_name='FS_ACCFUND_PAYABLE'
'''
df = pd.read_sql_query(sql, db)
```

