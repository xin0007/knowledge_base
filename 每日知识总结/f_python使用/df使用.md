1. df去重
```python
first_df = false_df.drop_duplicates(keep='first',inplace=False, subset=['username'])
# keep 保留原则 {'first', 'last', False}
#Determines which duplicates (if any) to keep.
#    - ``first`` : Drop duplicates except for the first occurrence.
#    - ``last`` : Drop duplicates except for the last occurrence.
#    - False : Drop all duplicates.

# subset 以哪个字段进行去重
```



2. group by 并统计

```python
first_all_df.groupby(['min']).size().sort_values(ascending = False)
```

根据其中'min'这一列进行groupby，然后用.size()统计数量，用.sort_values进行排序



3. df列名修改

```python
df.columns = ['username', 'total_num']
```



4. df间进行merge

```PYTHON
fi = pd.merge(left_df, right_df, how = 'left', on ='username' / left_on = '', right_on = '')
```

