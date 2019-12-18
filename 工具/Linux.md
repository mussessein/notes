# Linux

## 防火墙端口

CentOS 7 版本（阿里云服务器开放端口之后，还需要在设置中添加端口访问）

### 防火墙状态

```shell
# 查看防火墙状态
systemctl status firewalld
# 关闭打开防火墙
systemctl start/stop firewalld
# 重启保持防火墙状态
systemctl enable firewalld
```

### 开放端口

```shell
firewall-cmd --zone=public --add-port=8080/tcp --permanent
# 记得刷新规则
firewall-cmd --reload
```

### 查看端口

```shell
#查看已开放的所有端口
firewall-cmd --list-ports
# 查看已开放端口
firewall-cmd --zone=public --query-port=8080/tcp
```

### 关闭端口

```shell
firewall-cmd --zone=public --remove-port=8080/tcp --permanent
```

## 进程

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

#### kill

杀死进程

```shell
kill -9 进程id  # 强制终止进程
```

开放指定端口

```shell
$ sudo iptables -I INPUT -i eth0 -p tcp --dport 22 -j ACCEPT
```

## 内存管理

### top—查看内存占用

```shell
# top排序策略
top -N	# 按PID大小
top -P	# 按CPU占用率
top -M	# 按内存占用率
```

```shell
PID USER      PR  NI    VIRT    RES    SHR S %CPU %MEM     TIME+ COMMAND
1417 root     10 -10  128712  15124   9312 S  1.3  0.8   1:59.10 AliYunDun
3002 root     20   0  859212  67128  16640 S  0.3  3.6   0:10.63 code-server
1 root      20   0  125168   3696   2448 S  0.0  0.2   0:01.07 systemd
```

- PID
- USER
- PR
- VIRT： 虚拟内存
- RES： 常驻内存
- SHR： 共享内存
- %CPU
- %MEM

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

