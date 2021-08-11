[[PYTHON_TAG]] #PYTHON 

Translates slice objects to concatenation along the second axis.


```python
np.array([1,2,3])
>> array([1, 2, 3])

np.array([4,5,6])
>> array([4, 5, 6])

np.c_[np.array([1,2,3]), np.array([4,5,6])]
>> array([[1, 4],
       [2, 5],
       [3, 6]])
```

* TIPS: 必须用 [ ]