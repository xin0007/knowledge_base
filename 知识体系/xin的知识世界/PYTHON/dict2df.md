[[PYTHON_TAG]]

```python
r1 = '{"Country":"Afghanistan","City":"`Aaqadari Yosufkhel","Latitude":33.051945,"Longitude":68.65,"Population":null}|||{"Country":"Afghanistan","City":"Aba","Latitude":32.669487,"Longitude":62.16116,"Population":null}|||{"Country":"Afghanistan","City":"Aba","Latitude":32.243587,"Longitude":64.684706,"Population":null}|||{"Country":"Afghanistan","City":"Abah Kah","Latitude":36.67363,"Longitude":69.078194,"Population":null}|||{"Country":"Afghanistan","City":"Abaka","Latitude":36.67363,"Longitude":69.078194,"Population":null}|||{"Country":"Afghanistan","City":"Abak","Latitude":34.234973,"Longitude":67.191593,"Population":null}|||{"Country":"Afghanistan","City":"Abak","Latitude":33.635262,"Longitude":64.740147,"Population":null}'
r2 = '{"Country":"Afghanistan","City":"`Aaqadari Yosufkhel","Latitude":33.051945,"Longitude":68.65,"Population":null}|||{"Country":"Afghanistan","City":"Aba","Latitude":32.669487,"Longitude":62.16116,"Population":null}|||{"Country":"Afghanistan","City":"Aba","Latitude":32.243587,"Longitude":64.684706,"Population":null}|||{"Country":"Afghanistan","City":"Abah Kah","Latitude":36.67363,"Longitude":69.078194,"Population":null}|||{"Country":"Afghanistan","City":"Abaka","Latitude":36.67363,"Longitude":69.078194,"Population":null}|||{"Country":"Afghanistan","City":"Abak","Latitude":34.234973,"Longitude":67.191593,"Population":null}|||{"Country":"Afghanistan","City":"Abak","Latitude":33.635262,"Longitude":64.740147,"Population":null}'

cc = {'r1':r1, 'r2':r2}
dd = pd.DataFrame.from_dict(cc,orient='index')
dd
```

|      |                                                 0 |
| ---: | ------------------------------------------------: |
|   r1 | {"Country":"Afghanistan","City":"`Aaqadari Yos... |
|   r2 | {"Country":"Afghanistan","City":"`Aaqadari Yos... |

解决方法：

```python
def cleaner(r1):
    result = pd.DataFrame(map(json.loads, r1.split('|||')))
    return result
  
pd.concat(list(dd[0].apply(lambda s: cleaner(s))))
```

| Country |        City |            Latitude | Longitude | Population |      |
| ------: | ----------: | ------------------: | --------: | ---------: | ---- |
|       0 | Afghanistan | `Aaqadari Yosufkhel | 33.051945 |  68.650000 | None |
|       1 | Afghanistan |                 Aba | 32.669487 |  62.161160 | None |
|       2 | Afghanistan |                 Aba | 32.243587 |  64.684706 | None |
|       3 | Afghanistan |            Abah Kah | 36.673630 |  69.078194 | None |
|       4 | Afghanistan |               Abaka | 36.673630 |  69.078194 | None |
|       5 | Afghanistan |                Abak | 34.234973 |  67.191593 | None |
|       6 | Afghanistan |                Abak | 33.635262 |  64.740147 | None |
|       0 | Afghanistan | `Aaqadari Yosufkhel | 33.051945 |  68.650000 | None |
|       1 | Afghanistan |                 Aba | 32.669487 |  62.161160 | None |
|       2 | Afghanistan |                 Aba | 32.243587 |  64.684706 | None |
|       3 | Afghanistan |            Abah Kah | 36.673630 |  69.078194 | None |
|       4 | Afghanistan |               Abaka | 36.673630 |  69.078194 | None |
|       5 | Afghanistan |                Abak | 34.234973 |  67.191593 | None |
|       6 | Afghanistan |                Abak | 33.635262 |  64.740147 | None |