检测响应状态码
```python
r = requests.get('http://httpbin.org')
r.status_code
> 200

```

Requests还附带的内置状态码查询对象
```python
r.status_code == requests.codes.ok

```