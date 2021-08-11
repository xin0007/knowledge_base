[[PYTHON_TAG]] #PYTHON 

```python
nx, ny = 200, 100
x_min, x_max = plt.xlim()
y_min, y_max = plt.ylim()
x_grid, y_grid= np.meshgrid(np.linspace(x_min, x_max, nx), np.linspace(y_min, y_max, ny))
```

这里的x_grid，形状是[200, 100]

len(x_grid) = 100

len(x_grid[0]) = 200

他会分成200份，然后每一个y有一行数据，所以一共有100行

