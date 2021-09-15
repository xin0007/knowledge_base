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
	- 容器命令
	- 操作命令
	- 。。。


## Docker 镜像

## 容器数据卷

## DockerFile

## Docker网络原理

## IDEA整合Docker

## Docker Compose

## Docker Swarm


## CI/CD
