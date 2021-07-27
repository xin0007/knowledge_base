1. 保存成pickle

```python
# 保存df
import pickle

## result to pickle

if __name__ == '__main__':
    path = '/Users/mingxinli/Desktop/上海外服/任务/元数据梳理/数据分析/计算结果/result/column_names_df.pickle'
    f = open(path, 'wb')
    pickle.dump(df, f)
    f.close()
```



2. 从pickle中加载数据

```python
# load pickle data
path = '/Users/mingxinli/Desktop/上海外服/任务/元数据梳理/数据分析/计算结果/result/column_names_df.pickle'
f1 = open(path, 'rb')
df = pickle.load(f1)
```

