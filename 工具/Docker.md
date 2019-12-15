# Docker

https://blog.csdn.net/deng624796905/article/details/86493330

## 命令

###　docker启动

~~~shell
# 启动Docker-CE
$ sudo systemctl enable docker	# 自启服务
$ sudo systemctl start docker	# 启动服务
~~~

### 镜像命令

#### pull

拉取镜像

~~~shell
docker pull library/hello-world
~~~

- `docker pull images` 是抓取 `image` 文件，`library/hello-world` 是 `image` 文件在仓库里面的位置

- `library` 是 `image` 文件所在的组， `hello-world` 是 `image` 文件的名字；

- 后面可以继续用`:`指定`image`文件的版本，不指定版本默认`lastest`

#### image

有关镜像命令

~~~shell
$ sudo docker image
Commands:
  build       Build an image from a Dockerfile
  history     Show the history of an image
  import      Import the contents from a tarball to create a filesystem image
  inspect     Display detailed information on one or more images
  load        Load an image from a tar archive or STDIN
  ls          List images
  prune       Remove unused images
  pull        Pull an image or a repository from a registry
  push        Push an image or a repository to a registry
  rm          Remove one or more images
  save        Save one or more images to a tar archive (streamed to STDOUT by default)
  tag         Create a tag TARGET_IMAGE that refers to SOURCE_IMAGE
~~~

批量删除

### 容器命令

#### ps

查看容器

~~~shell
$ sudo docker ps	# 查看当前运行的容器
$ sudo docker container ls
$ sudo docker ps -a	# 查看所有容器
~~~

#### run

#### stop

停止容器

```shell
$ sudo docker start 容器id
```

#### rm

```shell
# 批量删除所有
$ docker rm $(docker ps -a -q)
```

### 进入容器

####　attach

```shell
$ docker attach 容器ID
```

#### exec

```shell
$ docker exec -it 容器ID /bin/bash
```



### logs

查看logs信息

```shell
$ docker logs 容器ID
```



### 保存容器为新镜像

```shell
docker commit -m “” -a “” [CONTAINER ID] [给新的镜像命名]
```

### 保存容器为新文件

```shell
docker save -o 要保存的文件名 要保存的镜像
```

### 导入文件生成镜像

```shell
docker load --input 文件 或者 docker load < 文件名
```







## 部署项目到docker

https://blog.csdn.net/puhaiyang/article/details/77530417

## 部署code-server

版本：codercom/code-server:1.939

code-server最新版本是v2，但是java环境下，插件支持性很差，所以选择1.939版本；

### 准备文件

```shell
whr@master:~/Desktop/docker-code-server$ ll
总用量 16
drwxr-xr-x 6 whr whr 4096 9月  14 13:15 apache-maven-3.6.0
-rw-r--r-- 1 whr whr  470 11月 27 15:39 Dockerfile
drwxr-xr-x 7 whr whr 4096 4月   2  2019 jdk1.8
drwxr-xr-x 2 whr whr 4096 11月 27 14:58 vsix
```

- Maven，JDK：准备的环境
- vsix：vscode插件
- Dockerfile

### Dockerfile

```shell
FROM codercom/code-server:1.939
# Copy
COPY vsix /opt/vsix/
COPY jdk1.8 /opt/jdk1.8/
COPY apache-maven-3.6.0 /opt/apache-maven-3.6.0
# Env
ENV JAVA_HOME=/opt/jdk1.8/
ENV PATH=$JAVA_HOME/bin:$PATH
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV MAVEN_HOME=/opt/apache-maven-3.6.0
ENV PATH=$MAVEN_HOME/bin:$PATH
# port
EXPOSE 8086
WORKDIR /home/coder/project
ENTRYPOINT ["code-server","-N"]
```

### 制作image

```shell
$ docker build -t cstor/code-server:v1 --rm=true .
```

### 启动容器

```shell
$ docker run -dit --restart=always --privileged=true --name code-server -p 8086:8443 -h master -u root -v /root/code-server:/opt/ cstor/code-server:v1
```

