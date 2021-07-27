https://blog.csdn.net/wxc20062006/article/details/111256533

一次greenplum死锁问题排查
1.从 pg_stat_activity 视图中查找处于等锁状态的任务:

select * from pg_stat_activity where waiting_reason='lock';
dangdang=#                   select * from pg_stat_activity where waiting_reason='lock';
 datid | datname  | procpid | sess_id | usesysid | usename |                current_query                 | waiting |         query_start          |        backend_start         | client_addr | c
lient_port | application_name |          xact_start          | waiting_reason 
-------+----------+---------+---------+----------+---------+----------------------------------------------+---------+------------------------------+------------------------------+-------------+--
-----------+------------------+------------------------------+----------------
 17154 | dangdang |  501586 |  159440 |       10 | gpadmin | truncate table fi_temp.fi_hive_check_orders; | t       | 2020-12-16 11:52:54.52002+08 | 2020-12-16 11:47:18.96088+08 |             |  
        -1 | psql             | 2020-12-16 11:52:54.52002+08 | lock
(1 row)

通过上面语句，可以找到那张表相关的任务正在处于锁定状态；
具体的语句为truncate table fi_temp.fi_hive_check_orders，也就是说当前这个SQL导致了死锁；


2.关联pg_locks,pg_class,pg_stat_activity 表，查询与上述任务相关的锁，结果如下：
dangdang=# select a.locktype,a.pid,a.gp_segment_id,b.relname,substring(c.current_query,1,100),
dangdang-# c.xact_start,a.pid,a.mode,a.granted from pg_locks a,pg_class b,pg_stat_activity c 
dangdang-# where a.relation = b.oid and a.pid = c.procpid and b.relname like 'fi_hive_check_orders%';

 locktype |  pid   | gp_segment_id |       relname        |                  substring                   |          xact_start          |  pid   |        mode         | granted 
----------+--------+---------------+----------------------+----------------------------------------------+------------------------------+--------+---------------------+---------
 relation | 468474 |            -1 | fi_hive_check_orders | <IDLE> in transaction                        | 2020-12-16 09:58:52.4963+08  | 468474 | AccessShareLock     | t
 relation | 501586 |            -1 | fi_hive_check_orders | truncate table fi_temp.fi_hive_check_orders; | 2020-12-16 11:52:54.52002+08 | 501586 | AccessExclusiveLock | f
(2 rows)


3.第2步也可以通过如下SQL查询得到具体的锁信息.
select * from gp_toolkit.gp_locks_on_relation where lorrelname like 'fi_hive_check_orders%';
dangdang=# select * from gp_toolkit.gp_locks_on_relation where lorrelname like 'fi_hive_check_orders%';
1
 lorlocktype | lordatabase |      lorrelname      | lorrelation | lortransaction | lorpid |       lormode       | lorgranted |               lorcurrentquery                
-------------+-------------+----------------------+-------------+----------------+--------+---------------------+------------+----------------------------------------------
 relation    |       17154 | fi_hive_check_orders |    42627212 |       44046626 | 468474 | AccessShareLock     | t          | <IDLE> in transaction
 relation    |       17154 | fi_hive_check_orders |    42627212 |       44054263 | 501586 | AccessExclusiveLock | f          | truncate table fi_temp.fi_hive_check_orders;
从而获取到那些表之间那些锁发生了冲突，导致了死锁。
也可以通过搜索进程号，来判断死锁情况。

通过如下这些查询，发现fi_hive_check_orders这张表，一共有2个事务持有，pid分别是468474,501586，而主要mode分别为AccessShareLock和AccessExclusiveLock；
而目前AccessShareLock对应的pid可以处理"in transaction"状态中，而当前这张表属于临时表，可以把数据清除，或者不要，于是
于是通过select pg_cancel_backend(468474);

4. 最后还可以通过
select usename,procpid,client_addr,query_start,current_query,waiting_reason,client_port
from pg_stat_activity where procpid in (468474,501586);
dangdang=# select usename,procpid,client_addr,query_start,current_query,waiting_reason,client_port
dangdang-# from pg_stat_activity where procpid in (468474,501586);
 usename | procpid | client_addr |          query_start          |                current_query                 | waiting_reason | client_port 
---------+---------+-------------+-------------------------------+----------------------------------------------+----------------+-------------
 fi_user |  468474 | 10.4.XXX.XXX | 2020-12-16 09:58:54.970918+08 | <IDLE> in transaction                        |                |       60863
 gpadmin |  501586 |             | 2020-12-16 11:52:54.52002+08  | truncate table fi_temp.fi_hive_check_orders; | lock           |          -1
通过这个SQL语句，能查询到transaction是哪个IP接入的，其中client_port=-1表示是从master节点连接的。
