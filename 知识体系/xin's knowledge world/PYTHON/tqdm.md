[[PYTHON_TAG]] #PYTHON 

1. pandas使用tqdm

```python
from tqdm import tqdm
tqdm.pandas()

df_tables['words'] = df_tables['table_id'].progress_apply(lambda x: parse.column_features(x))
```


2. list使用tqdm

```python
for i in tqdm(range(len(rela_fi))):
    i += 1
```


