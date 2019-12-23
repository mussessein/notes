# Nginx

 https://blog.csdn.net/qq_29677867/article/details/90112120 

## 概述

### Nginx可以做什么

1. Web服务
2. 均衡负载（反向代理）
3. Web缓存

### Nginx优点

1. 高并发。静态小文件
2. 占用资源少。2万并发、10个线程，内存消耗几百M。
3. 利用nginx可以对IP限速，可以限制连接数。
4. 配置简单，更灵活。

### 安装

依赖：

```shell
yum install  pcre pcre-devel -y 
yum install openssl openssl-devel -y 
```

官网下载包，解压，编译

```shell
./configure
make && make install 
```

目录结构

```shell
# 目录
/usr/local/nginx/
drwxr-xr-x 3 root   root 4096 Dec 18 14:08 conf	# 配置文件
drwxr-xr-x 2 root   root 4096 Dec 18 13:40 html	# 静态文件
drwxr-xr-x 2 root   root 4096 Dec 18 13:41 logs	# 日志
drwxr-xr-x 2 root   root 4096 Dec 18 13:40 sbin	# 可执行文件
```

命令

```shell
./sbin/nginx 		# 启动
		-s stop		#
		-s reload 	# 重新加载
		-s stop		# 停止
```

设置开机自启动

```shell
vi /etc/rc.local
增加一行 /usr/local/nginx/sbin/nginx
chmod 755 rc.local
```

## 配置

默认配置

```yaml
#user  nobody;
worker_processes  1;
pid        logs/nginx.pid	# 进程存放地址
events {
    worker_connections  1024;	# 每个进程的最大连接数
}
# include cond.d/*.conf;
http {
    include       mime.types;	# mime.types是一张映射表
    default_type  application/octet-stream;
    sendfile        on;			# 允许sendfile方式传输文件
    keepalive_timeout  65;		# 连接超时时间
    server {
            listen       80;	# 监听端口
            server_name  域名;
            location / {		# location：用于反向代理
                proxy_pass http://127.0.0.1:8086;	# 将监听端口转发到这里
                root   html;
                index  index.html index.htm;
            }
            error_page   500 502 503 504  /50x.html;
            location = /50x.html {
                root   html;
            }
     }
}
```





