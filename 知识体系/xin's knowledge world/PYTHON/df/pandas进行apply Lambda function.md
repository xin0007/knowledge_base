```python

def minute(time):
    return time[12:17]

def date(time):
    return time[:2]

def hour(time):
    return time[12:14]


cleaned_df['Time_date'] = cleaned_df['Time'].apply(lambda s:date(s))
cleaned_df['Time_hour'] = cleaned_df['Time'].apply(lambda s:hour(s))
cleaned_df['Time_minute'] = cleaned_df['Time'].apply(lambda s:minute(s))

```

对一个dataframe一个column进行function的运算
df['column'].apply(lambda s:function(s))

对一个dataframe整体进行function的运算
df.apply(lambda s:function(s), axis=1)
