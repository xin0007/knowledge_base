[[IT_TAG]] #IT 

transformation：

1. Trans1

![[Pasted image 20210811085849.png]]
在最后，要添加 **Copy rows to result**，这样在job中trans才会有数据流出



2. Trans2

![[Pasted image 20210811085859.png]]

设置parameter，在job中去承接数据



Job:

![[Pasted image 20210811085908.png]]

transformation1不做处理

Transformation2：

- 勾选execute every input row

![[Pasted image 20210811085918.png]]

- 勾选 copy results to parameters

- 勾选 pass parameter values to sub transformation

- 并且配置对应的parameter映射，

  第一个parameter是 “Get Parameters” 根据transformation2得到的

  第二个Parameter to use 是trans1 copy 的results的字段名

![[Pasted image 20210811085927.png]]




