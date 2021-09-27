## Docker 概述
Docker 为什么会出现？




## Docker 安装

根据官网的指示在Linux上进行安装：
1. 删除旧版本
```shell
yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```
> 在安装过程中，必须要加sudo，为了避免麻烦，进行授权
	> ```shell
	> 1.  建组
	> sudo groupadd docker
	> 2、 将docker的账户给与权限 sudo gpasswd -a <用户名> docker
	> sudo gpasswd -a lighthouse docker  
	> 3、 重启docker
	> sudo service docker restart
	> ```

2. 配置仓库
```shell
yum install -y yum-util  ## 安装管理Repository及扩展包的工具


yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo ## 官网默认的仓库，但是在国外
	http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo ## 阿里云的镜像仓库
```

3. 安装Docker引擎
```
yum install docker-ce docker-ce-cli containerd.io

## docker-ce是community版本，如果是企业版本就是 -ee
```

4. 测试Docker安装是否成功
```shell
docker version
```

5.  启动Docker
```
systemctl start docker
```

6. 测试Docker 用 hello-world
```
docker run hello-world
```


> **Docker run的流程**
> ```shell
> docker run hello world
> ```
> ![[Pasted image 20210914110851.png]]


**Docker是怎么工作的**
Docker是一个client - Server结构的系统，Docker的守护进程运行在主机上。通过Socket从客户端访问！
DockereServer接收到Docker-Client的指令，就会执行这个命令


## Docker 命令
 **帮助命令**
```shell
docker version #显示docker的版本信息
docker info # 显示docker的系统信息，包括镜像和容器的数量
docker 命令 --help #万能命令
```



	- 镜像命令
	
```shell

1. 查询 docker search

docker search mysql  #与从Docker Hub中搜索相同

#加条件 -f
docker search mysql -f stars=3000  #star数量超过3000的mysql镜像

2. 下载 docker pull
docker pull mysql  #默认pull latestbanben
docker pull mysql:5.7  #下载5.7版本的mysql镜像
```

![[Pasted image 20210918145714.png]]

```shell
3. 删除 docker rmi -f  > -f: force

docker images 查询image ID

docker rmi -f DockerID

> 骚操作，删除所有的docker images
docker rmi -f ${docker images -aq}  > -a:全部； -q:quite 只显示ID
									> -$(): shell里表示变量

```

	- 容器命令
说明：我们有了镜像才可以创建容器，linux
```shell
1. 查看正在运行的container docker ps 
docker ps 

2. 查看所有的container，包括历史数据 docker ps -a
docker ps -a

3. 容器基本操作
docker start containerID		#启动
docker restart containerID		#重启
docker stop containerID			#停止
docker kill containerID			#强制重启

4. 删除容器
docker rm containerID 			#但是不能删除正在运行的
docker rm -f $(docker ps -aq)	#删除所有的容器

> 另一种写法
docker ps -aq|xargs docker rm


5. 后台运行
docker run -d centos #后台运行centos
> 但是有个问题 就是docker ps 查看，会发现没有
> 原因：docker如果后台运行/守护进程，必须要有前台进程，否则docker会认为已经结束，而自动停止

> 骚操作：
> 启动centos并执行脚本
docker run -d centos /bin/sh -c "while true;do echo mingxinli;sleep 1;done"

> run -it 后 保持运行退出
^q^p^q 按照顺序按


6. 查看日志
docker logs -tf 
docker logs -tf --tail 10 containerID   
> -t: timestamp -f:log format


7. 查看容器中的进程数据
docker top containerID


8. 查看容器中的元数据
docker inspect containerID


9. 进入正在运行的container
方法一：docker exec -it containerID /bin/bash

方法二：docker attach containerID

两者的区别：
docker exec 进入到容器中后，开启一个新的终端，可以在终端中操作（常用）
docker attach 进入到容器正在执行的终端，不会启动新的进程


10. 将容器内的文件拷贝出来
docker cp
docker cp containseID:/home/file /home/current
> 这个运不运行都是可以的，只要container在，数据就可以拷贝出来
> 以后可以用 -v 挂载命令 会自动同步
```


docker 安装 Nginx
```shell
1. search Nginx
docker search 

2. docker pull ngxin

3. 运行
docker run -d --name nginx01 -p 3344:80 nginx

> 这样运行的话 要把 ngxin或者imageID放在最后面 否则会报错

--name 给启动的container起一个名字
-p 暴露端口 宿主机端口:容器内部端口
```

端口暴露原理
![[Pasted image 20210921102212.png]]

docker 安装 tomcat
```shell
1. 直接run，找不到会自动下载
docker run -d -p 3355:8080 --name tomcat01 tomcat(:latest)

> docker un -it -rm 测试用，用完就删


```

docker 安装Elastic Search
```shell
1.安装elastic search
docker run -d --name elasticsearch02 -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e ES_JAVA_OPTS="-Xms64m -Xmx512m" elasticsearch:7.6.2

> -e 环境配置
> es非常占用内存，一旦启动起来容易卡死，所以需要配置环境变量 
-e ES_JAVA_OPTS="-Xms64m -Xmx512m" : 就是占用内存只能在 64M - 512M之间

2. 查看内存占用
docker stats containerID

CONTAINER ID   NAME              CPU %     MEM USAGE / LIMIT   MEM %     NET I/O     BLOCK I/O    PIDS
cab7a6f644b9   elasticsearch02   0.01%     266MiB / 3.649GiB   7.12%     766B / 0B   0B / 246kB   21

```
	

可视化 - portainer工具
```shell
工具运行：
docker run -d -p 8000:8000 -p 9000:9000 --name=portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer-ce

```
![[Pasted image 20210922082103.png]]
是一个关于docker容器的可视化工具

## Docker 镜像
1. UFS Union file system 联合文件系统 
这是一种分层、轻量级并且高性能的文件系统。 -- 它支持对文件系统的修改作为一次提交来一层层的叠加。
分层：每个文件都是一层
轻量级：只保留基本的功能，能用就行
高性能：因为其轻量级，所以加载快；因为其分层，所以可以多线程？

UnionFS是Docker镜像的基础。镜像可以通过分层来进行继承，基于基础镜像，可以制作各种具体的应用镜像。


自己制作镜像  -- docker commit
```shell
docker commit -a="author name"  -m="message" containerID name:tag

例如：
docker commit -a="lmxdashuaige" -m="added webapps" f2188fbce046 tomcat01:2.0

docker images 查看：

REPOSITORY               TAG       IMAGE ID       CREATED         SIZE
tomcat01                 2.0       9a2d8402dcff   3 seconds ago   684MB  -- 新建的
tomcat                   latest    bb832de23021   11 days ago     680MB  -- 原有的
```
![[Pasted image 20210926102026.png]]

## 容器数据卷
数据卷理念：
容器内的数据同步到本地环境
目录的挂载，将我们容器内的目录，挂载到Linux上
总结：容器的持久化和同步操作！容器间也可以实现数据共享

```shell
docker run -it -v host地址：容器地址


e.g.
1. docker run -it -v /home/ceshi:/home centos -> 把host的home/ceshi挂载到容器内centos中的 /home下

2. docker inspect 一下
docker inspect containerID
```
![[Pasted image 20210926155233.png]]

docker 安装 MySQL
```shell
docker run -d -p 3310:3306 -v /home/mysql/conf:/etc/mysql/conf.d -v /home/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 --name mysql01 mysql:5.7
```



## DockerFile

## Docker网络原理

## IDEA整合Docker

## Docker Compose

## Docker Swarm


## CI/CD
