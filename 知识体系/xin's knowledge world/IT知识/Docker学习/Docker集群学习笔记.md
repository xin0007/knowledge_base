## Docker Compose
#### 简介

自己理解：Docker的部署，如果有很多的微服务，每个单独写Dockerfile，然后build再run的话，会非常的复杂和麻烦，所以Docker compose 相当于一个一键启动所有容器的yaml文件。

官方文档：
https://docs.docker.com/compose/

Compose is a tool for defining and running multi-container Docker applications. With Compose, you use a YAML file to configure your application’s services. Then, with a single command, you create and start all the services from your configuration. 

1. 定义和运行多容器的Docker应用
2. 使用YMAL文件来配置
3. 使用single command，就可以创建和启动所有的服务

Using Compose is basically a three-step process:
1.  Define your app’s environment with a `Dockerfile` so it can be reproduced anywhere.
2.  Define the services that make up your app in `docker-compose.yml` so they can be run together in an isolated environment.
3.  Run `docker compose up` and the [Docker compose command](https://docs.docker.com/compose/cli-command/) starts and runs your entire app. You can alternatively run `docker-compose up` using the docker-compose binary.
<br>
一个例子:
```yaml
version: "3.9"  # optional since v1.27.0
services:
  web:   ## 这是第一个web服务
    build: .
    ports:
      - "5000:5000"
    volumes:
      - .:/code
      - logvolume01:/var/log
    links:   ## 服务间的链接
      - redis  
  redis:    ## 这是第二个 redis服务
    image: redis
volumes:
  logvolume01: {}  ## 容器挂载
```

Compose的两个术语
1. service  每个容器就是一个服务/应用 例如：web、redis, mysql
2. project 在一个docker compose组成的服务群（一组关联的container），就是一个project 例如：博客（web+mysql）

#### 安装
1. 下载
```shell
a. 官方网址
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

-- 但是下载会非常慢

b. 国内网址
sudo curl -L "https://get.daocloud.io/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
```

2. 赋权
```shell
sudo chmod +x /usr/local/bin/docker-compose
```

3. 测试
```shell
docker-compose version
```
![[Pasted image 20211017225542.png]]
安装成功！

#### Docker compose官网例子
https://docs.docker.com/compose/gettingstarted/
1. 创建文件夹
```shell
mkdir composetest
cd composetest
```

2. 创建app.py
```python
import time

import redis
from flask import Flask

app = Flask(__name__)
cache = redis.Redis(host='redis', port=6379)

def get_hit_count():
    retries = 5
    while True:
        try:
            return cache.incr('hits')
        except redis.exceptions.ConnectionError as exc:
            if retries == 0:
                raise exc
            retries -= 1
            time.sleep(0.5)

@app.route('/')
def hello():
    count = get_hit_count()
    return 'Hello World! I have been seen {} times.\n'.format(count)
```

3. 创建requirements.txt，以后用来pip install
```txt
flask
redis
```

4. 创建Dockerfile来编译image
```shell
# syntax=docker/dockerfile:1
FROM python:3.7-alpine
WORKDIR /code
ENV FLASK_APP=app.py
ENV FLASK_RUN_HOST=0.0.0.0
RUN apk add --no-cache gcc musl-dev linux-headers
COPY requirements.txt requirements.txt
RUN pip install -r requirements.txt
EXPOSE 5000
COPY . .
CMD ["flask", "run"]
```

5. 创建docker-compose.yml 来编排images
```yaml
version: "3.9"
services:
  web:
    build: .
    ports:
      - "5000:5000"
  redis:
    image: "redis:alpine"
```

6. 启动docker compose
```shell
docker-compose up
```

docker-compose的默认规则
docker images
1. docker compose 相关的 images 自动下载
![[Pasted image 20211018133650.png]]
2. docker ps 
![[Pasted image 20211018133718.png]]
名字会自动增加数字 _num
3. docker service 

4. 网络 docker network
会自动创建一个composetest_default的network
docker inspect network_name
![[Pasted image 20211018133856.png]]

停止：
docker-compose down (一定要在docker-compose文件夹下)

docker-compose命令
```shell
docker-compose up -d 后台运行
docker-compose ps
docker-compose stop/down

```


#### docker-compose yaml规则
核心就是三层
```yaml
1. version #版本，是向下兼容的，所以可以直接写最新版本
2. services #服务
3. 其他配置
```
展开来
```yaml
version: '' 
services:
	service1:web
		#服务配置
		images
		build
		network
		...
	service2:redis
		...
	service3:redis
		...
[其他配置]
volumes:
networks:
configs:
```

service一些语句：
1. depends_on
```yaml
version: "3.9"
services:
  web:
    build: .
    depends_on:
      - db
      - redis
  redis:
    image: redis
  db:
    image: postgres
```

这样的话会先启动db，在启动redis，最后才会启动web


#### 开源项目
WordPress项目
https://docs.docker.com/samples/wordpress/
```yaml
version: "3.9"
    
services:
  db:
    image: mysql:5.7
    volumes:
      - db_data:/var/lib/mysql
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: somewordpress
      MYSQL_DATABASE: wordpress
      MYSQL_USER: wordpress
      MYSQL_PASSWORD: wordpress
    
  wordpress:
    depends_on:
      - db
    image: wordpress:latest
    volumes:
      - wordpress_data:/var/www/html
    ports:
      - "8000:80"
    restart: always
    environment:
      WORDPRESS_DB_HOST: db:3306
      WORDPRESS_DB_USER: wordpress
      WORDPRESS_DB_PASSWORD: wordpress
      WORDPRESS_DB_NAME: wordpress
volumes:
  db_data: {}
  wordpress_data: {}
```

假设项目要重新部署打包
```shell
docker-compose up --build
```


## Docker Swarm
官方文档：https://docs.docker.com/engine/swarm/
how docker node work?
![[Pasted image 20211019211703.png]]

1. docker swarm 初始化操作
```shell
docker swarm init --advertise-addr 172.16.149.11(ip地址 以此为manager节点)
```

2. 通过已经建立的swarm集群，找到加入的令牌
```shell
# 获取令牌
docker swarm join-token manager		# manager节点加入的令牌
docker swarm join-token worker		# worker节点加入的令牌
```

```shell
docker swarm join-token worker
```
![[Pasted image 20211019170651.png]]



```shell
# 将这个指令在第二台服务器上
docker swarm join --token SWMTKN-1-69j7t6xvexxd2x1uzrw4sak5twj3jo284mfxmt63nanr3m3u26-5yf5bmws2obarw8e11q9mg5h1 172.16.149.11:2377
```
![[Pasted image 20211019170756.png]]


3. 查看docker node ls  这个只能在manager节点查看
```shell
docker node ls
```
![[Pasted image 20211020090132.png]]


11 manager
26 manager
12 worker
23 worker

#### Raft协议
Raft协议：保证大多数节点存活才可以用。机器 > 1台，集群要>= 3台

例子：
1. 双主双从：宕机一台manager节点，不可用
2. 三主一从：宕机一台manager节点，可用！
					 宕机两台manager节点，不可用！
					 
> worker就是为了工作的，管理节才能操作。
> 官网介绍：
> -   An `N` manager cluster tolerates the loss of at most `(N-1)/2` managers.
>  Docker recommends a maximum of seven manager nodes for a swarm.

总结：
至少要3个主节点，并且保证两台可用，才能保证集群的可用性 => 高可用（high-availability）

思考：为什么不全部都是manager节点呢？这样每台服务器都可以用管理员操作
首先，明确manager node作用，根据官网，其功能：1. 维持集群状态 2. 定时服务 3. 提供API端口；注意：manager节点也会跑容器
其次，明确worker node作用，`sole purpose is to execute containers`
结论，manager和worker节点的功能不同，manager的作用不包括运行容器！

manager节点和worker节点是可以互换的：
```shell
docker node promote
docker node demote
```




副本
docker service

docker run 容器启动
docker service 服务启动！可以滚动更新


#### docker sevice
作用：弹性扩缩容
		   灰度发布/金丝雀发布
		   
```
docker service create 
```

例子：
1. 启动一个nginx的服务
```
docker service create -p 8888:80 --name my-nginx nginx
```
![[Pasted image 20211020090721.png]]

语法基本跟docker run是一致的
> docker run 容器启动
> docker service 服务，具有扩缩容

TIPS:
这个只能在manager上操作，在worker上操作的话会报错：
![[Pasted image 20211020092411.png]]


2. 查看服务
```shell
# 这个只能在manager上查看，worker节点也不行
docker service ls
```
![[Pasted image 20211020091349.png]]
这里的REPLICAS 是副本，代表只有一个副本

```shell
docker service ps my-nginx
```
![[Pasted image 20211020091419.png]]

3. update服务来增加副本
```shell
docker service update --replicas 3 my-nginx
```
![[Pasted image 20211020091731.png]]
这时就会创建3个副本到不同的服务器上，但是swarm会自己进行负载均衡
查看一下，发现运行3个nginx运行在3个不同的机器上
![[Pasted image 20211020091827.png]]

启动数量大于节点的数量：也是可以的，因为是容器化部署，所以互相并不会影响
扩展和缩减都可以：只需要更改 replicas 后面的数字

可以用`scale`命令在扩展
```shell
docker service scale my-nginx=5
```
作用效果是相同的

4. 停止服务
```shell
docker service rm my-nginx
```

