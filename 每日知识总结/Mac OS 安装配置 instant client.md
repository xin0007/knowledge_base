# [Mac OS 安装配置 instant client](https://my.oschina.net/u/3477333/blog/3067763)

https://my.oschina.net/u/3477333/blog/3067763

\1. 下载资源

  下载三个文件（oracle 官网）

- instantclient-basic-macos.x64-18.1.0.0.0.zip
- instantclient-sdk-macos.x64-18.1.0.0.0-2.zip
- instantclient-sqlplus-macos.x64-18.1.0.0.0.zip

\2. 创建目录 解压缩文件

```bash
sudo mkdir -p /opt/oracle
```

将下载文件复制到刚刚创建的目录下（/opt/oracle/）

```bash
sudo cp ****.zip /opt/oracle/
```

按上面顺序解压缩zip

```bash
unzip instantclient-basic-macos.x64-18.1.0.0.0.zip
unzip instantclient-sdk-macos.x64-18.1.0.0.0-2.zip
unzip instantclient-sqlplus-macos.x64-18.1.0.0.0.zip
```

解压第一个以后会产生一个 instantclient_18_1目录（版本不同产生的目录不同），然后解压 sdk和sqlplus 执行命令以后会自动解压到instantclient_18_1，里面会创建一个sdk和sqlplus的目录。

然后我们将sdk里的文件全部copy到instantclient_18_1目录

```bash
切换到instantclient_18_1目录里执行：
cp sdk/* .
```

3.配置环境变量

打开.base_profile或者.profile文件，我这里没有创建.base_profile所以直接在.profile文件里面配置，效果都是一样的。如果你想配置成全局的环境变量可以在/etc/profile文件里面配置，也没有问题。

```bash
vim ~/.profile
然后输入：
export ORACLE_HOME=/opt/oracle/instantclient_18_1/
export DYLD_LIBRARY_PATH=$ORACLE_HOME
export LD_LIBRARY_PATH=$ORACLE_HOME
PATH=$PATH:$ORACLE_HOME

:wq //保存退出

打开一个新的终端执行：
source ~/.profile //命令生效

然后执行：
sqlplus

SQL*Plus: Release 18.0.0.0.0 Production on Sun Jun 30 16:02:48 2019
Version 18.1.0.0.0

Copyright (c) 1982, 2018, Oracle.  All rights reserved.

Enter user-name: user/password@host/数据库名称

键入你的连接信息就可以连接到远程oracle服务了。
例：test/test@123@192.168.1.100/orcl
```

到此Mac安装配置instant client 完毕。下篇文章我们接受python利用cx_Oracle 连接和操作远程oracle数据库服务