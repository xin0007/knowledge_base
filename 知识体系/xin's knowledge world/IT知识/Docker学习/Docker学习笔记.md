## Docker 概述
Docker 为什么会出现？

因为DevOps概念的生成，以后的趋势一定是Docker部署



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
> 在安装过程中，必须要加sudo，为了避免麻烦，进行授权
	> ```shell
	> 1.  建组
	> sudo groupadd docker
	> 2、 将docker的账户给与权限 sudo gpasswd -a <用户名> docker
	> sudo gpasswd -a lighthouse docker  
	> 3、 重启docker
	> sudo service docker restart
	> ```
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
docker pull mysql  #默认pull latest版本
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
> -c command 后面跟一个command：1. command要用双引号 “”框起  2. 绝对路径
^q^p^q 按照顺序按 （Mac系统）


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

> docker run -it -rm 测试用，用完就删


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

具名挂载、匿名挂载、指定路径挂载
```shell
1. 匿名挂载
docker run -d -P -v /etc/nginx --name nginx02 nginx

> -P 是随机指定挂载端口
> -v /etc/nginx 不指定宿主机挂载路径，不起名字，只指定容器内路径


docker volume ls

DRIVER    VOLUME NAME
local     d0b4e04128290f9c054092b713eadeb84baa34ef5190ad05e9070c20a49bfcf4
local     portainer_data


2. 具名挂载
docker run -d -P -v nginx_juming:/etc/nginx --name nginx03 nginx

> -v nginx_juming:/etc/nginx 给这个卷取名为nginx_juming

docker volume ls

DRIVER    VOLUME NAME
local     d0b4e04128290f9c054092b713eadeb84baa34ef5190ad05e9070c20a49bfcf4
local     nginx_juming
local     portainer_data


3. 对volume进行inspect

docker inspect volume_ID or volume_name

[
    {
        "CreatedAt": "2021-09-27T08:54:39+08:00",
        "Driver": "local",
        "Labels": null,
        "Mountpoint": "/var/lib/docker/volumes/d0b4e04128290f9c054092b713eadeb84baa34ef5190ad05e9070c20a49bfcf4/_data",
        "Name": "d0b4e04128290f9c054092b713eadeb84baa34ef5190ad05e9070c20a49bfcf4",
        "Options": null,
        "Scope": "local"
    }
]

> 能看到不指定具体地址，就默认为 /var/lib/docker/volumes/[volume_name]/_data_



docker inspect nginx_juming

[
    {
        "CreatedAt": "2021-09-27T08:58:38+08:00",
        "Driver": "local",
        "Labels": null,
        "Mountpoint": "/var/lib/docker/volumes/nginx_juming/_data",
        "Name": "nginx_juming",
        "Options": null,
        "Scope": "local"
    }
]

```

小结：
```shell
-v 容器内路径		  		# 匿名挂载
-v 卷名：容器内路径			  # 具名挂载 （推荐使用）		
-v /宿主机路径:/容器内路径 	# 指定路径挂载
```

拓展：
```shell
# 通过-v容器内路径，ro rw改变读写权限

ro readonly  这个是针对容器内的权限，ro的话只能通过宿主机进行操作，不能在容器内修改
rw readwrite

举例：
docker run -d -P -v nginx_juming:/etc/nginx:ro --name nginx03 nginx
docker run -d -P -v nginx_juming:/etc/nginx:rw --name nginx03 nginx

```


ro readonly  这个是针对容器内的权限，ro的话只能通过宿主机进行操作，不能在容器内修改
rw readwrite

#### DockerFile挂载容器卷
dockerfile 就是用来构建docker镜像的构建文件。 -- 命令参数脚本
通过这个脚本可以生成镜像，镜像是一层一层的，脚本的每个命令就是一层。

```shell
# 构建一个dockerfile的名字可以是随机的，但是建议dockerfile
# 脚本中的命令都要大写

FROM centos
VOLUME ["volume01", "volume02"]  # 匿名挂载两个数据卷
CMD echo "-----end-----"
CMD /bin/bash

```

