## Docker部署code-server

版本：codercom/code-server:v2

先说下问题：
1. 插件版本落后于vscode，需要手动安装旧版
2. 多种方法测试，不能直接部署中文，需要部署完成，手动安装中文插件（中文插件需要修改package.json的版本为1.39.2）

### 准备文件

需要的环境准备好，打成镜像直接复制进去，同时配置环境变量（未加入python环境）

```shell
drwxr-xr-x.  6 root root     92 12月 17 16:49 apache-maven-3.6.0
-rwxr-xr-x.  1 root root    948 12月 19 13:06 Dockerfile
drwxr-xr-x. 21 root root   4096 12月 19 11:59 extensions
drwxr-xr-x.  8 root root   4096 12月 19 09:11 jdk1.8.0_162
drwxr-xr-x.  6 root root     46 12月 19 09:16 scala-2.11.12
-rwxr-xr-x.  1 root root   1582 12月 19 13:02 settings.json
```

### Dockerfile

```shell
FROM codercom/code-server:v2
# ADD
ADD jdk1.8.0_162 /usr/local/jdk1.8.0_162/
ADD apache-maven-3.6.0 /usr/local/apache-maven-3.6.0/
ADD scala-2.11.12 /usr/local/scala-2.11.12/
# code-server总配置文件
ADD settings.json /root/.local/share/code-server/User/
# code-server已安装的插件
ADD extensions /root/.local/share/code-server/extensions

# Env
ENV JAVA_HOME /usr/local/jdk1.8.0_162
ENV JRE_HOME /usr/local/jdk1.8.0_162/jre
ENV PATH $PATH:$JAVA_HOME/bin:$JRE_HOME/bin
ENV CLASSPATH .:$JAVA_HOME/lib:$JRE_HOME/lib
ENV SCALA_HOME /usr/local/scala-2.11.12
ENV MAVEN_HOME=/usr/local/apache-maven-3.6.0

ENV PATH=$MAVEN_HOME/bin:$SCALA_HOME/bin:$PATH

WORKDIR /home/coder/project
# 容器启动code-server：指定插件目录，指定中文，指定免密登录
ENTRYPOINT ["code-server","-locale","zh-cn","--auth","none"]
```

### 制作image

```shell
$ docker build -t cstor/code-server:v2 --rm=true .
```

### 启动容器

```shell
$ docker run -dit --restart=always --privileged=true --name code -p 8086:8080 -h master -u root -v /root/code-server:/home/ cstor/code:v2
```

## code-server启动命令参数

如果使用docker，这些参数可以作为Dockerfile中最后执行的命令的参数；

一般使用：

```shell
# 在docker中，code-server
$ code-server --auth none --port 8086 --bash-path .
```

所有命令参数：

```shell
Options
  --locale <locale>             # 设置语言，不装插件，此项没用；装了插件，不需要配置此项；
  --user-data-dir <dir>         # 设置用户目录 默认/root/.local/share/code-server/User
  -v --version                   
  -h --help                      
  --telemetry                   # 不知道
  --extra-builtin-extensions-dir# 没用
  --extra-extensions-dir        # 没用
  --base-path                   # 默认/root/.local/share/code-server
  --cert                        	
  --cert-key                     
  --format                       	
  --host                        # 主机
  --auth                        # 默认是给一个随机密码，"--auth none"免密
  --open                        # 启动自动打开浏览器
  --port                        # 端口
  --socket                      # 主机：端口

# 插件管理
  --extensions-dir <dir>        # 插件目录 默认/root/.local/share/code-server/extenstions
  --list-extensions             # 终端列出插件                  
  --show-versions                        
  # 命令行安装插件，需要插件id或者离线vsix，没啥用，插件id要从vscode市场去找
  --install-extension <extension-id | path-to-vsix>	
  --uninstall-extension <extension-id>	
# 错误排查
  --log <level>                   # 日志路径
  --max-memory                    # 最大分配内存
```

## IDE内部配置

参考VSCode的使用方法

### setteings.json

左下角齿轮——》setting——》右上角转为使用json文件来配置设置

全局所有设置，都可以在这里实现

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
    "java.home": "/usr/local/jdk1.8.0_162",
    "maven.executable.path": "/usr/local/apache-maven-3.6.0/bin/mvn",
    "java.configuration.maven.userSettings": "/usr/local/apache-maven-3.6.0/conf/settings.xml",
    "maven.terminal.customEnv": [
        {
            "environmentVariable": "JAVA_HOME",
            "value": "/usr/local/jdk1.8.0_162"
        }
    ],
    "python.formatting.provider": "yapf",
    "python.autoComplete.addBrackets": true,
    "python.jediEnabled": false,
    "python.linting.pylintEnabled": true,
    // exclude file
    "files.exclude": {
        "**/.classpath": true,
        "**/.project": true,
        "**/.settings": true,
        "**/.factorypath": true,
        "**/.vscode": true,
        "**/.empty": true,
    },
    // code-runner
    "code-runner.clearPreviousOutput": true,
    "code-runner.runInTerminal": false,
    // 执行文件的脚本，可以使用绝对路径
    "code-runner.executorMap": {
        "python": "/usr/local/anaconda3/python3",
        "scala": "/usr/local/scala-2.11.12/bin/scala",
        "java": "cd $dir && javac $fileName && java $fileNameWithoutExt",
    },
}
```

### 插件

总体来说：插件市场是跟随VSCode，code-server是落后于VSCode版本的；

理论上直接market安装，但不保证能正常工作；

1. 如果缺少依赖，可尝试在插件目录中`npm install`重新安装

2. 如果版本不匹配，比如：插件版本是1.40，但是code-server只是1.39.2版本

   只需要将插件离线下载，打开，找到packge.json文件，修改里面的版本为1.39.2即可；

### 创建Maven工程

`ctrl+shift+p`是所有命令的总入口

比如创建maven工程：

```shell
ctrl+shift+p	
输入：maven
选项：
Maven:create maven project	# 创建maven工程
Maven:execute commands		# 执行maven命令，插件
```

## 镜像打包

### 容器生成新镜像

```shell
docker commit -a "runoob.com" -m "my apache" [容器ID] [生成镜像名]
```

### 镜像保存为文件

```shell
docker save -o [文件名] [要保存的镜像名]
```

### 文件生成镜像

```shell
docker load < [文件名]
```

### 启动镜像

```shell
docker run -dit --restart=always --privileged=true --name code -p 8086:8080 -h master -u root -v /root/code-server:/home/ cstor/code:v2
```

