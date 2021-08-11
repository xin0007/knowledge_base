transformation：

1. Trans1

![Screen Shot 2021-03-24 at 3.14.19 pm](/Users/mingxinli/Library/Application Support/typora-user-images/Screen Shot 2021-03-24 at 3.14.19 pm.png)在最后，要添加 **Copy rows to result**，这样在job中trans才会有数据流出



2. Trans2

![Screen Shot 2021-03-24 at 3.15.28 pm](/Users/mingxinli/Library/Application Support/typora-user-images/Screen Shot 2021-03-24 at 3.15.28 pm.png)

设置parameter，在job中去承接数据



Job:

![Screen Shot 2021-03-24 at 3.16.28 pm](/Users/mingxinli/Library/Application Support/typora-user-images/Screen Shot 2021-03-24 at 3.16.28 pm.png)

transformation1不做处理

Transformation2：

- 勾选execute every input row

![Screen Shot 2021-03-24 at 3.17.07 pm](/Users/mingxinli/Library/Application Support/typora-user-images/Screen Shot 2021-03-24 at 3.17.07 pm.png)

- 勾选 copy results to parameters

- 勾选 pass parameter values to sub transformation

- 并且配置对应的parameter映射，

  第一个parameter是 “Get Parameters” 根据transformation2得到的

  第二个Parameter to use 是trans1 copy 的results的字段名

![Screen Shot 2021-03-24 at 3.17.46 pm](/Users/mingxinli/Library/Application Support/typora-user-images/Screen Shot 2021-03-24 at 3.17.46 pm.png)



