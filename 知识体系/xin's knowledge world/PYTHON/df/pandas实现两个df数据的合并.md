[[PYTHON_TAG]] #PYTHON 

pandas 实现两个 df 数据的合并：

按行上下拼接：
```python
pd.concat([df1,df2],axis=0)
```

按列左右拼接：
```python
pd.concat([df1,df2],axis=1)
```
