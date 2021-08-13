[[IT_TAG]] #IT 

1. mac安装

brew install goaccess

linux安装

yum install goaccess

2. 学习网站

https://www.xyhtml5.com/6458.html



## 配置文件介绍

打开`/usr/local/etc/goaccess/goaccess.conf`(这里要强调的是这个`goaccess.conf`配置文件的位置一定要搞清楚，我这里是 GoAccess 1.3 编译安装默认的位置），如果不确定位置可以使用 `whereis goaccess.conf’命令来查询具体位置）里面的最主要的几个配置为：

- time-format `%H:%M:%S`
- date-format `%d/%b/%Y`
- log-format

网络上大部分的文章和介绍都只适合没任何修改的nginx日志格式，对自定义的log format都不怎么涉及。如果你采用的自定义的nginx日志格式，那么此处就需要特别注意，一旦log-format配置不对，goaccess分析的结果会差很大。

以我nginx日志格式为例：

```
log_format main  '$server_name $remote_addr - $remote_user [$time_local] "$request" '
'$status $body_bytes_sent "$http_referer" '
'"$http_user_agent" "$http_x_forwarded_for" '
'$upstream_addr $request_time $upstream_response_time';
```

按照goaccess预设的log format，这样的日志是没法分析的，所以我们需要自定义log format。

我的log format为： `log-format %^ %h %^ %^ [%d:%t %^] “%r” %s %b “%R” “%u” “%^” %^ %T %^`

## log-format参数说明

- `%t` 匹配time-format格式的时间字段
- `%d` 匹配date-format格式的日期字段
- `%h` host(客户端ip地址，包括ipv4和ipv6)
- `%r` 来自客户端的请求行
- `%m` 请求的方法
- `%U` URL路径
- `%H` 请求协议
- `%s` 服务器响应的状态码
- `%b` 服务器返回的内容大小
- `%R` HTTP请求头的referer字段
- `%u` 用户代理的HTTP请求报头
- `%D` 请求所花费的时间，单位微秒
- `%T` 请求所花费的时间，单位秒
- `%^` 忽略这一字段

## 为了设置正确的log format，踩了不少坑，先列出来避免大家重复碰到。

- log format默认是按照空格分隔日志信息的，所以，对于包含了特殊字符如空格等信息的字段，必须包含在“”里面。如字段`request http_user_agent`等
- nginx日志格式里面，采用空格分隔，但是此处一定注意，只能用一个空格。当时我有个地方用了两个空格，直接导致goaccess结果出错。
- nginx日志中的每一个字段都要和log format中的一一对应，如果log format中不需要nginx中的某一个信息，则用%^跳过该信息。
- 对于nginx日志中的每一个 “-“,log format都需要一个`%^`来跳过， 如果是“-”， 则用`“%^”`
- 如果nginx日志信息中有”：” ,则需要在log format中也显示出来。例如nginx日志中$time_local就包含了”：”,所以在log format的相应位置也是 `%d:%t%^`

![GoAccess-logo.png](https://www.xyhtml5.com/wp-content/uploads/2021/01/1722603424.png)

## 实例

- 下面是我目前使用的日志分析输出为 HTML 的命令行

```
/usr/local/bin/goaccess -f /home/wwwlogs/www.xxxxx.com.log -p /usr/local/etc/goaccess/gaccess.conf -a > /home/www/default/blog.html
```

- 下面是我目前使用的日志分析实时输出为 HTML 的命令行

```
/usr/local/bin/goaccess /home/wwwlogs/www.xxxxx.com.log -o /home/www/default/report.html --real-time-htm -p /usr/local/etc/goaccess/goaccess.conf
```