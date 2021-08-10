```python
import pandas as pd
import numpy as np
import re
import warnings
warnings.filterwarnings('ignore')


# 读取文件
raw_data = open('/Users/mingxinli/Desktop/上海外服/任务/紧急事件-Nginx日志数据薪酬查询记录/第二次/wechat0713.log', 'r').read()

# split数据集
data=raw_data.split('\n')
## 去掉最后一个空集
data = data[:-1]


# 自定义一个function来做数据的parse
def cleaning(row):
    one_row={}
    one_row['IP'] = re.findall(r'\b(?:\d{1,3}\.){3}\d{1,3}\b', row)[0].replace(']','').replace('[','')
    one_row['Time'] = re.findall(r'\[.*?\]', row)[0].replace(']','').replace('[','')
    one_row['Request'] = re.findall(r'\".*?\"', row)[0].replace('"','')
    one_row['URL Source'] = re.findall(r'\".*?\"', row)[1].replace('"','')
    one_row['web'] = re.findall(r'\".*?\"', row)[2].replace('"','')
    one_row['User Info'] = re.findall(r'\".*?\"', row)[3].replace('"','')
    one_row['Response'] = re.findall(r'(?:[0-9]{1,3}\s[0-9]{1,3})', row)[0].split(' ')[0]
    one_row['Size'] = re.findall(r'(?:[0-9]{1,3}\s[0-9]{1,3})', row)[0].split(' ')[1]
    
    return one_row
   
    
# 对split的数据集parse
cleaned = list(map(cleaning, data))

# 转换成df格式
cleaned_df = pd.DataFrame(cleaned)
```

