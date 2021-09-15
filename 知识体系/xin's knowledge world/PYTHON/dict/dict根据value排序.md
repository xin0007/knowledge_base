```python
after = dict(sorted(before.items(), key=lambda e: e[1], reverse = True))
```

sorted功能排序
- 排的是before.items()， 这一步讲dict变为tuple，目的是为了将key和value打包成一个整体进行排序
- key是 e[1]， 就是根据这个 tuple的[1]，即value, e[0]的话就是根据key进行排序
- reverse  = True 倒叙
- 然后再将这个dict_items 转化回 dict