将上述的dockerfile写好后，运行
```shell

docker build -f dockerfile-centos -t mingxin-centos:1.0 .

> -f dockerfile的路径
> -t tag 生成的镜像名：版本号
> . 生成路径

```

docker images 查看刚才生成的镜像
```shell
REPOSITORY               TAG       IMAGE ID       CREATED          SIZE
mingxin-centos           1.0       b0976a8bfb4e   59 minutes ago   231MB
```

运行
```shell
docker run -it imageID /bin/bash

发现里面存在两个文件夹volume01 volume02

docker inspect containerID
可以查看Mount的情况
```

#### 多个容器间的数据同步

```shell
1. 启动第一个docker
docker run -d --name docker01 mingxin-centos:1.0

2. 启动第二个docker，并volume-from第一个docker
docker run -d --name docker02 --volumes-from docker01 mingxin-centos:1.0

> 这样的话，第二个docker就会继承第一个docker的数据卷挂载
> 尝试数据的变化，发现文件是同步的，证明挂载成功

-- 后续的尝试
3. 启动第三docker，volume-from第一个docker
docker run -d --name docker03 --volumes-from docker01 mingxin-centos:1.0

这时候停止并删除第一个docker
docker stop docker01
docker rm docker01

再尝试docker02 和docker03的数据卷同步测试，发现仍然能够同步成功
```

```shell
查看docker container

docker inspect docker02


"Mounts": [
	{
		"Type": "volume",
		"Name": "e555c729cbef1c6a1a97ff6d5ead3cf2049efbc9128a88ebaff65ab1f0db8494",
		"Source": "/var/lib/docker/volumes/e555c729cbef1c6a1a97ff6d5ead3cf2049efbc9128a88ebaff65ab1f0db8494/_data",
		"Destination": "volumne02",
		"Driver": "local",
		"Mode": "",
		"RW": true,
		"Propagation": ""
	}
	
> 发现这种容器内的数据卷同步，仍然是会以host为中转，所以 --volume-from 这个继承的选项，会同步在本地的相同的路径

```
--volumes-from 的原理：
就是根据from的container的挂载记录，也给新的container挂载在同一个本地地址，所以即使删除from的container，新的container的挂载也不会受到影响。

数据卷的机制：
不是共享机制，是拷贝/备份的机制
容器之间配置信息的传递，数据卷容器的生命周期一直持续到没有容器使用为止。
但是一旦持久化到本地，这个时候，本地的数据是不会删除的

## DockerFile

构建步骤
1. 编写Dockerfile文件
2. docker build 构建成为一个镜像
3. docker run 运行镜像
4. docker push 发布 （DockerHub、阿里云镜像仓库）


Dockerfile一层一层的概念：
![[Pasted image 20211001163011.png]]

Dockerfile基础知识：
1. 每个保留关键字（指令）都必须是大写字母
2. 按照从上到下的顺序执行
3. "#" 表示注释
4. 每一个指令都会创建提交一个新的镜像层，并提交

常用命令：
```shell
FROM			# 基础镜像，一切从这里构建
MAINTAINER		# 镜像是谁写的
RUN				# 构建镜像的时候需要运行的命令
ADD				# 添加内容，e.g. 基础镜像+tomcat
WORKDIR			# 镜像的工作目录
VOLUME			# 挂载的目录
EXPOSE			# 暴露的端口配置
CMD				# 指定这个容器启动的时候要运行的命令，只有最后一个会生效，可被替代
ENTRYPOINT		# 指定这个容器启动的时候要运行的命令，可以追加命令
ONBUILD			# 当构建一个被继承DockerFile 这个时候就会运行 ONBUILD的命令，触发指令
COPY			# 类似ADD，将我们的文件拷贝到镜像中
ENV				# 构建的时候设置环境变量
```