- --privileged：让容器内root用户拥有真正root权限
- -p：端口映射，如果要使用SpringBoot，提前在这里，添加多个端口绑定，比如：`-p 8087:8080`
- -h：host
- -v：挂载磁盘
- -N：是code-server的命令，免密登录

### 存在问题

- java插件无法使用（目前已知是版本问题，安装历史版本可以使用）
- 中文插件需要修改package.json的版本字段

### code-server:1.939命令参数

在Dockerfile中，添加启动命令

常用命令：

- -N：免密登录
- -P（大写）：指定IDE启动的password
- -p（小写）：指定code-server的启动端口

```shell
root@master:/usr/bin# code-server --help
Usage: code-server [options]
Options:
  -V, --version                output the version number
  --cert <value>               
  --cert-key <value>           
  -e, --extensions-dir <dir>   Set the root path for extensions.
  -d --user-data-dir <dir>      Specifies the directory that user data is kept in, useful when running as root.
  --data-dir <value>           DEPRECATED: Use '--user-data-dir' instead. Customize where user-data is stored.
  -h, --host <value>           Customize the hostname. (default: "0.0.0.0")
  -o, --open                   Open in the browser on startup.
  -p, --port <number>          Port to bind on. (default: 8443)
  -N, --no-auth                Start without requiring authentication.
  -H, --allow-http             Allow http connections.
  -P, --password <value>       DEPRECATED: Use the PASSWORD environment variable instead. Specify a password for authentication.
  --disable-telemetry          Disables ALL telemetry.
  --socket <value>             Listen on a UNIX socket. Host and port will be ignored when set.
  --install-extension <value>  Install an extension by its ID.
  --bootstrap-fork <name>      Used for development. Never set.
  --extra-args <args>          Used for development. Never set.
  -h, --help                   output usage information
```

### code-server内部配置

参考VSCode的使用方法

1. setteing.json

   ```json
   {
       // Nomal
       "editor.fontSize": 18,
       "workbench.iconTheme": "vscode-icons",
       "vsicons.dontShowNewVersionMessage": true,
       "editor.minimap.enabled": true,
       "workbench.colorTheme": "Visual Studio Light",
       "workbench.startupEditor": "newUntitledFile",
       // 保存格式化
       "files.autoSave": "onFocusChange",
       "editor.formatOnPaste": true,
       "editor.formatOnType": true,
       // Env
       "java.home": "/opt/jdk1.8",
       "maven.executable.path": "/opt/apache-maven-3.6.0/bin/mvn",
       "java.configuration.maven.userSettings": "/opt/apache-maven-3.6.0/conf/settings.xml",
       "maven.terminal.customEnv": [
           {
               "environmentVariable": "JAVA_HOME",
               "value": "/opt/jdk1.8"
           }
       ],
       "python.formatting.provider": "yapf",
       "python.autoComplete.addBrackets": true,
       "python.jediEnabled": false,
       "python.linting.pylintEnabled": true,
       // Debug
       "files.exclude": {
           "**/.classpath": true,
           "**/.project": true,
           "**/.settings": true,
           "**/.factorypath": true
       },
       // code-runner
       "code-runner.clearPreviousOutput": true,
       "code-runner.runInTerminal": false,
       "code-runner.executorMap": {
           // "javascript": "node",
           // "php": "C:\\php\\php.exe",
           "python": "python3",
           "scala": "/opt/scala-2.13.1/bin/scala",
           // "perl": "perl",
           // "ruby": "C:\\Ruby23-x64\\bin\\ruby.exe",
           // "go": "go run",
           // "html": "\"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe\"",
           "java": "cd $dir && javac $fileName && java $fileNameWithoutExt",
           // "c": "cd $dir && gcc $fileName -o $fileNameWithoutExt && $dir$fileNameWithoutExt"
       },
   }
   ```

2. 离线安装插件（要安装对应VSCode版本的插件，最新的插件可能存在不支持的情况）

   不建议直接在IDE内安装插件！

3. 配置代码模板

   File--Preferences--User Snippets（或者：左下角设置--User Snippets）

### 修改容器端口映射

如果向在运行的容器中新添加端口映射：

```shell

```

