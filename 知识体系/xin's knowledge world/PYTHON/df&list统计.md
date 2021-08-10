[[PYTHON_TAG]] #PYTHON 

1. 针对多列统计
```python
# 创建新的df
new_df= pd.DataFrame(cleaned_df, columns=['Date', 'IP','Port'])

# 统计
aaa = new_df.groupby(['Date','IP', 'Port']).size()
aaa

# 结果
Date        IP               Port                                     
2021-07-06  192.168.100.246  /Viewer/Count/FS_ASSIGN                      594
                             /Viewer/Count/FS_CLIENT                        1
                             /Viewer/Count/FS_DEPTS                         1
                             /Viewer/Count/FS_HUMBAS                        2
                             /Viewer/Count/FS_SALE_GROUP_ALL                1
                                                                         ... 
2021-08-06  192.168.100.17   /favicon.ico                                   1
                             /fonts/glyphicons-halflings-regular.woff       1
                             /fonts/glyphicons-halflings-regular.woff2      1
                             /prepare.js                                    1
            192.168.9.222    /Viewer/Fetch/FS_FINC_RCV                     50
Length: 1126, dtype: int64

```



对结果重新变为df
```python
b = pd.DataFrame(aaa)
b.reset_index(inplace=True)
b
```

结果：
![[Pasted image 20210809105227.png]]


2. 针对其中一个字段进行统计

```python
df['tag'].value_counts()
```

3. 对统计结果的操作

```python
count = left_df['username'].value_counts()

count[count.values == 2].index 
```

4. list统计

```python
result_train = pd.value_counts(y_train)

## y_train 是一个list
```