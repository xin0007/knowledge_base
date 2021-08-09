Dataframe

针对其中一个字段进行统计

```python
df['tag'].value_counts()
```



对统计结果的操作

```python
count = left_df['username'].value_counts()

count[count.values == 2].index 
```





list

```python
result_train = pd.value_counts(y_train)

## y_train 是一个list
```

