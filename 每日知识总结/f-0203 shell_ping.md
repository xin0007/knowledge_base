shell脚本循环ping ip地址

```shell
#! /bin/bash
for i in "172.16.145.16" "172.16.145.17" "172.16.145.18" "172.16.145.19" 
do
ping=`ping -c 1 $i|grep loss|awk '{print $6}'|awk -F "%" '{print $1}'`
if [ $ping -eq 100  ];then
echo ping $i fail
else
echo ping $i ok
fi
done
```