创建一个自己的centos镜像
```shell
1. 编写docker file

FROM centos
MAINTAINER mingxin

ENV MYPATH /usr/local
WORKDIR $MYPATH

RUN yum -y install vim
RUN yum -y install net-tools

EXPOSE 80

CMD echo $MYPATH
CMD echo "-----end-----"
CMD /bin/bash
```
> CMD和RUN的区别：
> RUN是docker通过dockerfile在build image的时候执行的
> CMD是docker run镜像的时候，执行的

> CMD和ENTRYPOINT的区别：
> CMD运行的时候是可以被覆盖的，只有最后一个会被执行，写法：CMD echo "hell"; 或者  ["echo","hello"]也可以
> ENTRYPOINT是运行的时候一定会被执行的 ，写法： ENTRYPOINT ["echo","hello"]

```shell
2. 构建镜像
docker build -f / -t mycentos:1.0 .

结果:
Successfully built 2e9b3a80a16b
Successfully tagged mycentos:1.0
```

```shell
3. 运行镜像进行测试
docker run -it imagesID

结果:
> 进入的默认地址为 /usr/local
> vim与net-tools已经被安装好了
```

可以使用 docker history命令查看所有image的构建过程
```shell
docker history imageID

```

对比测试：
```shell
1. 两个简单的dockerfile

cmd-test:
FROM centos
MAINTAINER mingxin
CMD ["ls","-a"]  >注意要使用双引号！！


entrypoint-test:
FROM centos
MAINTAINER mingxin
ENTRYPOINT ["ls", "-a"]

2. build image
docker build -f filename -t targetName:tag .

3. 运行测试
docker run -it cmd-test:1.0 -l
结果：
docker: Error response from daemon: OCI runtime create failed: container_linux.go:380: starting container process caused: exec: "-l": executable file not found in $PATH: unknown.
ERRO[0000] error waiting for container: context canceled

docker run -it entrypoint-test:1.0 -l
结果：正常运行

```

e.g. 自己搭建Tomcat
1. 编写docker file 
> 起名Dockerfile，build会自动寻找这个文件，就不需要-f指定
```shell
FROM centos
MAINTAINER mingxin

COPY readme.txt /usr/local/readme.txt  # 把host文件复制到容器内

ADD jdk-8ull-linux-x64.tar.gz /usr/local/
ADD apache-tomcat-9.0.22.tar.gz /usr/local/  > ADD的作用就是可以直接解压缩文件

RUN yum -y install vim

ENV MYPATH /usr/local
WORKDIR #MYPATH

ENV JAVA_HOME /usr/local/jdk1.8.0_11
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV CATALINA_HOME /usr/local/apache-tomcat-9.0.22
ENV CATALINA_BASH /usr/local/apache-tomcat-9.0.22
ENV PATH $PATH:$JAVA_HOME/lib:$CATALINA_HOME/lib:$CATALINA_HOME/bin

EXPOSE 8080

CMD /usr/local/apache-tomcat-9.0.22/bin/startup.sh && tail -F /url/local/apache-tomcat-9.0.22/bin/catalina.out
```

2. build镜像
```shell
docker build -t diytomcat . # 因为起名为Dockerfile，所以不需要通过-f去寻找
```

3. 运行镜像
```shell
docker run -d -p 9090:8080 --name mingxintomcat -v /home/local/tomcat/test:/usr/local/apache-tomcat-9.0.22/webapps/test -v /home/local/tomcat/tomcatlogs/:/usr/local/apache-timcat-9.0.22/logs diytomcat
```

## Docker文件发布
```shell
更改image名字和tag
docker tag imageID imageName:tag
```

```shell
1. 登录dockerHub
docker login -u

2. push到docker hub
docker push imageName:tag
```

## Docker网络原理
将所有的container清理后，发现原始的ip addr状态如下：
![[Pasted image 20211004154538.png]]
docker0 是安装完docker就会产生的网卡

