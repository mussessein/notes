# Linux

## 端口

#### lsof

根据**PID**查看进程

deepin中：100以下的端口，需要root权限访问

查看端口占用情

```shell
$ lsof -i:prot
$ lsof -i | grep port
# 如果有显示端口，则已经开放，如果没有消息，则没有开放
```

#### netstat

根据**端口**查看进程

```shell
$ netstat 
-t：指定tcp端口
-u：指定UDP端口
-p：显示进程名称
-n：不进行DNS轮寻（可以加速操作）
$ netstat -lnp|grep 22 	# 查看所有22端口使用情况
```

## 进程

#### ps

根据**进程名字**查看

一般用grep来查看某个进程

```shell
- a 列出终端的进程
- w 显示加宽可以显示较多的资讯
- au 显示详细资讯
- aux 显示包含其他使用者的进程
$ ps -ef | grep -i redis # 查看redis进程
```

#### top

实时显示进程，可以查看内存占用情况

```
top
-i 不显示任何闲置
```

#### kill

杀死进程

```shell
kill -9 进程id  # 强制终止进程
```

开放指定端口

```shell
$ sudo iptables -I INPUT -i eth0 -p tcp --dport 22 -j ACCEPT
```

## 服务管理

service

[start | stop | restart | reload | status]

```shell
$ service mysqld start
$ service mysqld status
```

查看所有服务

```shell
$ ll /etc/init.d/
```

## 磁盘

#### df

磁盘占用情况

```shell
$ df -h 	# 格式化查看所有磁盘占用
$ df -h
```

#### du

指定目录或文件的占用磁盘空间

```shell
$ du -h --max-depth=1	# 只显示一层目录,-h格式化
```

## curl

通过指定的URL来上传或下载数据，并将数据展示出来

1. curl

```shell
$ curl www.baidu.com
```

单纯访问域名，返回响应实体；

2. curl -v

用于输出通信的整个过程

```shell
$ curl -v www.baidu.com
```

3. curl -d

用于发送POST请求的数据体，如果是用户名密码，也可以用 - u

```shell
$ curl -d'login=emma＆password=123'-X POST https://google.com/login
```

4. curl -I (大写)

表示向服务器发送HEAD请求，返回响应头

```shell
$ curl -I www.baidu.com
$ curl --head www.baidu.com
```

5. curl -o (小写)

等同于wget命令

## 日志定位分析

### cat

查看日志

```shell
$ cat *.log > example.log # 合并两个日志
```

### grep

抓取日志，过滤日志 配合cat使用

-n：显示行号

-c：统计返回记录数

-E：使用正则表达式匹配

```shell
# 拆分404日志到一个新的文件
$ grep "404" a.log > 404.txt
# 配合cat使用 -n：显示行号
$ cat a.log | grep -n '404'
# 打印首次出现ERROR的行号
$ grep -n "ERROR" a.log | tail -1
```

3. ps查看进程

```shell
# 查看java进程
$ ps -ef | grep java
```









## Linux终端快捷键

1. ctrl+l：小写L，清屏
2. ZZ：vim状态下，保存退出
3. ZQ：vim下，不保存退出









## debian安装命令

```shell
$ dpkg -i 安装包路径
```

