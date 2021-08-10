数据的分层取样 stratified

```python
from sklearn.model_selection import train_test_split

X_train, X_test, y_train, y_test = train_test_split(dataset_fi, lable_fi,
                                                    stratify=lable_fi, 
                                                    test_size=0.2, random_state = 222)
```