创建两个最简单的centos的container
然后ip addr 去获取网卡信息：
![[Pasted image 20211004225055.png]]
会发现除了本地回环地址，还生成了一个evth-pair网卡
在查看host的ip addr
![[Pasted image 20211004225724.png]]

> veth pair
> 1. veth-pair 是一对的虚拟设备接口
> 2. 成对出现
> 3. 常常充当着一个桥梁，连接着各种虚拟网络设备

##### Docker网络原理
1. 每启动一个docker容器，docker就会给docker容器分配一个ip，只要安装docker，就会有一个docker0的网卡，桥接模式，使用的是veth-pari技术
2. 每启动一个docker容器，都会多一对veth pari网卡：一段连着协议，一段彼此相连
![[Pasted image 20211004230317.png]]

```shell
docker exec -it centos01 ping 172.17.0.3

PING 172.17.0.3 (172.17.0.3) 56(84) bytes of data.
64 bytes from 172.17.0.3: icmp_seq=1 ttl=64 time=0.017 ms
64 bytes from 172.17.0.3: icmp_seq=2 ttl=64 time=0.034 ms
```
-- 可以ping通，反之亦然

3. 两个容器能够ping通的原理
![[Pasted image 20211004231151.png]]
结论：
1. 并不是container01与02直接进行连接，而是通过host的桥接和转发进行ping通的
2. container01和container02是用一个公用的路由器，docker0
所有的容器在不指定网络的情况下，都是用docker0路由的，docker会给我们的容器分配一个IP
2. docker中的所有网络接口都是虚拟的（虚拟的转发效率高）
3. 只要容器删除，对应的一对veth网卡就都没有了

-- Link 作用
```shell
1. 测试container之间直接ping container name
docker exec -it centos01 ping centos02
> ping: centos02: Name or service not known

2. 新建一个container 包含 --link功能
docker run -d --name centos03 --link centos02 centos /bin/bash -c "while true;do echo mingxin;sleep 1;done"

3. 再测试ping container name
docker exec -it centos03 ping centos02

> PING centos02 (172.17.0.3) 56(84) bytes of data.
> 64 bytes from centos02 (172.17.0.3): icmp_seq=1 ttl=64 time=0.075 ms
> 64 bytes from centos02 (172.17.0.3): icmp_seq=2 ttl=64 time=0.055 ms
```
发现加入--link 后就可以ping通，但是反之不可以ping通

```shell
docker network ls
可以查看所有的network
```


本质探究：--link 的作用 就是在hosts配置中增加了 "ip hostname"
```shell
docker exec -it centos03 cat /etc/hosts

> 
127.0.0.1	localhost
::1	localhost ip6-localhost ip6-loopback
fe00::0	ip6-localnet
ff00::0	ip6-mcastprefix
ff02::1	ip6-allnodes
ff02::2	ip6-allrouters
172.17.0.3	centos02 e1880ee23979
172.17.0.4	48ff0846bbf9
```
发现在hosts文件中多了一个centos02的hostname，这就是能ping通的原理

但是这个功能已经不建议使用 --link了，因为
1. 自定义网络不支持docker0
2. 双方ping通容器名需要双方在开始的时候都进行配置，单边的话只能ping通单边的container name

解决方案：
创建自定义网络
```shell
1. 创建一个桥接网络，并且子网掩码为192.168.0.0 网关为192.168.0.1，起名为mynet
docker network create --driver bridge --subnet 192.168.0.0/16 --gateway 192.168.0.1 mynet

2. 启动container时，使用mynet这个网络
docker run -d -P --name nettest01 --net mynet tomcat
docker run -d -P --name nettest02 --net mynet tomcat
```
这样的话，就nettest01可以直接ping nettest02 是可以ping通了
 
好处：
1. 不同集群，互相隔离。
2. 保证集群安全和健康

#### 网络连通
```shell
docker  network connect network_name containerID

```
增加之后，是会变成 一个容器，2个IP


