使用sqlalchemy

```python
import re
import os 
import pandas as pd
from sqlalchemy import create_engine

SQLALCHEMY_DATABASE_URI = 'postgresql://itssc_operation:@172.16.149.11:5432/operation'
engine = create_engine(SQLALCHEMY_DATABASE_URI)

df = pd.read_sql('''select * from  ods_251_ticket.t_hr_datamodify_apply 
                 where apply_date >= '2021-05-01'
                 ''', engine)


```