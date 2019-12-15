# 集群脚本

## xcall

```shell
#! /bin/bash
echo ----------- master -----------
"$*"
for i in slave1 slave2
do
        echo ----------- $i -----------
        ssh $i "$*"
done
```

- master是执行命令的节点，不能使用ssh名，单独放出来

## zxCall

```shell
#! /bin/bash
case $1 in
"start"){

	echo -------------- master --------------
	/home/whr/workbench/zookeeper/bin/zkServer.sh start
	for i in slave1 slave2
	do
		echo -------------- $i --------------
		ssh $i "/home/whr/workbench/zookeeper/bin/zkServer.sh start"
	done
};;
"stop"){
	echo -------------- master --------------
	/home/whr/workbench/zookeeper/bin/zkServer.sh stop
	for i in slave1 slave2
	do
		echo -------------- $i --------------
		ssh $i "/home/whr/workbench/zookeeper/bin/zkServer.sh stop"
	done
};;
"status"){
	echo -------------- master --------------
	/home/whr/workbench/zookeeper/bin/zkServer.sh status
	for i in slave1 slave2
	do
		echo -------------- $i --------------
		ssh $i "/home/whr/workbench/zookeeper/bin/zkServer.sh status"
	done
};;
esac
```

- 执行：zkCall.sh start/stop/status

## sparkCall

```shell
#! /bin/bash
case $1 in
"start"){
	echo ----------- spark starting -----------
	/home/whr/workbench/spark/sbin/start-all.sh
};;
"stop"){
	echo ----------- spark stopping -----------
	/home/whr/workbench/spark/sbin/stop-all.sh
};;
esac
```

- 这个命令不需要在子节点上执行，只需要在master上执行

## xhbase

```shell
#! /bin/bash
case $1 in
"start"){
	echo ----------- hbase starting -----------
	/home/whr/workbench/hbase/bin/start-hbase.sh
};;
"stop"){
	echo ----------- hbase stopping -----------
	/home/whr/workbench/hbase/bin/hbase-daemon.sh stop master
	/home/whr/workbench/hbase/bin/hbase-daemon.sh stop regionserver
};;
esac
```

- 同样只需要在master上进行

# shell笔记

## $0、$!、$$、$*、$#、$@

- $0：shell文件本身文件名
- $1~n：shell命令的参数
- $!：Shell最后运行的后台Process的PID
- $*：所有参数列表（类似$@）
- $$：shell命令本身的PID

