[[IT_TAG]] #IT 

Carte 服务器上的PDI的使用：

- 停止服务：
使用网页
http://hadoop101:8080/kettle/stopCarte

- 开启服务：
找到之前的历史
history | grep carte ： 能找到跟carte相关的历史操作

找到 source /usr/local/greenplum-db-clients/greenplum_clients_path.sh && ( ./carte.sh carte-config-master.xml > logs/carte.log 2>&1 &)

> 这里要加source client，目的是为了greenplum loader的使用，因为没有配置环境变量

(carte.sh和carte-config-master.xml在/app/data-integration/里面) -- find / -name carte.sh
即可开启



Greenplum:

时间问题：
用 >=; <= 的话，如果<= 一天，是不包含这一天的数据